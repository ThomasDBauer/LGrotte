package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.server.db.seeds.EigenschaftSeeds;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;

public class SuchprofilInfoEditor extends VerticalPanel{
		
		
		//Buttons
		private HorizontalPanel buttonPanel = new HorizontalPanel();
		private Button addEigenschaftenButton = new Button("Eigenschaft hinzuf�gen", 
				new AddEigenschaftenClickHandler());
		private Button speicherButton = new Button("Speichern", new SpeichernClickHandler());
		private Button loeschenButton = new Button("Löschen", new LoeschenClickHandler());
		
		//Ergebnisse
		private FlexTable resultTable = new FlexTable();

		//Editieren
		private VerticalPanel editPanel = new VerticalPanel();
		private VerticalPanel eigenschaftenPanel = new VerticalPanel();
		private Label nochkeinInfosLabel = new Label("Lass uns noch genauer suchen");
		private int aktiveEigenschaftenCounter = 0;

		//Speicher
		private Vector<ListBox> eigenschaftenListboxen = new Vector<ListBox>();
		private Vector<TextBox> infoTextboxen = new Vector<TextBox>();
		private Vector<Eigenschaft> eigenschaften;
		private Vector<Info> infosToDelete = new Vector<Info>();
		
		//Das Suchprofil + Setter
		private Suchprofil suchprofil;
		public void setSuchprofil(Suchprofil suchprofil){
			this.suchprofil = suchprofil;
			loadSuchprofilEigenschaften();
		}
		
		
		//Konstruktor
		public SuchprofilInfoEditor() throws Exception {	
			buttonPanel.add(addEigenschaftenButton);
			speicherButton.setStylePrimaryName("grotte-Button");
			addEigenschaftenButton.setStylePrimaryName("grotte-Button");
			this.add(buttonPanel);
			this.add(editPanel);
			this.add(eigenschaftenPanel);
			ClientSideSettings.getEditorService().getEigenschaften(new GetEigenschaftenCallback());
		}
		
		
		//Eigenschaften laden +
		public void loadSuchprofilEigenschaften() {
			resultTable.clear();
			nochkeinInfosLabel.removeFromParent();
			try {
				ClientSideSettings.getEditorService().getSuchprofilEigenschaften(
						suchprofil.getSuchprofilname(), new LoadSuchprofileCallback());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		//+ Callback
		private class LoadSuchprofileCallback implements AsyncCallback<Vector<ProfilEigenschaft>> {
			public void onFailure(Throwable caught) {
				eigenschaftenPanel.add(new Label("LoadProfileCallback " + caught.toString()));
			}
			public void onSuccess(Vector<ProfilEigenschaft> result) {
				eigenschaftenPanel.clear();
				if (result.size() == 0) {
					eigenschaftenPanel.add(nochkeinInfosLabel);
				}
				for (int i = 0; i < result.size(); i++) {
					resultTable.setWidget(i, 0, new Label(result.elementAt(i).getName() + ": "));
					resultTable.setWidget(i, 1, new Label(result.elementAt(i).getWert()));
					CheckBox cb = new CheckBox();
					cb.addClickHandler(new AddToDeleteHandler(result.elementAt(i).getInfo()));
					resultTable.setWidget(i, 2, cb);
				}
				eigenschaftenPanel.add(resultTable);
			}
		}
		
		
		//Add To Delete Handler
		private class AddToDeleteHandler implements ClickHandler{
			private Info info;
			public AddToDeleteHandler(Info info){
				this.info = info;
			}
			public void onClick(ClickEvent e){
				CheckBox cb = (CheckBox) e.getSource();
				if(cb.getValue()){
					infosToDelete.add(info);
				}else{
					infosToDelete.remove(info);
				}
				if(infosToDelete.size()!=0){
					buttonPanel.add(loeschenButton);
				}else{
					loeschenButton.removeFromParent();
				}
			}
		}
		
		//Delete ClickHandler
		private class LoeschenClickHandler implements ClickHandler {
			public void onClick(ClickEvent e){
				for(int i = 0; i < infosToDelete.size(); i++){
					try {
						ClientSideSettings.getEditorService().deleteSuchprofilInfo(
								suchprofil, infosToDelete.elementAt(i), new AsyncCallback(){
									public void onFailure(Throwable caught) {
										Window.alert("LoeschenClickHandler " + caught.toString());
									}
									public void onSuccess(Object result) {
										loeschenButton.removeFromParent();
										infosToDelete.clear();
										try {
											loadSuchprofilEigenschaften();
										} catch (Exception e) {
											e.printStackTrace();
										}
									}
								});
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		
		//Hinzufügen ClickHandler
		private class AddEigenschaftenClickHandler implements ClickHandler {
			public void onClick(ClickEvent e) {
				// wir wollen eine listbox und eine textbox
				ListBox listbox = new ListBox(false);
				TextBox infotextbox = new TextBox();
				buttonPanel.add(speicherButton);
				aktiveEigenschaftenCounter = aktiveEigenschaftenCounter + 1;
				// um sie sp�ter auszulesen, werden sie au�erhalb der methode
				// gespeichert
				eigenschaftenListboxen.add(listbox);
				infoTextboxen.add(infotextbox);
				// f�llen der listbox mit allen eigenschaften
				for (int i = 0; i < eigenschaften.size(); i++) {
					listbox.addItem(eigenschaften.elementAt(i).getErlaeuterung());
				}
				// h�bsch anordnen
				HorizontalPanel hpanel = new HorizontalPanel();
				Button abbrechenButton = new Button("abbrechen", new AbbrechenHandler(listbox, infotextbox));
				hpanel.add(listbox);
				hpanel.add(infotextbox);
				hpanel.add(abbrechenButton);
				abbrechenButton.setStylePrimaryName("grotte-Button");
				editPanel.add(hpanel);
			}
		}
		
		private class AbbrechenHandler implements ClickHandler {
			private ListBox listbox;
			private TextBox textbox;
			public AbbrechenHandler(ListBox lb, TextBox tb){
				this.listbox = lb;
				this.textbox = tb;
			}
			public void onClick(ClickEvent e){
				aktiveEigenschaftenCounter = aktiveEigenschaftenCounter - 1;
				listbox.removeFromParent();
				textbox.removeFromParent();
				Button b = (Button)e.getSource();
				b.removeFromParent();
				if(aktiveEigenschaftenCounter==0){
					speicherButton.removeFromParent();
				}
				
			}
		}
		
		
		//Callback zum Laden der Eigenschaften. Aufruf im Konstruktor
		private class GetEigenschaftenCallback implements AsyncCallback<Vector<Eigenschaft>> {
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Vector<Eigenschaft> result) {
				eigenschaften = result;
			}
		}
		
		
		//Speichern ClickHandler
		private class SpeichernClickHandler implements ClickHandler {
			public void onClick(ClickEvent e) {
				editPanel.clear();
				for (int i = 0; i < eigenschaftenListboxen.size(); i++) {
					ListBox lb = eigenschaftenListboxen.elementAt(i);
					int eigenschaftsID = 0;
					for (int o = 0; o < eigenschaften.size(); o++) {
						if (lb.getItemText(lb.getSelectedIndex()).equals(
								eigenschaften.elementAt(o).getErlaeuterung())) {
							eigenschaftsID = eigenschaften.elementAt(o).getId();
						}
					}
					Info info = new Info();
					info.setEigenschaft(eigenschaftsID);
					info.setValue(infoTextboxen.elementAt(i).getText());
					try {
						ClientSideSettings.getEditorService().insertSuchprofilInfo(
								suchprofil, info, new InsertSuchprofilInfoCallback());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				//Löschen der Zwischenspeicher
				eigenschaftenListboxen.clear();
				infoTextboxen.clear();
				speicherButton.removeFromParent();
			}
		}
		
		private class InsertSuchprofilInfoCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Object result) {
				try {
					loadSuchprofilEigenschaften();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
}
