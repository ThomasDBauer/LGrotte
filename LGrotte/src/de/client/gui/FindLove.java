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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

public class FindLove extends VerticalPanel {

	private FlexTable table = new FlexTable();
	private Vector<String> emailBuffer = new Vector<String>();
	private Button merkButton = new Button("Profile merken", new MerkHandler());
	private Button sperrButton = new Button("Profile sperren", new SperrHandler());
	private VerticalPanel resultPanel = new VerticalPanel();
	

	public FindLove() throws Exception {
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.add(merkButton);
		controlPanel.add(sperrButton);
		this.add(controlPanel);
		this.add(resultPanel);
		loadProfiles();
	}

	public void loadProfiles() throws Exception {
		resultPanel.clear();
		table.clear();
		resultPanel.add(table);
		ClientSideSettings.getEditorService().getProfilesForEditor(new GetProfileCallback());
	}

	private class GetProfileCallback implements AsyncCallback<Vector<Profil>> {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(new Label(caught.toString() + " @FindLove.GetProfileCallback"));
		}

		public void onSuccess(Vector<Profil> result) {
			if (result.size() == 0) {
				resultPanel.add(new Label("Keine Ergebnisse"));
			} else {
				for (int i = 0; i < result.size(); i++) {
					Profil p = result.elementAt(i);

					CheckBox cb = new CheckBox();
					cb.addClickHandler(new CheckProfilHandler(p.getEmail()));

					table.setWidget(i, 0, cb);

					table.setWidget(i, 1, new Label("Name"));
					table.setWidget(i, 2, new Label(p.getFname() + " " + p.getLname()));
					table.setWidget(i, 3, new Label("Email"));
					table.setWidget(i, 4, new Label(p.getEmail()));
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

	private class MerkHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			merkButton.setEnabled(false);
			try {
				ClientSideSettings.getEditorService().insertMerkzettel(emailBuffer, new InsertCallback());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			emailBuffer.clear();
		}
	}

	private class SperrHandler implements ClickHandler {

		public void onClick(ClickEvent e) {
			sperrButton.setEnabled(false);
			try {
				ClientSideSettings.getEditorService().insertKontaktsperren(emailBuffer, new InsertCallback());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private class InsertCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			RootPanel.get().add(new Label(caught.toString()+ " @FindLove.InsertCallback"));
		}

		public void onSuccess(Object result) {
			sperrButton.setEnabled(true);
			merkButton.setEnabled(true);
			emailBuffer.clear();
			try {
				loadProfiles();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
