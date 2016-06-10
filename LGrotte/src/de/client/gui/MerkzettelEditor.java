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
	private boolean asyncBack = false;
	private int row;

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
	}

	public void loadMerkzettel() throws Exception {
		// resultPanel.clear();
		// flexTable.clear();
		// resultPanel.add(flexTable);
		// getProfil.clear();
		// loadProfil();
		ClientSideSettings.getEditorService().getMerkzettelByOwner(
				new GetMerkzettelByOwnerCallback());
		
		if(getProfil.size() == row){
			loadProfil();
		}
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
					row = i;
					getProfil.addElement(mz.getGemerktesProfil());
				}
				
				asyncBack = true;
			}
		}
	}

	public void loadProfil() throws Exception {
		resultPanel.clear();
		flexTable.clear();
		resultPanel.add(flexTable);
		getProfil.clear();
		for (int i = 0; i < getProfil.size(); i++) {
			String mail = getProfil.elementAt(i);
			ClientSideSettings.getEditorService().getProfilEintraege(mail,
					new getProfileMerkzettelCallback());
		}
	}

	private class getProfileMerkzettelCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(
					new Label(caught.toString()
							+ " @FindLove.GetProfileCallback GetPr"));
		}

		public void onSuccess(Profil result) {
			CheckBox cb = new CheckBox();
			cb.addClickHandler(new CheckProfilHandler(result.getEmail()));
			Label name = new Label("Name: " + result.getFname() + " "
					+ result.getLname());
			flexTable.setWidget(row, 0, cb);
			flexTable.setWidget(row, 1, name);
		}

		// try {
		// ClientSideSettings.getEditorService().getProfilEintraege(
		// mz.getGemerktesProfil(),
		// new getProfileMerkzettelCallback());
		// } catch (Exception e) {
		// e.printStackTrace();
		// RootPanel.get("Inhalt_unten").add(new
		// Label("GetProilEintraeg Fail"));
		// }
		// private class getProfileMerkzettelCallback implements
		// AsyncCallback<Profil> {
		//
		// public void onFailure(Throwable caught) {
		// RootPanel.get().add(
		// new Label(caught.toString()
		// + " @FindLove.GetProfileCallback GetPr"));
		// }
		//
		// public void onSuccess(Profil result) {
		// CheckBox cb = new CheckBox();
		// cb.addClickHandler(new CheckProfilHandler(result.getEmail()));
		// Label name = new Label("Name: " + result.getFname() + " "
		// + result.getLname());
		// flexTable.setWidget(row, 0, cb);
		// flexTable.setWidget(row, 1, name);
		// }

		// }

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

}
