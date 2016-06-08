package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.javascript.jscomp.Result;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;

public class ProfilEigenschaftEditor extends VerticalPanel {

	private VerticalPanel editPanel = new VerticalPanel();
	private Button addEigenschaftenButton = new Button("+", new AddEigenschaftenClickHandler());
	private Button speicherButton = new Button("Speichern", new SpeicherClickHandler());
	private Vector<ListBox> eigenschaftenListboxen = new Vector<ListBox>();
	private Vector<TextBox> eigenschaftenTextboxen = new Vector<TextBox>();
	private Vector<Eigenschaft> eigenschaften;
	private Vector<Info> infos;
 

	public ProfilEigenschaftEditor() throws Exception {
		this.add(addEigenschaftenButton);
		this.add(editPanel);
		this.add(speicherButton);
		ClientSideSettings.getEditorService().getEigenschaften(new GetEigenschaftenCallback());

	}

	private class AddEigenschaftenClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			// wir wollen eine listbox und eine textbox
			ListBox listbox = new ListBox(false);
			TextBox infotextbox = new TextBox();
			Button loeschenButton = new Button("x", new LoeschenClickHandler());

			// um sie sp�ter auszulesen, werden sie au�erhalb der methode
			// gespeichert
			eigenschaftenListboxen.add(listbox);
			eigenschaftenTextboxen.add(infotextbox);

			// f�llen der listbox mit allen eigenschaften
			for (int i = 0; i < eigenschaften.size(); i++) {
				listbox.addItem(eigenschaften.elementAt(i).getErlaeuterung());
			}

			// h�bsch anordnen
			HorizontalPanel hpanel = new HorizontalPanel();
			hpanel.add(listbox);
			hpanel.add(infotextbox);
			hpanel.add(loeschenButton);
			editPanel.add(hpanel);
		}
	}

	private class GetEigenschaftenCallback implements AsyncCallback<Vector<Eigenschaft>> {
		public void onFailure(Throwable caught) {

		}

		public void onSuccess(Vector<Eigenschaft> result) {
			eigenschaften = result;
		}
	}
	
	private class GetInfosCallback implements AsyncCallback<Vector<Info>> {
		public void onFailure(Throwable caught) {	
		}
		public void onSuccess(Vector<Info> result) {
			infos = result;
		}
	}

	private class SpeicherClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			for (int i = 0; i < eigenschaftenListboxen.size(); i++) {
				ListBox lb = eigenschaftenListboxen.elementAt(i);
				int eigenschaftsID = 0;
				for (int o = 0; o < eigenschaften.size(); o++) {
					if (lb.getItemText(lb.getSelectedIndex()).equals(eigenschaften.elementAt(o).getErlaeuterung())) {
						eigenschaftsID = eigenschaften.elementAt(o).getId();
					}
				}
				Info info = new Info();
				info.setEigenschaft(eigenschaftsID);
				info.setValue(eigenschaftenTextboxen.elementAt(i).getText());
				try {
					ClientSideSettings.getEditorService().insertInfo(info, new InsertInfoCallback());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
//				for (int pit = 0; pit < eigenschaftenTextboxen.size(); pit++) {
//					// Schleife um zu erkennen um welche info es sich handelt
//					TextBox tb = eigenschaftenTextboxen.elementAt(pit);
//					int infoID = 0;
//					for (int pi = 0; pi < infos.size(); pi++){
//						if(tb.getText().equals(infos.elementAt(pi).getValue())){
//							infoID = infos.elementAt(pi).getId();
//						}
//					}
//					// Info Id in Tabelle "ProfilInfo" kicken
//					ProfilInfo pi = new ProfilInfo();
//					Profil profilEmail = new Profil();
//					pi.setInfoID(infoID);
//					pi.setProfilEmail(profilEmail.getEmail());
//					try {
//						ClientSideSettings.getEditorService().insertProfilInfo(pi, new InsertProfilInfoCallback());
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
			}
		}
	}

	public class LoeschenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
		//Heir kommt die Deletedunktion die anhand der im TextFeld eingegebenen eigenschaft NICHT anhand der ID	
			
		}
		
	}
	private class DeleteInfoCallback implements AsyncCallback{
		public void onFailure(Throwable caught) {
			
		}
		public void onSuccess(Object result) {

		}
		
	}
	
	private class InsertProfilInfoCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {	
		}
		public void onSuccess(Object result) {	
		}		
	}
	
	private class InsertInfoCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
		}
		public void onSuccess(Object result) {
		}
	}
}


