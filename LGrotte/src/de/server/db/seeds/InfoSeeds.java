package de.server.db.seeds;

import de.server.db.InfoMapper;
import de.shared.BO.Info;

public class InfoSeeds {

	public void seedInfoTable() throws Exception{
		for(int i = 0; i < eigenschaften.length; i++){
			Info info = new Info();
			info.setValue(values[i]);
			info.setEigenschaft(eigenschaften[i]);
			InfoMapper.infoMapper().insertInfo(info);
		}
	}
	
	private String[]values = {
		"Fu�ball", "Fussball", "Schwimmen",
		"Liebesfilme", "Django", "Lucky#Slevin",
		"HipHop", "Hip-Hop", "Schlager", "Jazz",
		"Handball", "Titanic", "Pizza", "Frühling", "Elekro"
	};
	
	private int[]eigenschaften = {
		1, 1, 1, 2, 2, 2, 3, 3, 3, 3, 1, 2, 3, 4, 5
	};
}
