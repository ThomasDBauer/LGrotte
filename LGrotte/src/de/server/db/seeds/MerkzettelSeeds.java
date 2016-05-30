package de.server.db.seeds;

import de.server.db.MerkzettelMapper;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;

public class MerkzettelSeeds {

	public void seedMerkzettelTable() throws Exception{
		for(int i = 0; i < profile.length; i++){
			Profil mz = new Profil();
			mz.setEmail(profile[i]);
//			MerkzettelMapper.merkzettelMapper().insertMerkzettel(mz);
		}
	}
	
//	public void seedMerkzettelDelete() {
//		for(int i = 0; i < profil_ID.length; i++){
	//		Profil mz = new Profil();
//			mz.setId(someValues[i]);
		//	try {
			//	MerkzettelMapper.merkzettelMapper().deleteMerkzettel(mz);
			//} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			//}
		//}
//		}
	
	private int[]profil_ID = {
		5, 6, 7, 12, 54, 2, 98, 1, 54, 90
	};
	
	private String[]profile = {
			"Anna@LG", "Daniel@LG", "Daniel@LG", "Daniel@LG", "thdobauer@LG", 
			"enrico@gmail.com", "bauer@gmail.com", "nicolai@gmail.com", 
			"thdobauer@gmail.com", "Lina@LG", "Lina@LG", "Lina@LG", "Lina@LG"
	};
}
