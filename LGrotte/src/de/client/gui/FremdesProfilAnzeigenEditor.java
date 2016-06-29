package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
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
		private Label namenLabel = new Label("Name");
		private HorizontalPanel name = new HorizontalPanel();
		private Label geschlechtLabel = new Label();
		private Label geschlechtLabel1 = new Label("Geschlecht");
		private HorizontalPanel geschlecht = new HorizontalPanel();
		private Label haarfarbeLabel = new Label();
		private Label haarfarbeLabel1 = new Label("Haarfarbe");
		private HorizontalPanel haarfarbe = new HorizontalPanel();
		private Label koerperLabel = new Label();
		private Label koerperLabel1 = new Label("Körpergröße");
		private HorizontalPanel koerpergroesse = new HorizontalPanel();
		private Label religionLabel = new Label();
		private Label religionLabel1 = new Label("Religion");
		private HorizontalPanel religion = new HorizontalPanel();
		private Label raucherLabel = new Label();
		private Label raucherLabel1 = new Label("Raucher");
		private HorizontalPanel raucher = new HorizontalPanel();
		private Label bdayLabel = new Label();
		private Label bdayLabel1 = new Label("Geburtsdatum");
		private HorizontalPanel bday = new HorizontalPanel();
		private Label eMail = new Label();
		private Label mail = new Label("E-Mail");
		private HorizontalPanel emailPanel = new HorizontalPanel();
		private Vector<HorizontalPanel> eigenschaftPanel = new Vector<HorizontalPanel>();
		
		
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
			merkButton.setStyleName("Margin-Bottom", true);
			eMail.setStyleName("Eigenschaft-Border", true);
			mail.setStyleName("Eigenschaft-Border", true);
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
			
			
//			profilAnzeigenTable.addStyleName("findLove-table td");
			
			ClientSideSettings.getEditorService().getProfilEigenschaften(
					profil.getEmail(), new ProfilEigenschaftenCallback());
			
			buttonPanel.add(merkButton);
			buttonPanel.add(sperrButton);
			buttonPanel.add(zurueckButton);
			this.add(buttonPanel);
			
			name.add(namenLabel);
			name.add(fnameLabel);
			name.add(lnameLabel);
			this.add(name);
			
			geschlecht.add(geschlechtLabel1);
			geschlecht.add(geschlechtLabel);
			this.add(geschlecht);
			
			haarfarbe.add(haarfarbeLabel1);
			haarfarbe.add(haarfarbeLabel);
			this.add(haarfarbe);
			
			koerpergroesse.add(koerperLabel1);
			koerpergroesse.add(koerperLabel);
			this.add(koerpergroesse);
			
			religion.add(religionLabel1);
			religion.add(religionLabel);
			this.add(religion);
			
			raucher.add(raucherLabel1);
			raucher.add(raucherLabel);
			this.add(raucher);
			
			bday.add(bdayLabel1);
			bday.add(bdayLabel);
			this.add(bday);
			
			emailPanel.add(mail);
			emailPanel.add(eMail);
			this.add(emailPanel);
			
			
			for(int i = 0; i < eigenschaftPanel.size(); i++){
				this.add(eigenschaftPanel.elementAt(i).getParent());
			}
			
		}
		
		private class ProfilEigenschaftenCallback implements
			AsyncCallback<Vector<ProfilEigenschaft>>{
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("FremdesProfilAnzeigen."
						+ "ProfilEigenschaftenCallback " + caught.toString()));
			}
			public void onSuccess(Vector<ProfilEigenschaft> result) {
				Label eigenschaften = new Label("Eigenschaften");
				eigenschaften.setStyleName("Margin-Top", true);
				profilAnzeigenTable.setWidget(9, 0, eigenschaften);
				for(int i = 0; i < result.size(); i++){
					
					Label wert = new Label (result.elementAt(i).getWert());
					Label name = new Label (result.elementAt(i).getName());
					HorizontalPanel eigenschaft = new HorizontalPanel();
					eigenschaft.add(name);
					eigenschaft.add(wert);
					eigenschaftPanel.add(eigenschaft);
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
