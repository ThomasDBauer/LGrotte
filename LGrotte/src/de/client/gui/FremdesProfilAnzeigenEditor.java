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
		
		private Button zurueckButton = new Button("Zurueck", new ZurueckHandler());
		private Label fnameLabel = new Label();
		private Label lnameLabel = new Label();
		private Label namenLabel = new Label("Name: ");
		private HorizontalPanel name = new HorizontalPanel();
		private Label geschlechtLabel = new Label();
		private Label geschlechtLabel1 = new Label("Geschlecht: ");
		private HorizontalPanel geschlecht = new HorizontalPanel();
		private Label haarfarbeLabel = new Label();
		private Label haarfarbeLabel1 = new Label("Haarfarbe: ");
		private HorizontalPanel haarfarbe = new HorizontalPanel();
		private Label koerperLabel = new Label();
		private Label koerperLabel1 = new Label("Körpergröße: ");
		private HorizontalPanel koerpergroesse = new HorizontalPanel();
		private Label religionLabel = new Label();
		private Label religionLabel1 = new Label("Religion: ");
		private HorizontalPanel religion = new HorizontalPanel();
		private Label raucherLabel = new Label();
		private Label raucherLabel1 = new Label("Raucher: ");
		private HorizontalPanel raucher = new HorizontalPanel();
		private Label bdayLabel = new Label();
		private Label bdayLabel1 = new Label("Geburtsdatum: ");
		private HorizontalPanel bday = new HorizontalPanel();
		private Label eMail = new Label();
		private Label mail = new Label("E-Mail: ");
		private HorizontalPanel emailPanel = new HorizontalPanel();
		private Vector<HorizontalPanel> eigenschaftPanel = new Vector<HorizontalPanel>();
		
		
		public FremdesProfilAnzeigenEditor(Profil profil) throws Exception {
			this.p = profil;
			zurueckButton.setStylePrimaryName("Button-img");
			Image zurueckImage = new Image("zurueck.png");
			zurueckImage.setStylePrimaryName("Button-img-Image");
			zurueckButton.getElement().appendChild(zurueckImage.getElement());
			namenLabel.setStyleName("Fremd-Profil-Label", true);
			Label nameZus = new Label();
			nameZus.setStyleName("Fremd-Profil-Label2", true);
			geschlechtLabel1.setStyleName("Fremd-Profil-Label", true);
			geschlechtLabel.setStyleName("Fremd-Profil-Label2", true);
			geschlechtLabel.setStyleName("Frem-Profil-Label2", true);
			haarfarbeLabel1.setStyleName("Fremd-Profil-Label", true);
			haarfarbeLabel.setStyleName("Fremd-Profil-Label2", true);
			koerperLabel1.setStyleName("Fremd-Profil-Label", true);
			koerperLabel.setStyleName("Fremd-Profil-Label2", true);
			religionLabel1.setStyleName("Fremd-Profil-Label", true);
			religionLabel.setStyleName("Fremd-Profil-Label2", true);
			raucherLabel1.setStyleName("Fremd-Profil-Label", true);
			raucherLabel.setStyleName("Fremd-Profil-Label2", true);
			bdayLabel1.setStyleName("Fremd-Profil-Label", true);
			bdayLabel.setStyleName("Fremd-Profil-Label2", true);
			mail.setStyleName("Fremd-Profil-Label", true);
			eMail.setStyleName("Fremd-Profil-Label2", true);
			zurueckButton.setStyleName("Margin-Bottom", true);
			fnameLabel.setText(p.getFname());
			fnameLabel.setStyleName("Fremd-Profil-Name", true);
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
			
			buttonPanel.add(zurueckButton);
			this.add(buttonPanel);
			
			name.add(namenLabel);
			nameZus.setText(fnameLabel.getText() + lnameLabel.getText());
			name.add(nameZus);
			name.setStyleName("Fremd-Profil-Panel", true);
			this.add(name);
			
			geschlecht.add(geschlechtLabel1);
			geschlecht.add(geschlechtLabel);
			geschlecht.setStyleName("Fremd-Profil-Panel", true);
			this.add(geschlecht);
			
			haarfarbe.add(haarfarbeLabel1);
			haarfarbe.add(haarfarbeLabel);
			haarfarbe.setStyleName("Fremd-Profil-Panel", true);
			this.add(haarfarbe);
			
			koerpergroesse.add(koerperLabel1);
			koerpergroesse.add(koerperLabel);
			koerpergroesse.setStyleName("Fremd-Profil-Panel", true);
			this.add(koerpergroesse);
			
			religion.add(religionLabel1);
			religion.add(religionLabel);
			religion.setStyleName("Fremd-Profil-Panel", true);
			this.add(religion);
			
			raucher.add(raucherLabel1);
			raucher.add(raucherLabel);
			raucher.setStyleName("Fremd-Profil-Panel", true);
			this.add(raucher);
			
			bday.add(bdayLabel1);
			bday.add(bdayLabel);
			bday.setStyleName("Fremd-Profil-Panel", true);
			this.add(bday);
			
			emailPanel.add(mail);
			emailPanel.add(eMail);
			emailPanel.setStyleName("Fremd-Profil-Panel", true);
			this.add(emailPanel);
			
			
		}
		
		private class ProfilEigenschaftenCallback implements
			AsyncCallback<Vector<ProfilEigenschaft>>{
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("FremdesProfilAnzeigen."
						+ "ProfilEigenschaftenCallback " + caught.toString()));
			}
			public void onSuccess(Vector<ProfilEigenschaft> result) {
				RootPanel.get("Inhalt").add(new HTML("<h3>Eigenschaften</h3>"));
				for(int i = 0; i < result.size(); i++){
					HorizontalPanel eigenschaft = new HorizontalPanel();
					eigenschaft.add(new Label(result.elementAt(i).getName()));
					eigenschaft.getWidget(0).setStyleName("Fremd-Profil-Label",true);
					eigenschaft.add(new Label(result.elementAt(i).getWert()));
					eigenschaft.getWidget(1).setStyleName("Fremd-Profil-Label2", true);
					eigenschaft.setStyleName("Fremd-Profil-Panel", true);
					RootPanel.get("Inhalt").add(eigenschaft);
				}			}
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
				
			}
		}
		
		
		
}
