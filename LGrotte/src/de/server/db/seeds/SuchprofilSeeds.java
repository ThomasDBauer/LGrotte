package de.server.db.seeds;

import de.server.db.SuchprofilMapper;
import de.shared.BO.Suchprofil;

public class SuchprofilSeeds {
	
	

	public void seedSuchprofilTable() throws Exception{
		
		Suchprofil sp = new Suchprofil();
		sp.setGeschlecht("weiblich");
		sp.setHaarfarbe("brunette");
		sp.setMaxAlter(99);
		sp.setMinAlter(18);
		sp.setMinGroesse(140);
		sp.setMaxGroesse(190);
		sp.setProfil("thdobauer@gmail.com");
		sp.setRaucher("Raucher");
		sp.setReligion("Christlich");
		sp.setSuchprofilname("DickeFrauen");
		
		SuchprofilMapper.suchprofilMapper().insertSuchprofil(sp);
		
		Suchprofil sp1 = new Suchprofil();
		sp1.setGeschlecht("m&aumlnnlich");
		sp1.setHaarfarbe("Glatze");
		sp1.setMaxAlter(99);
		sp1.setMinAlter(18);
		sp1.setMinGroesse(140);
		sp1.setMaxGroesse(190);
		sp1.setProfil("thdobauer@gmail.com");
		sp1.setRaucher("Raucher");
		sp1.setRaucher("andere");
		sp1.setSuchprofilname("RauchendeGlatzen");
		
		SuchprofilMapper.suchprofilMapper().insertSuchprofil(sp1);
		
		
	}
	
}
