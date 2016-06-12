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

public class FremdesProfilAnzeigenEditor extends VerticalPanel{
	
		private VerticalPanel vpanel = this;
		private HorizontalPanel buttonPanel = new HorizontalPanel();
		private Vector<String> emailBuffer = new Vector<String>();
		private FlexTable profilAnzeigenTable = new FlexTable();
		
		private Button merkenButton = new Button("Profil merken");
		private Button sperrenButton = new Button("Profil sperren");
		private Label fnameLabel = new Label();
		private Label lnameLabel = new Label();
		private Label geschlechtLabel = new Label();
		private Label haarfarbeLabel = new Label();
		private Label koerperLabel = new Label();
		private Label religionLabel = new Label();
		private Label raucherLabel = new Label();
		private Label bdayLabel = new Label();
		
		public FremdesProfilAnzeigenEditor() {
			
			try {
				ClientSideSettings.getEditorService().getProfilesForEditor(
						new ProfilAnzeigenCallback());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			profilAnzeigenTable.setWidget(0, 0, buttonPanel);
			
			profilAnzeigenTable.setWidget(1, 0, new Label("Vorname"));
			profilAnzeigenTable.setWidget(1, 1, fnameLabel);
			
			profilAnzeigenTable.setWidget(2, 0, new Label("Nachname"));
			profilAnzeigenTable.setWidget(2, 1, lnameLabel);
			
			profilAnzeigenTable.setWidget(3, 0, new Label("Geschlecht"));
			profilAnzeigenTable.setWidget(3, 1, geschlechtLabel);
			
			profilAnzeigenTable.setWidget(4, 0, new Label("Haarfarbe"));
			profilAnzeigenTable.setWidget(4, 1, haarfarbeLabel);
			
			profilAnzeigenTable.setWidget(5, 0, new Label("Körpergröße"));
			profilAnzeigenTable.setWidget(5, 1, koerperLabel);
			
			profilAnzeigenTable.setWidget(6, 0, new Label("Religion"));
			profilAnzeigenTable.setWidget(6, 1, religionLabel);
			
			profilAnzeigenTable.setWidget(7, 0, new Label("Raucher"));
			profilAnzeigenTable.setWidget(7, 1, raucherLabel);
			
			profilAnzeigenTable.setWidget(8, 0, new Label("Geburtsdatum"));
			profilAnzeigenTable.setWidget(8, 1, bdayLabel);
			
			profilAnzeigenTable.addStyleName("findLove-table td");
			profilAnzeigenTable.setWidth("45em");
			
			buttonPanel.add(merkenButton);
			buttonPanel.add(sperrenButton);
			this.add(profilAnzeigenTable);
		}
		
		private class ProfilAnzeigenCallback implements AsyncCallback<Vector<Profil>> {
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Vector<Profil> result) {
				for (int i = 0; i < result.size(); i++) {

					Profil p = result.elementAt(i);

					CheckBox cb = new CheckBox();
					cb.addClickHandler(new CheckProfilHandler(p.getEmail()));
					fnameLabel.setText(p.getFname());
					lnameLabel.setText(p.getLname());
					geschlechtLabel.setText(p.getGeschlecht());
					haarfarbeLabel.setText(p.getHaarfarbe());
					koerperLabel.setText(Integer.toString(p.getKoerpergroesse()));
					religionLabel.setText(p.getReligion());
					raucherLabel.setText(p.getRaucher());
					bdayLabel.setText(String.valueOf(p.getGeburtsdatum()));	
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
				merkenButton.setEnabled(false);
				try {
					ClientSideSettings.getEditorService().insertMerkzettel(
							emailBuffer, new InsertCallback());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				emailBuffer.clear();
			}
		}
		
		private class SperrHandler implements ClickHandler {

			public void onClick(ClickEvent e) {
				sperrenButton.setEnabled(false);
				try {
					ClientSideSettings.getEditorService().insertKontaktsperren(
							emailBuffer, new InsertCallback());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		private class InsertCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(
						new Label(caught.toString() + " @FindLove.InsertCallback"));
			}

			public void onSuccess(Object result) {
				sperrenButton.setEnabled(true);
				merkenButton.setEnabled(true);
				emailBuffer.clear();
			}
		}
		
		
		
}
