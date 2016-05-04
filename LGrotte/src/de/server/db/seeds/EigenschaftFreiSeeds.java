package de.server.db.seeds;

import de.server.db.EigenschaftAuswahlMapper;
import de.server.db.EigenschaftFreiMapper;
import de.shared.BO.EigenschaftAuswahl;
import de.shared.BO.EigenschaftFrei;

public class EigenschaftFreiSeeds {
	
	public void seedEigenschaftFreiTable(){
		for(int i = 0; i < someValues.length; i++){
				EigenschaftFrei ef = new EigenschaftFrei(someValues[i]);
				ef.setId(someValues[i]);
				try {
					EigenschaftFreiMapper.eigenschaftFreiMapper().insertEigenschaftFrei(ef);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	public void seedEigenschaftFreiDelete(){
		for(int i = 0; i< someValues.length; i++){
			EigenschaftFrei ef = new EigenschaftFrei(someValues[i]);
			ef.setId(someValues[i]);
			try {
				EigenschaftFreiMapper.eigenschaftFreiMapper().deleteEigenschaftFrei(ef);
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
