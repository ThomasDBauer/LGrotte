package de.server.db.seeds;

import de.server.db.ProfilinfoMapper;
import de.shared.BO.ProfilInfo;

public class ProfilInfoSeeds {

	public void seedProfilinfoTable() throws Exception{
		for(int i = 0; i < profilId.length; i++){
			ProfilInfo pi = new ProfilInfo();
			pi.setInfoID(infoId[i]);
			pi.setProfilID(profilId[i]);
			ProfilinfoMapper.profilinfoMapper()
			.insertProfilInfo(pi);
		}
	}
		
	
	private int[]profilId = {
			1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13
	};
	
	private int[]infoId = {
			1, 9, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8,
	};

}
