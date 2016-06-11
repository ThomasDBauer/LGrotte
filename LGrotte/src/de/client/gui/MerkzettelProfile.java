package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;

public class MerkzettelProfile {
	Vector<Profil> merkzettelProfileV = new Vector<Profil>();
	private Vector<String> merkzettelEmail = new Vector<String>();

	public MerkzettelProfile(Vector<String> email) throws Exception {
		merkzettelEmail = email;
		loadProfil(merkzettelEmail);
		getMerkzettelProfile();
	}

	public void loadProfil(Vector<String> email) throws Exception {
		for (int i = 0; i < email.size(); i++) {
			ClientSideSettings.getEditorService().getProfilEintraege(
					email.elementAt(i), new getProfileMerkzettelCallback());
		}
	}

	public Vector<Profil> getMerkzettelProfile() {
		return merkzettelProfileV;
	}

	public void setMerkzettelProfile(Vector<Profil> merkzettelProfile) {
		this.merkzettelProfileV = merkzettelProfile;
	}

	private class getProfileMerkzettelCallback implements AsyncCallback<Profil> {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(
					new Label(caught.toString()
							+ " @FindLove.GetProfileCallback GetPr"));
		}

		public void onSuccess(Profil result) {
			Profil p = new Profil();
			result = p;
			merkzettelProfileV.addElement(p);
		}
	}
	// CheckBox cb = new CheckBox();
	// cb.addClickHandler(new CheckProfilHandler(result.getEmail()));
	// Label name = new Label("Name: " + result.getFname() + " "
	// + result.getLname());
	// flexTable.setWidget(row, 0, cb);
	// flexTable.setWidget(row, 1, name);

	// 

}
