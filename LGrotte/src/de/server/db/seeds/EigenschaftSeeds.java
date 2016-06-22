package de.server.db.seeds;

import de.server.db.AuswahlMapper;
import de.server.db.EigenschaftMapper;
import de.shared.BO.Auswahl;
import de.shared.BO.Eigenschaft;

public class EigenschaftSeeds {

	public void seedEigenschaftTable() throws Exception{
		for(int i = 0; i < erlaeuterungen.length; i++){
			Eigenschaft e = new Eigenschaft();
			e.setErlaeuterung(erlaeuterungen[i]);
			e.setAuswahl(0);
			EigenschaftMapper.eigenschaftMapper().insertEigenschaft(e);
		}
		seedEigenschaftAuswahlTable();
	}
	
	
	public void seedEigenschaftAuswahlTable() throws Exception{
		for(int i = 0; i < erlaeuterungenAuswahl.length; i++){
			Eigenschaft e = new Eigenschaft();
			e.setErlaeuterung(erlaeuterungen[i]);
			e.setAuswahl(1);
			EigenschaftMapper.eigenschaftMapper().insertEigenschaft(e);
		}
		seedAuswahlOptionen();
	}
	
	private String[]erlaeuterungen = {
		"Lieblingssport", "Lieblingsfilm", "Lieblingsessen"
	};
	
	private String[]erlaeuterungenAuswahl = {
			"Lieblingsjahreszeit", "Lieblingsmusik"
	};
	
	private void seedAuswahlOptionen() throws Exception{
		for(int i = 0; i < auswahlOptionen.length; i++){
			for(int o = 0; o < auswahlOptionen[i].length; o++){
				Auswahl a = new Auswahl();
				a.setEigenschaft_id(1+erlaeuterungen.length+i);
				a.setValue(auswahlOptionen[i][o]);
				AuswahlMapper.auswahlMapper().insertAuswahl(a);
			}
		}
	}
	
	private String[][]auswahlOptionen = {
			//Jahreszeiten
			{"Fr&uumlhling", "Sommer", "Herbst", "Winter"},
			//Lieblingsmusik
			{"HipHop", "Schlager", "Elektro", "Rock", "Pop", "Klassik"}
	};
	
}
