package de.server.db.seeds;

import de.server.db.InfoMapper;
import de.shared.BO.Info;

public class InfoSeeds {

	public void seedInfoTable() throws Exception{
		for(int i = 0; i < eigenschaften.length; i++){
			Info info = new Info();
			info.setEigenschaft(eigenschaften[i]);
			info.setValue(values[i]);
			InfoMapper.infoMapper().insertInfo(info);
		}
	}
	
	private String[]values = {
		"Fußball", "Fussball", "Schwimmen",
		"Titanic", "Django", "Lucky#Slevin",
		"HipHop", "Hip-Hop", "Schlager", "Jazz"
	};
	
	private int[]eigenschaften = {
		1, 1, 1, 2, 2, 2, 3, 3, 3, 3
	};
}
