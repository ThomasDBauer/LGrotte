package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.javascript.jscomp.Result;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.mysql.fabric.xmlrpc.Client;

import de.client.ClientSideSettings;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.RO.ProfilEigenschaft;

public class ProfilEigenschaftEditor extends VerticalPanel {

	private VerticalPanel editPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private Button addEigenschaftenButton = new Button("+", new AddEigenschaftenClickHandler());
	private Button speicherButton = new Button("Speichern", new SpeicherClickHandler());
	private Vector<ListBox> eigenschaftenListboxen = new Vector<ListBox>();
	private Vector<TextBox> eigenschaftenTextboxen = new Vector<TextBox>();
	private Vector<Eigenschaft> eigenschaften;
	private VerticalPanel eigenschaftenPanel = new VerticalPanel();

	public ProfilEigenschaftEditor() throws Exception {
		buttonPanel.add(addEigenschaftenButton);
		this.add(buttonPanel);
		this.add(editPanel);
		this.add(eigenschaftenPanel);
		ClientSideSettings.getEditorService().getEigenschaften(new GetEigenschaftenCallback());
		loadProfilEigenschaften();
	}

	public void loadProfilEigenschaften() throws Exception {
		eigenschaftenPanel.clear();
		ClientSideSettings.getEditorService().getProfilEigenschaften(new LoadProfileCallback());
	}

	private class LoadProfileCallback implements AsyncCallback<Vector<ProfilEigenschaft>> {
		public void onFailure(Throwable caught) {
			eigenschaftenPanel.add(new Label("LoadProfileCallback " + caught.toString()));

		}

		public void onSuccess(Vector<ProfilEigenschaft> result) {
			if (result.size() == 0)
				eigenschaftenPanel.add(new Label("Erzähle etwas über ich!"));

			FlexTable table = new FlexTable();

			for (int i = 0; i < result.size(); i++) {

				table.setWidget(i, 0, new Label(result.elementAt(i).getName() + ": "));
				table.setWidget(i, 1, new Label(result.elementAt(i).getWert()));
				table.setWidget(i, 2, new Button("X", new LoeschenClickHandler(
						result.elementAt(i).getInfo())));
						//result.elementAt(i).getName(), result.elementAt(i).getWert())));
			}
			eigenschaftenPanel.add(table);
		}
	}
	
	private class LoeschenClickHandler implements ClickHandler {
		
		private Info info;
		
		public LoeschenClickHandler(Info info){
			this.info = info;
		}
		
		public void onClick(ClickEvent e){
			try {
				ClientSideSettings.getEditorService().deleteInfo(info, new AsyncCallback(){
					public void onFailure(Throwable caught) {
					}
					public void onSuccess(Object result) {
					}
				});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	private class AddEigenschaftenClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			// wir wollen eine listbox und eine textbox
			ListBox listbox = new ListBox(false);
			TextBox infotextbox = new TextBox();
			buttonPanel.add(speicherButton);

			// um sie spï¿½ter auszulesen, werden sie auï¿½erhalb der methode
			// gespeichert
			eigenschaftenListboxen.add(listbox);
			eigenschaftenTextboxen.add(infotextbox);

			// fï¿½llen der listbox mit allen eigenschaften
			for (int i = 0; i < eigenschaften.size(); i++) {
				listbox.addItem(eigenschaften.elementAt(i).getErlaeuterung());
			}

			// hï¿½bsch anordnen
			HorizontalPanel hpanel = new HorizontalPanel();
			hpanel.add(listbox);
			hpanel.add(infotextbox);
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
					ClientSideSettings.getEditorService().insertProfilInfo(info, new InsertInfoCallback());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

//	public class LoeschenClickHandler implements ClickHandler {
//		public void onClick(ClickEvent event) {
//			for (int i = 0; i < eigenschaftenTextboxen.size(); i++) {
//				Info info = new Info();
//				info.setValue(eigenschaftenTextboxen.elementAt(i).getText());
//				try {
//					ClientSideSettings.getEditorService().deleteInfo(info, new DeleteInfoCallback());
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			}
//		}
//
//	}

	private class InsertInfoCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Object result) {
		}
	}

}
