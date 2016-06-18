package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.javascript.jscomp.Result;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
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
import de.shared.RO.ProfilEigenschaft;

public class ProfilEigenschaftEditor extends VerticalPanel {

	// GUI Elemente
	private VerticalPanel editPanel = new VerticalPanel();
	private HorizontalPanel buttonPanel = new HorizontalPanel();
	private VerticalPanel eigenschaftenPanel = new VerticalPanel();
	private Button addEigenschaftenButton = new Button("Eigenschaft hinzuf�gen", new AddEigenschaftenClickHandler());
	private Button speicherButton = new Button("Speichern", new SpeicherClickHandler());
	private Button loeschenButton = new Button("L�schen", new DeleteInfosHandler());

	// Daten und Zwischenspeicher
	private Vector<ListBox> eigenschaftenListboxen = new Vector<ListBox>();
	private Vector<TextBox> eigenschaftenTextboxen = new Vector<TextBox>();
	private Vector<Eigenschaft> eigenschaften;
	private Vector<Info> infosToDelete = new Vector<Info>();
	private int aktiveEigenschaftenCounter = 0;

	// Konstruktor
	public ProfilEigenschaftEditor() throws Exception {
		addEigenschaftenButton.setStylePrimaryName("grotte-Button");
		speicherButton.setStylePrimaryName("grotte-Button");
		loeschenButton.setStylePrimaryName("grotte-Button");
		buttonPanel.add(addEigenschaftenButton);
		this.add(buttonPanel);
		this.add(editPanel);
		this.add(eigenschaftenPanel);
		ClientSideSettings.getEditorService().getEigenschaften(new GetEigenschaftenCallback());
		loadProfilEigenschaften();
	}

	// Eigenschaften laden +
	public void loadProfilEigenschaften() throws Exception {
		eigenschaftenPanel.clear();
		ClientSideSettings.getEditorService().getProfilEigenschaften(new LoadProfileCallback());
	}

	// + Callback
	private class LoadProfileCallback implements AsyncCallback<Vector<ProfilEigenschaft>> {
		public void onFailure(Throwable caught) {
			eigenschaftenPanel.add(new Label("LoadProfileCallback " + caught.toString()));
		}

		public void onSuccess(Vector<ProfilEigenschaft> result) {
			if (result.size() == 0)
				eigenschaftenPanel.add(new Label("Erz�hle etwas �ber ich!"));
			FlexTable table = new FlexTable();
			for (int i = 0; i < result.size(); i++) {
				table.setWidget(i, 0, new Label(result.elementAt(i).getName() + ": "));
				table.setWidget(i, 1, new Label(result.elementAt(i).getWert()));
				CheckBox cb = new CheckBox();
				cb.addClickHandler(new AddToDeleteHandler(result.elementAt(i).getInfo()));
				table.setWidget(i, 2, cb);
			}
			eigenschaftenPanel.clear();
			eigenschaftenPanel.add(table);
		}
	}

	// Add To Delete ClickHandler
	private class AddToDeleteHandler implements ClickHandler {
		private Info info;
		public AddToDeleteHandler(Info info) {
			this.info = info;
		}
		public void onClick(ClickEvent e) {
			CheckBox cb = (CheckBox) e.getSource();
			if (cb.getValue()) {
				infosToDelete.add(info);
			} else {
				infosToDelete.remove(info);
			}
			if(infosToDelete.size()!=0){
				buttonPanel.add(loeschenButton);
			}else{
				loeschenButton.removeFromParent();
			}
		}
	}
	
	// Delete Handler
	private class DeleteInfosHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			for (int i = 0; i < infosToDelete.size(); i++) {
				try {
					ClientSideSettings.getEditorService().deleteInfo(infosToDelete.elementAt(i), 
							new AsyncCallback() {
						public void onFailure(Throwable caught) {
						}

						public void onSuccess(Object result) {
							loeschenButton.removeFromParent();
							infosToDelete.clear();
							try {
								loadProfilEigenschaften();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

	// Hinzuf�gen ClickHandler
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
			eigenschaftenTextboxen.add(infotextbox);
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
			editPanel.add(hpanel);
		}
	}

	// Wenn doch keine Eigenschaften eingegeben werden.
	private class AbbrechenHandler implements ClickHandler {
		private ListBox listbox;
		private TextBox textbox;

		public AbbrechenHandler(ListBox lb, TextBox tb) {
			this.listbox = lb;
			this.textbox = tb;
		}

		public void onClick(ClickEvent e) {
			aktiveEigenschaftenCounter = aktiveEigenschaftenCounter - 1;
			listbox.removeFromParent();
			textbox.removeFromParent();
			Button b = (Button) e.getSource();
			b.removeFromParent();
			if(aktiveEigenschaftenCounter==0){
				speicherButton.removeFromParent();
			}
		}
	}

	// Callback zum Laden der Eigenschaften. Aufruf im Konstruktor
	private class GetEigenschaftenCallback implements AsyncCallback<Vector<Eigenschaft>> {
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Vector<Eigenschaft> result) {
			eigenschaften = result;
		}
	}

	// Speichern ClickHandler
	private class SpeicherClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			editPanel.clear();
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
			// L�schen der Zwischenspeicher
			eigenschaftenListboxen.clear();
			eigenschaftenTextboxen.clear();
			speicherButton.removeFromParent();
			aktiveEigenschaftenCounter = 0;
		}
	}

	// Speichern Callback - tut nix, au�er das GUI neu laden
	private class InsertInfoCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Object result) {
			try {
				loadProfilEigenschaften();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
