package de.client.gui;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

public class ProfilAnzeigenEditor extends VerticalPanel {

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

	public ProfilAnzeigenEditor(Profil result) {
		
		vpanel.add(profilAnzeigenTable);

		fname.setText(result.getFname());
		lname.setText(result.getLname());
		geschlecht.setText(result.getGeschlecht());
		haarfarbe.setText(result.getHaarfarbe());
		koerper.setText(Integer.toString(result.getKoerpergroesse()));
		religion.setText(result.getReligion());
		raucher.setText(result.getRaucher());
		bday.setText(String.valueOf(result.getGeburtsdatum()));

		profilAnzeigenTable.setWidget(0, 0, new Label("Vorname"));
		profilAnzeigenTable.setWidget(0, 1, fname);

		profilAnzeigenTable.setWidget(1, 0, new Label("Nachname"));
		profilAnzeigenTable.setWidget(1, 1, lname);

		profilAnzeigenTable.setWidget(2, 0, new Label("Geschlecht"));
		profilAnzeigenTable.setWidget(2, 1, geschlecht);

		profilAnzeigenTable.setWidget(3, 0, new Label("Haarfarbe"));
		profilAnzeigenTable.setWidget(3, 1, haarfarbe);

		profilAnzeigenTable.setWidget(4, 0, new Label("Körpergröße"));
		profilAnzeigenTable.setWidget(4, 1, koerper);

		profilAnzeigenTable.setWidget(5, 0, new Label("Religion"));
		profilAnzeigenTable.setWidget(5, 1, religion);

		profilAnzeigenTable.setWidget(6, 0, new Label("Raucher"));
		profilAnzeigenTable.setWidget(6, 1, raucher);

		profilAnzeigenTable.setWidget(7, 0, new Label("Geburtsdatum"));
		profilAnzeigenTable.setWidget(7, 1, bday);

		profilAnzeigenTable.addStyleName("findLove-table td");
		profilAnzeigenTable.setWidth("45em");

	}

}
