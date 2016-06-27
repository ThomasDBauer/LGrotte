package de.server.db.seeds;

import de.server.db.ProfilinfoMapper;
import de.shared.BO.ProfilInfo;

public class ProfilInfoSeeds {

	public void seedProfilinfoTable() throws Exception{
		for(int i = 0; i < profilId.length; i++){
			ProfilInfo pi = new ProfilInfo();
			pi.setInfoID(infoId[i]);
			pi.setProfilEmail(profilId[i]);
			ProfilinfoMapper.profilinfoMapper()
			.insertProfilInfo(pi);
		}
	}
		
	
	private String[]profilId = {
			"Anna@LG", "Daniel@LG", "Daniel@LG", "Daniel@LG", "Anna@LG", 
			"Anna@LG", "Anna@LG", "Anna@LG", 
			"Anna@LG", "Lina@LG", "Lina@LG", "Lina@LG", "Lina@LG",
			"thdobauer@gmail.com", "thdobauer@gmail.com", "thdobauer@gmail.com",
			"thdobauer@gmail.com", "thdobauer@gmail.com"
	};
	
	private int[]infoId = {
			1, 9, 10, 1, 2, 3, 8, 7, 4, 5, 6, 7, 8, 11, 12, 13, 14, 15
	};

}
