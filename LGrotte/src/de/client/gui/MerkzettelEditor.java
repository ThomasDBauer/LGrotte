package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;

public class MerkzettelEditor extends VerticalPanel {

	// private VerticalPanel vpanel = this;
	private FlexTable flexTable = new FlexTable();
	private Button anzeigeButton = new Button("anzeigen");
	private Button loeschButton = new Button("x");
	private VerticalPanel resultPanel = new VerticalPanel();
	private Vector<String> getProfil = new Vector<String>();
	private Vector<String> emailBuffer = new Vector<String>();
	// private Vector<Profil> gemerkteProfile = new Vector<Profil>();
	// FlexCellFormatter = table.getCellFormatter();

	private Label profilLabel = new Label("ProfilName    ");
	private Label ueWertLabel = new Label("in %   ");

	public MerkzettelEditor() throws Exception {
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.add(anzeigeButton);
		controlPanel.add(loeschButton);
		this.add(controlPanel);
		this.add(resultPanel);
		loadMerkzettel();
		MerkzettelProfile mzP = new MerkzettelProfile(getProfil);
		for (int i = 0; i < mzP.merkzettelProfileV.size(); i++) {
			Profil p = new Profil();
			CheckBox cb = new CheckBox();
			cb.addClickHandler(new CheckProfilHandler(p.getEmail()));
			Label name = new Label("Name: " + p.getFname() + " " + p.getLname());
			flexTable.setWidget(i, 0, cb);
			flexTable.setWidget(i, 1, name);
		}
		resultPanel.clear();
		flexTable.clear();
		resultPanel.add(flexTable);
		getProfil.clear();
	}

	public void loadMerkzettel() throws Exception {
		resultPanel.clear();
		flexTable.clear();
		resultPanel.add(flexTable);
		getProfil.clear();
		ClientSideSettings.getEditorService().getMerkzettelByOwner(
				new GetMerkzettelByOwnerCallback());
	}

	private class GetMerkzettelByOwnerCallback implements
			AsyncCallback<Vector<Merkzettel>> {

		public void onFailure(Throwable caught) {
			RootPanel.get("Inhalt_unten").add(
					new Label(caught.toString()
							+ " @FindLove.GetProfileCallback GetMz"));
		}

		public void onSuccess(Vector<Merkzettel> result) {
			if (result.size() == 0) {
				resultPanel.add(new Label("Keine Ergebnisse"));
			} else {

				for (int i = 0; i < result.size(); i++) {
					Merkzettel mz = result.elementAt(i);
					getProfil.addElement(mz.getGemerktesProfil());
				}

			}
		}
	}

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
			}
		}
	}
}
