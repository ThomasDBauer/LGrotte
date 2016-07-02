package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

/**
 * Darstelllung der Kontaktsperren in einem VerticalPanel
 * 
 * @author Ernico Popaj, Nicolai Ermanntraut & Lukas Kircher
 *
 * @version 1.0
 */

public class KontaktsperreEditor extends VerticalPanel {

	// Die Tabelle in der die Profile angezeigt werden
	private FlexTable table = new FlexTable();

	// Vector, zur Speicherung der Email-Adressen bei Klicken der Checkbox
	private Vector<String> emailBuffer = new Vector<String>();

	// Button zum beheben der Sperre
	private Button aufhebenButton = new Button("Kontaktsperre aufheben",
			new AufhebenHandler());

	// Panel zur Unterstuetzung des GUI
	private VerticalPanel resultPanel = new VerticalPanel();
	private HorizontalPanel controlPanel = new HorizontalPanel();

	FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

	/**
	 * Hier werden Styles vergeben und die Methode loadProfiles lädt die
	 * aktuellen Kontaktsperren in die Tabelle
	 */
	public KontaktsperreEditor() throws Exception {
		table.addStyleName("findLove-table");
		table.setWidth("45em");
		aufhebenButton.setStylePrimaryName("grotte-Button");
		aufhebenButton.setStyleName("Margin-Bottom", true);
		Image aufhebenImage = new Image("update.png");
		aufhebenImage.setStylePrimaryName("Button-img-Image");
		aufhebenButton.getElement().appendChild(aufhebenImage.getElement());
		aufhebenButton.setStylePrimaryName("Button-img");
		loadProfiles();
		this.add(controlPanel);
		this.add(resultPanel);
	}

	/**
	 * Die Panels und die Tabelle wird geleert und die Kontaktsperren werden aus
	 * der Datenbank geholt
	 */
	public void loadProfiles() throws Exception {
		resultPanel.clear();
		table.clear();
		resultPanel.add(table);
		ClientSideSettings.getEditorService().getKontaktsperrenByOwner(
				new GetKontaktsperreProfileCallback());
	}

	/**
	 * Der Callback mit einem Vector<Profil> in dem die ausgelsenen Daten in die
	 * Tabelle eingetragen werden und pro Profil eine CheckBox erstellt wird
	 * Auch der Style wird zugeteilt
	 */
	private class GetKontaktsperreProfileCallback implements
			AsyncCallback<Vector<Profil>> {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(
					new Label(caught.toString()
							+ " @FindLove.GetProfileCallback"));
		}

		public void onSuccess(Vector<Profil> result) {
			if (result.size() == 0) {
				resultPanel.add(new Label("Keine Ergebnisse"));
				aufhebenButton.removeFromParent();
			} else {
				controlPanel.add(aufhebenButton);

				for (int i = 0; i < result.size(); i++) {

					Profil p = result.elementAt(i);
					CheckBox cb = new CheckBox();
					cb.addClickHandler(new CheckProfilHandler(p.getEmail()));
					HorizontalPanel mzProfile = new HorizontalPanel();
					Label fName = new Label(p.getFname() + " " + p.getLname());
					fName.addStyleName("findLove-Label");
					mzProfile.add(fName);

					mzProfile.addStyleName("findLove-HPaneltop");
					mzProfile.addStyleName("findLove-Panel");
					table.setWidget(i, 0, cb);
					table.setWidget(i, 1, mzProfile);
				}
			}
		}
	}

	/**
	 * Beim klicken der CheckBox wird die Email in den Vector eingetragen, beim
	 * erneuten Klicken(das Kreuz verschwindet wieder) wird die Email aus dem
	 * Vector entfernt
	 */
	private class CheckProfilHandler implements ClickHandler {
		private String userEmail;

		public CheckProfilHandler(String email) {
			this.userEmail = email;
		}

		public void onClick(ClickEvent e) {
			CheckBox cb = (CheckBox) e.getSource();
			if (!cb.getValue()) {
				emailBuffer.remove(userEmail);
			} else {
				emailBuffer.add(userEmail);
				try {

				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	/**
	 * ClickHandler, der die im EmailBuffer(Vector) stehenden Emails
	 * aus den Kontaktsperren löschen und anschließend den Vector EmailBuffer
	 * clearen sowie die aktuellen Kontaktsperren lädt
	 */
	private class AufhebenHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			aufhebenButton.setEnabled(false);
			try {
				ClientSideSettings.getEditorService().deleteKontaktsperre(
						emailBuffer, new DeleteCallback());
			} catch (Exception e2) {
				e2.printStackTrace();
				RootPanel.get().add(new Label("Fehler im deleteMerkzettel"));
			}
			emailBuffer.clear();
		}
		
		/**
		 * Callback, der die im EmailBuffer(Vector) stehenden Emails
		 * aus den Kontaktsperren löschen und anschließend den Vector EmailBuffer
		 * clearen sowie die aktuellen Kontaktsperren lädt
		 */
		private class DeleteCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(
						new Label(caught.toString()
								+ " @FindLove.InsertCallback"));
			}

			public void onSuccess(Object result) {
				aufhebenButton.setEnabled(true);
				emailBuffer.clear();
				try {
					loadProfiles();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}
}
