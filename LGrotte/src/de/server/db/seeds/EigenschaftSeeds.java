package de.server.db.seeds;

import de.server.db.EigenschaftMapper;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Profil;

public class EigenschaftSeeds {

	public void seedEigenschaftTable(){
		for(int i = 0; i < someValues.length; i++){
//			Eigenschaft eg = new Eigenschaft(){};
//			eg.setId(someValues[i]);
//			try {
//				EigenschaftMapper.eigenschaftMapper().insertEigenschaft(eg);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
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
