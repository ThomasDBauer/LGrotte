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
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

public class KontaktsperreEditor {
	private FlexTable table = new FlexTable();
	private Vector<String> emailBuffer = new Vector<String>();
	private Button merkButton = new Button("Merklzettel loeschen", new MerkHandler());
	private VerticalPanel resultPanel = new VerticalPanel();

	 FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

	public KontaktsperreEditor() throws Exception {
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.add(merkButton);
//		controlPanel.add(sperrButton);
		table.addStyleName("findLove-table");
		table.setWidth("45em");
//		this.add(controlPanel);
//		this.add(resultPanel);
		loadProfiles();
	}

	public void loadProfiles() throws Exception {
		resultPanel.clear();
		table.clear();
		resultPanel.add(table);
//		ClientSideSettings.getEditorService().getKontakt(
//				new GetMerkzettelProfileCallback());
	}

	private class GetKontaktsperreProfileCallback implements AsyncCallback<Vector<Profil>> {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(
					new Label(caught.toString()
							+ " @FindLove.GetProfileCallback"));
		}

		public void onSuccess(Vector<Profil> result) {
			if (result.size() == 0) {
				resultPanel.add(new Label("Keine Ergebnisse"));
			} else {

				for (int i = 0; i < result.size(); i++) {

					Profil p = result.elementAt(i);
					CheckBox cb = new CheckBox();
					cb.addClickHandler(new CheckProfilHandler(p.getEmail()));
					HorizontalPanel mzProfile = new HorizontalPanel();
					Label fName = new Label("Name: " + p.getFname() + " "
							+ p.getLname());
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
		
		private class getProfilEintragCallback implements AsyncCallback<Profil>{
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label ("Fehler in Callback"));
			}
			public void onSuccess(Profil result) {
				emailBuffer.add(result.getEmail());
			}
			
		}
	}

	private class MerkHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			merkButton.setEnabled(false);
			try {
				ClientSideSettings.getEditorService().deleteMerkzettel(
						emailBuffer, new deleteCallback());
			} catch (Exception e2) {
				e2.printStackTrace();
				RootPanel.get().add(new Label ("Fehler im deleteMerkzettel"));
			}
			emailBuffer.clear();
		}
		
		private class deleteCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(
						new Label(caught.toString() + " @FindLove.InsertCallback"));
			}

			public void onSuccess(Object result) {
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
}