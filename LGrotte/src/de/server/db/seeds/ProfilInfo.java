package de.server.db.seeds;

import de.server.db.ProfilinfoMapper;

public class ProfilInfo {

	private void seedProfilInfoTable() throws Exception{
		for(int i = 0; i < someValues.length; i++){
			 ProfilInfo pi = new ProfilInfo();
//						ProfilinfoMapper.profilinfoMapper().insertProfilinfo(pi);
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
