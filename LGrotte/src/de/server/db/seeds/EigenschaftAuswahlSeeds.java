package de.server.db.seeds;

import de.server.db.EigenschaftAuswahlMapper;
import de.shared.BO.EigenschaftAuswahl;
import de.shared.BO.Profil;

public class EigenschaftAuswahlSeeds {

	
	public void seedEigenschaftAuswahlTable(){
		for(int i = 0; i < someValues.length; i++){
			EigenschaftAuswahl ea = new EigenschaftAuswahl(someValues[i]);
			ea.setId(someValues[i]);
			try {
				EigenschaftAuswahlMapper.eigenschaftAuswahlMapper().insertEigenschaftAuswahl(ea);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void seedEigenschaftAuswahlDelete(){
		for(int i = 0; i< someValues.length;i++){
			EigenschaftAuswahl ea = new EigenschaftAuswahl(someValues[i]);
			ea.setId(someValues[i]);
			try {
				EigenschaftAuswahlMapper.eigenschaftAuswahlMapper().deleteEigenschaftAuswahl(ea);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private int[]someValues = {
		5, 6, 7, 12, 54, 2, 98, 1, 54, 90
	};
	
	private String[]someOtherValues = {
		"Thomas", "Hanna", "Dieter", "Gerd", "Mike",
		"Kerstin", "Anna", "Peter", "Martin", "Tim"	
	};

}
