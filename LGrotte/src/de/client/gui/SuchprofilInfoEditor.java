package de.client.gui;

import java.util.HashMap;
import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import de.client.ClientSideSettings;
import de.shared.BO.Auswahl;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;

public class SuchprofilInfoEditor extends VerticalPanel {

	// GUI Elemente
	private VerticalPanel eigenschaftenPanel = new VerticalPanel();
	private Button addEigenschaftenButton = new Button("Eigenschaft hinzufügen");
	private Button speicherButton = new Button("Speichern", new InsertHandler());
	private Button loeschenButton = new Button("Löschen");
	private FlexTable table = new FlexTable();

	// Daten und Zwischenspeicher
	private HashMap<Eigenschaft, Widget> hashmap = new HashMap<Eigenschaft, Widget>();
	private Vector<Eigenschaft> eigenschaften = new Vector<Eigenschaft>();

	// Das Suchprofil + Setter
	private Suchprofil suchprofil;

	public void setSuchprofil(Suchprofil suchprofil) throws Exception {
		this.suchprofil = suchprofil;
		loadProfilEigenschaften();
	}

	// Konstruktor
	public SuchprofilInfoEditor() throws Exception {
		addEigenschaftenButton.setStylePrimaryName("grotte-Button");
		speicherButton.setStylePrimaryName("grotte-Button");
		loeschenButton.setStylePrimaryName("grotte-Button");
	}

	public void loadProfilEigenschaften() throws Exception {
		ClientSideSettings.getEditorService().getSuchprofilEigenschaften(suchprofil.getSuchprofilname(),
				new ProfilEigenschaftenCallback());
		this.add(speicherButton);
		this.add(eigenschaftenPanel);
		eigenschaftenPanel.add(table);
	}

	private class ProfilEigenschaftenCallback implements AsyncCallback<Vector<ProfilEigenschaft>> {
		public void onFailure(Throwable caught) {
		}

		// Baut die Eigenschafttabelle auf und speichert alles in der HashMap
		public void onSuccess(Vector<ProfilEigenschaft> result) {
			hashmap.clear();
			eigenschaften.clear();
			// Aufbau der Eigenschaften
			for (int i = 0; i < result.size(); i++) {
				Eigenschaft e = result.elementAt(i).getEigenschaft();
				eigenschaften.add(e);
				Info info = result.elementAt(i).getInfo();
				table.setWidget(i, 0, new Label(e.getErlaeuterung()));
				// Textboxen für freie Eigenschaften
				if (e.getAuswahl() == 0) {
					TextBox tb = new TextBox();
					tb.setText(info.getValue());
					table.setWidget(i, 1, tb);
					hashmap.put(e, tb);
					// ListBoxen für Auswahleigenschaften
				} else {
					ListBox lb = new ListBox();
					try {
						ClientSideSettings.getEditorService().getAuswahlForEigenschaft(e,
								new GetAuswahlCallback(lb, info.getValue()));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					table.setWidget(i, 1, lb);
					hashmap.put(e, lb);
				}
			}
		}
	}

	private class GetAuswahlCallback implements AsyncCallback<Vector<Auswahl>> {
		private ListBox lb;
		private String value;

		public GetAuswahlCallback(ListBox lb, String value) {
			this.lb = lb;
			this.value = value;
		}

		public void onFailure(Throwable caught) {
			Window.alert("GetAuswahlCallback " + caught.toString());
		}

		public void onSuccess(Vector<Auswahl> result) {
			for (int i = 0; i < result.size(); i++) {
				lb.addItem(result.elementAt(i).getValue());
			}
			if (value != null)
			for (int i = 0; i < lb.getItemCount(); i++) {
				if (lb.getItemText(i).equals(value)) {
					lb.setSelectedIndex(i);
					break;
				}
			}

		}
	}

	private class InsertHandler implements ClickHandler {
		
		private PopupPanel popup;
		
		public void onClick(ClickEvent e) {
			try {
				ClientSideSettings.getEditorService().deleteSuchprofilInfosForUser(suchprofil, new AsyncCallback() {
					public void onFailure(Throwable caught) {
						Window.alert("deleteError");
					}

					public void onSuccess(Object result) {
						for (int i = 0; i < eigenschaften.size(); i++) {
							Eigenschaft e = eigenschaften.elementAt(i);
							Info info = new Info();
							Widget w = hashmap.get(e);
							if (w instanceof TextBox) {
								info.setValue(((TextBox) w).getText());
							} else {
								info.setValue(((ListBox) w).getItemText(((ListBox) w).getSelectedIndex()));
							}
							info.setEigenschaft(e.getId());
							try {
								ClientSideSettings.getEditorService().insertSuchprofilInfo(suchprofil, info,
										new AsyncCallback() {
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
				});
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			this.popup = new PopupPanel(true, true);
			this.popup.add(new Label(
					"Suchprofilinfos wurde aktualisiert " +
					"zum Ausbelnden der Meldung ausserhalb des Feldes Clicken"));
			this.popup.center();
		}
	}
}