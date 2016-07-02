package de.client.gui;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

/**
 * GUI Klasse die das VerticalPanel vererbt bekommt
 * 
 * @author Enrico Popaj, Nicolai Ehrmanntraut und Lukas Kircher
 *
 * @version 1.0
 */

public class ProfilAnzeigenEditor extends VerticalPanel {
/**
 * Anlegen der GUI
 */
	private VerticalPanel vpanel = this;
	private FlexTable profilAnzeigenTable = new FlexTable();

	private Label fname = new Label();
	private Label lname = new Label();
	private Label geschlecht = new Label();
	private Label haarfarbe = new Label();
	private Label koerper = new Label();
	private Label religion = new Label();
	private Label raucher = new Label();
	private Label bday = new Label();
	private Label vorname = new Label("Vorname");
	private Label nachname = new Label ("Nachname");
	private Label geschlecht1 = new Label("Geschlecht");
	private Label haarfarbe1 = new Label("Haarfarbe");
	private Label koerpergroesse = new Label("Körpergröße");
	private Label religion1 = new Label("Religion");
	private Label raucher1 = new Label("Raucher");
	private Label geburtstag = new Label("Geburtsdatum");
	private Vector<Label> labels = new Vector<Label>();
		
		/**
		 * Konstruktor mit Labels für die Profil anzeige 
		 */
		public ProfilAnzeigenEditor() {
			try {
				ClientSideSettings.getEditorService().getProfil(
						new ProfilAnzeigenCallback());
			} catch (Exception e) {
				e.printStackTrace();
			}
			labels.addElement(fname);
			labels.addElement(vorname);
			labels.addElement(lname);
			labels.addElement(nachname);
			labels.addElement(geschlecht);
			labels.addElement(geschlecht1);
			labels.addElement(haarfarbe);
			labels.addElement(haarfarbe1);
			labels.addElement(koerper);
			labels.addElement(koerpergroesse);
			labels.addElement(religion);
			labels.addElement(religion1);
			labels.addElement(raucher);
			labels.addElement(raucher1);
			labels.addElement(bday);
			labels.addElement(geburtstag);
			
			/*
			 * Hinzufügen von Style 
			 */
			for (int i = 0; i < labels.size(); i++){
				
				labels.get(i).setStylePrimaryName("Profil-Anzeigen-Label");
			}
			
			for (int i = 0; i < labels.size(); i++){
				if(i%2 == 0){
					HorizontalPanel panel = new HorizontalPanel();
					panel.add(labels.get(i+1));
					panel.add(labels.get(i));
					profilAnzeigenTable.setWidget(i/2, 0, panel);
				}else{
				}
			}
			
			profilAnzeigenTable.addStyleName("findLove-table td");
			profilAnzeigenTable.setWidth("25em");
			
			vpanel.add(profilAnzeigenTable);
		}
		
		/**
		 * Callback um die Profildaten anzuzeigen
		 */
		private class ProfilAnzeigenCallback implements 
		AsyncCallback<Profil> {
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Profil result) {
				fname.setText(result.getFname());
				lname.setText(result.getLname());
				geschlecht.setText(result.getGeschlecht());
				haarfarbe.setText(result.getHaarfarbe());
				koerper.setText(Integer.toString(result.getKoerpergroesse()));
				religion.setText(result.getReligion());
				raucher.setText(result.getRaucher());
				bday.setText(String.valueOf(result.getGeburtsdatum()));	
			}
			
		}
		
		
		
		

}
