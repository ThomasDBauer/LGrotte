package de.server.db.seeds;

import de.server.db.EigenschaftMapper;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Profil;

public class EigenschaftSeeds {

	public void seedEigenschaftTable() throws Exception{
		for(int i = 0; i < erlaeuterungen.length; i++){
			Eigenschaft e = new Eigenschaft();
			e.setErlaeuterung(erlaeuterungen[i]);
			EigenschaftMapper.eigenschaftMapper().insertEigenschaft(e);
		}
	}
	
	private String[]erlaeuterungen = {
		"Lieblingssport", "Lieblingsfilm", "Lieblingsmusik"
	};
}
