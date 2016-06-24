package de.client;

import java.util.Vector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SelectElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.report.HTMLWriter;
import de.server.db.SuchprofilMapper;
import de.shared.LoginServiceAsync;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;
import de.shared.RO.ProfilReport;

public class ReportEntry implements EntryPoint {

	private ReportServiceAsync reportService;
	
	//Die beiden Buttons + Panel
	private Button alleAnzeigenButton = new Button("Alle Profile", 
			new AlleProfileHandler());
	private Button nichtBesuchteButton = new Button("Nicht besuchte Profile", 
			new NichtBesuchteHandler());
	private HorizontalPanel navPanel = new HorizontalPanel();
	
	//Suchprofil
	private VerticalPanel suchprofilPanel = new VerticalPanel();
	private Label suchProfileAnzeigenLabel = new Label("Filtere nach Suchprofil");
	private HorizontalPanel filterPanel = new HorizontalPanel();
	private ListBox suchprofilListbox = new ListBox();
	private CheckBox suchprofilCheckbox = new CheckBox();
	
	//Speicher
	private Vector<Suchprofil> suchprofile;
	private Suchprofil aktivesSP;
	
	//Main Panels
	private VerticalPanel links = new VerticalPanel();
	private VerticalPanel rechts = new VerticalPanel();

	
	
	public void onModuleLoad() {
		RootPanel.get("Navi").add(navPanel);
		RootPanel.get("Body-left").add(links);
		RootPanel.get("Body-right").add(rechts);
		suchprofilListbox.addClickHandler(new SucheChangeHandler());
		reportService = ClientSideSettings.getReportService();
		LoginServiceAsync loginService = ClientSideSettings.getLoginService();
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html", new AsyncCallback<Profil>() {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("login callback" + caught.toString()));
			}
			public void onSuccess(Profil result) {
				//Style
				alleAnzeigenButton.setStylePrimaryName("navi-button");
				nichtBesuchteButton.setStylePrimaryName("navi-button");
				suchProfileAnzeigenLabel.setStylePrimaryName("textlabel");
				suchprofilListbox.setStylePrimaryName("listbox");
				
				//Zusammenfügen der Buttons
				navPanel.add(alleAnzeigenButton);
				navPanel.add(nichtBesuchteButton);
				filterPanel.add(suchProfileAnzeigenLabel);
				filterPanel.add(suchprofilCheckbox);
				rechts.add(filterPanel);
				rechts.add(suchprofilListbox);
				rechts.add(suchprofilPanel);
				reportService.setUser(result, new AsyncCallback() {
					public void onFailure(Throwable caught) {
						RootPanel.get().add(new Label("SetUserCallback " + caught.toString()));
					}
					public void onSuccess(Object result) {
						loadSuchprofile();
					}
				});
			}
		});
	}
	
	/*
	 * L�dt initial alle Suchprofile. --> Baut die ListBox zum Selektieren auf
	 * --> �bergibt dem ListBox ChangeHandler das Suchprofil.
	 */
	public void loadSuchprofile() {
		try {
			reportService.getSuchprofile(new AsyncCallback<Vector<Suchprofil>>() {
				public void onFailure(Throwable caught) {
					Window.alert("getSuchprofileCallback " + caught.toString());
				}
				public void onSuccess(Vector<Suchprofil> result) {
					suchprofile = result;
					suchprofilListbox.addItem("Wähle Dein Suchprofil aus");
					for (int i = 0; i < result.size(); i++) {
						suchprofilListbox.addItem(result.elementAt(i).getSuchprofilname());
					}
					try {
						loadAllReports();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//HtML Reports erstellen lassen f�r Report-Listen
	public void loadHtmlReports(Vector<ProfilReport> reports){
		links.clear();
		if(reports.size()==0)links.add(new Label("Keine Ergebnisse"));
		HTMLWriter writer = new HTMLWriter();
		writer.process(reports);
		links.add(new HTML(writer.getReportText()));
	}
	
	/*
	 * Alle Profile laden
	 */
	public void loadAllReports() throws Exception{
		reportService.getReports(new ReportCallback());
	}

	/*
	 * Baut Reports f�r nicht besuchte Profile auf
	 */
	public void loadReportsForNotVisited() throws Exception {
		reportService.getNotVisitedReports(new ReportCallback());
	}
	/*
	 * Alle Profile nach Suchprofil gefiltert
	 */
	public void loadAllReports(Suchprofil sp) throws Exception {
		reportService.getReports(sp,new ReportCallback());
	}
	
	/*
	 * Alle nicht besuchten Profile nach Suchprofil gefiltert
	 */
	public void loadReportsForNotVisited(Suchprofil sp) throws Exception {
//		reportService.getNotVisitedReports(sp,new ReportCallback());
	}
	

	/*
	 * Callback der LoadReport() Methoden
	 */
	private class ReportCallback implements AsyncCallback<Vector<ProfilReport>>{
		public void onFailure(Throwable caught) {
			Window.alert("Entry.loadReportsForNotVisited " + caught.toString());
		}
		public void onSuccess(Vector<ProfilReport> result) {
			loadHtmlReports(result);
		}
	}
	
	
	/*
	 * Der ChangeHandler der Suchprofil-ListBox
	 */
	private class SucheChangeHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			SelectElement selectElement = suchprofilListbox.getElement().cast();
            selectElement.getOptions().getItem(0).setDisabled(true);
			Suchprofil sp = getSuchprofil();
			if(sp != null){
				updateSuchprofilPanel(sp);
				aktivesSP = sp;
			}
		}
	}
	
	public void updateSuchprofilPanel(Suchprofil sp){
		suchprofilPanel.setStylePrimaryName("suchprofil-filter");
		suchprofilPanel.clear();
		suchprofilPanel.add(new Label("Geschlecht: " + sp.getGeschlecht()));
		suchprofilPanel.add(new Label("Raucher: " + sp.getRaucher()));
		suchprofilPanel.add(new Label("Haarfarbe: " + sp.getHaarfarbe()));
		suchprofilPanel.add(new Label("Religion: " + sp.getReligion()));
		suchprofilPanel.add(new Label("Körpergröße von " + sp.getMinGroesse()+ " bis " + sp.getMaxGroesse()));
		suchprofilPanel.add(new Label("Alter von  " + sp.getMinAlter() + " bis " + sp.getMaxAlter()));
		
		
		for(int i = 0; i < sp.getProfileigenschaften().size(); i++){
			ProfilEigenschaft pe = sp.getProfileigenschaften().elementAt(i);
			suchprofilPanel.add(new Label(pe.getName() + ": " + pe.getWert()));
		}
	}
	
	/*
	 * Weil wir leider nicht mit einem DataListProvider arbeiten,
	 * m�ssen wir die Strings der ListBox mit den Suchprofilen abgleichen.
	 */
	public Suchprofil getSuchprofil(){
		Suchprofil sp = null;
		for(int i = 0; i < suchprofile.size(); i++){
			if(suchprofile.elementAt(i).getSuchprofilname().equals(
					suchprofilListbox.getItemText(suchprofilListbox.getSelectedIndex()))){
				sp = suchprofile.elementAt(i);
				
				break;
			}
		}
		return sp;
	}
	
	/*
	 * ClickHandler f�r AlleProfile-Button
	 */
	public class AlleProfileHandler implements ClickHandler{
		public void onClick(ClickEvent e){
			if(suchprofilCheckbox.getValue()){
				try {
					loadAllReports(aktivesSP);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}else{
				try {
					loadAllReports();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * ClickHandler f�r Nicht besuchte Profile-Button
	 */
	public class NichtBesuchteHandler implements ClickHandler{
		public void onClick(ClickEvent e){
			if(!suchprofilCheckbox.getValue()){
				try {
					loadReportsForNotVisited(aktivesSP);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			try {
				loadReportsForNotVisited();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
}
