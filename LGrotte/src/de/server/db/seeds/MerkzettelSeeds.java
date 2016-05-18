package de.server.db.seeds;

import de.server.db.MerkzettelMapper;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;

public class MerkzettelSeeds {

	public void seedMerkzettelTable(){
		for(int i = 0; i < someValues.length; i++){
			Profil mz = new Profil();
//			mz.setId(someValues[i]);
			try {
				MerkzettelMapper.merkzettelMapper().insertMerkzettel(mz);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void seedMerkzettelDelete() {
		for(int i = 0; i < someValues.length; i++){
			Profil mz = new Profil();
//			mz.setId(someValues[i]);
			try {
				MerkzettelMapper.merkzettelMapper().deleteMerkzettel(mz);
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
