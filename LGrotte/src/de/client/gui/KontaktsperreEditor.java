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

public class KontaktsperreEditor extends VerticalPanel {

	private VerticalPanel thisPanel = new VerticalPanel();
	private FlexTable table = new FlexTable();
	private Vector<String> emailBuffer = new Vector<String>();
	private Button merkButton = new Button("Kontaktsperre aufheben", new AufhebenHandler());
	private VerticalPanel resultPanel = new VerticalPanel();

	FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

	public KontaktsperreEditor() throws Exception {
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.add(merkButton);
		table.addStyleName("findLove-table");
		table.setWidth("45em");
		loadProfiles();
	}

	public void loadProfiles() throws Exception {
		resultPanel.clear();
		table.clear();
		resultPanel.add(table);
		ClientSideSettings.getEditorService().getKontaktsperrenByOwner(new GetKontaktsperreProfileCallback());
	}

	private class GetKontaktsperreProfileCallback implements AsyncCallback<Vector<Profil>> {

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
					HorizontalPanel mzProfile = new HorizontalPanel();
					Label fName = new Label("Name: " + p.getFname() + " " + p.getLname());
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
	}

	private class AufhebenHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			merkButton.setEnabled(false);
			try {
				ClientSideSettings.getEditorService().deleteMerkzettel(
						emailBuffer, new DeleteCallback());
			} catch (Exception e2) {
				e2.printStackTrace();
				RootPanel.get().add(new Label ("Fehler im deleteMerkzettel"));
			}
			emailBuffer.clear();
		}
		
		private class DeleteCallback implements AsyncCallback {
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
