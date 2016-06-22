package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;
import de.shared.RO.ProfilEigenschaft;

public class FremdesProfilAnzeigenEditor extends VerticalPanel{
	
		
		private Profil p;
	
		private HorizontalPanel buttonPanel = new HorizontalPanel();
		private FlexTable profilAnzeigenTable = new FlexTable();
		
		private Button merkButton = new Button("Profil merken", new MerkHandler());
		private Button sperrButton = new Button("Profil sperren", new SperrHandler());
		private Button zurueckButton = new Button("Zurueck", new ZurueckHandler());
		private Label fnameLabel = new Label();
		private Label lnameLabel = new Label();
		private Label geschlechtLabel = new Label();
		private Label haarfarbeLabel = new Label();
		private Label koerperLabel = new Label();
		private Label religionLabel = new Label();
		private Label raucherLabel = new Label();
		private Label bdayLabel = new Label();
		private Label eMail = new Label();
		
		
		public FremdesProfilAnzeigenEditor(Profil profil) throws Exception {
			this.p = profil;
			merkButton.setStylePrimaryName("Button-img");
			sperrButton.setStylePrimaryName("Button-img");
			zurueckButton.setStylePrimaryName("Button-img");
			Image merkImage = new Image("merken.png"); 
			Image sperrImage = new Image("sperren.png");
			Image zurueckImage = new Image("zurueck.png");
			merkImage.setStylePrimaryName("Button-img-Image");
			sperrImage.setStylePrimaryName("Button-img-Image");
			zurueckImage.setStylePrimaryName("Button-img-Image");
			merkButton.getElement().appendChild(merkImage.getElement());
			sperrButton.getElement().appendChild(sperrImage.getElement());
			zurueckButton.getElement().appendChild(zurueckImage.getElement());
			fnameLabel.setText(p.getFname());
			lnameLabel.setText(p.getLname());
			geschlechtLabel.setText(p.getGeschlecht());
			haarfarbeLabel.setText(p.getHaarfarbe());
			koerperLabel.setText(Integer.toString(p.getKoerpergroesse()));
			religionLabel.setText(p.getReligion());
			raucherLabel.setText(p.getRaucher());
			bdayLabel.setText(String.valueOf(p.getGeburtsdatum()));	
			eMail.setText(p.getEmail());
			
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
			
			profilAnzeigenTable.setWidget(9, 0, new Label("E-Mail"));
			profilAnzeigenTable.setWidget(9, 1, eMail);
			
			profilAnzeigenTable.addStyleName("findLove-table td");
			profilAnzeigenTable.setWidth("45em");
			
			ClientSideSettings.getEditorService().getProfilEigenschaften(
					profil.getEmail(), new ProfilEigenschaftenCallback());
			
			buttonPanel.add(merkButton);
			buttonPanel.add(sperrButton);
			buttonPanel.add(zurueckButton);
			this.add(profilAnzeigenTable);
		}
		
		private class ProfilEigenschaftenCallback implements
			AsyncCallback<Vector<ProfilEigenschaft>>{
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("FremdesProfilAnzeigen."
						+ "ProfilEigenschaftenCallback " + caught.toString()));
			}
			public void onSuccess(Vector<ProfilEigenschaft> result) {
				profilAnzeigenTable.setWidget(10, 0, new Label("------------------------------"));
				profilAnzeigenTable.setWidget(11, 0, new Label("Eigenschaften"));
				for(int i = 0; i < result.size(); i++){
					profilAnzeigenTable.setWidget(11+i, 0, new Label(
							result.elementAt(i).getName()));
					profilAnzeigenTable.setWidget(11+i, 1, new Label(
							result.elementAt(i).getWert()));
				}
			}
		}
		
		private class MerkHandler implements ClickHandler {
			public void onClick(ClickEvent e) {
				merkButton.setEnabled(false);
				try {
					ClientSideSettings.getEditorService().insertMerkzettel(
							p.getEmail(), new InsertCallback());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		private class SperrHandler implements ClickHandler {

			public void onClick(ClickEvent e) {
				sperrButton.setEnabled(false);
				try {
					ClientSideSettings.getEditorService().insertKontaktsperre(
							p.getEmail(), new InsertCallback());
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		private class ZurueckHandler implements ClickHandler {
			public void onClick (ClickEvent e) {
				RootPanel.get("Zusatz").clear();
				RootPanel.get("Inhalt").clear();
				RootPanel.get("Content").clear();
				RootPanel.get("Navi-Pop").clear();
				RootPanel.get("Inhalt").add(
						new HTML("<h2>Deine Partnervorschlaege</h2>"));
				try {
					RootPanel.get("Inhalt").add(new FindLove());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		private class InsertCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(
						new Label(caught.toString() + " @FindLove.InsertCallback"));
			}

			public void onSuccess(Object result) {
				sperrButton.setEnabled(true);
				merkButton.setEnabled(true);
			}
		}
		
		
		
}
