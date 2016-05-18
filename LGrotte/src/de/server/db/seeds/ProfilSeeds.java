package de.server.db.seeds;

import java.sql.Date;

import de.server.db.ProfilMapper;
import de.shared.BO.Profil;

public class ProfilSeeds {

	@SuppressWarnings("deprecation")
	public void seedProfilTable() throws Exception{
		for(int i = 0; i < 10; i++){
			Profil p = new Profil();
			p.setFname(vornamen[i]);
			p.setLname(nachnamen[i]);
			p.setKoerpergroesse(koerpergroesse[i]);
			p.setReligion(religion[(int)(Math.random()*(religion.length))]);
			p.setHaarfarbe(haarfarbe[(int)(Math.random()*(haarfarbe.length))]);
			p.setRaucher(raucher[(int)(Math.random()*(raucher.length))]);
			p.setGeburtsdatum(new Date(85, 10, 5));
			p.setEmail(emails[i]);
			if(i < 5)p.setGeschlecht(geschlecht[0]);
			if(i > 5)p.setGeschlecht(geschlecht[1]);
			ProfilMapper.profilMapper().insertProfil(p);
		}
	}
	
	private String[]emails = {
			"Thomas@LG", "Daniel@LG", "Dieter@LG", "Gerd@LG", "Mike@LG",
			"Kerstin@LG", "Anna@LG", "Hanna@LG", "Lina@LG", "Sarah@LG"	
		};
	
	private String[]vornamen = {
		"Thomas", "Daniel", "Dieter", "Gerd", "Mike",
		"Kerstin", "Anna", "Hanna", "Lina", "Sarah"	
	};
	private String[]nachnamen = {
			"Bauer", "Müller", "Pracht", "Engstler", "Friedrichsen",
			"Schmidt", "Albrecht", "Züblin", "Weiß", "Schwarz"	
	};
	private int[]koerpergroesse = {
		189, 181, 176, 162, 151,
		165, 157, 155, 174, 172
	};
	private String[]religion = {
			"Jüdisch", "Muslimisch", "Christlich", "Buddhistish",
			"Atheist", "Hinduistisch", "andere"
	};
	private String[]haarfarbe = {
		"brunette", "blond", "rothaarig", "schwarz", "bunt", "grau",
		"Glatze"
	};
	private String[]raucher = {
		"Kettenraucher", "Raucher", "Gelegenheitsraucher", "Partyraucher",
		"Strikter Nichtraucher", "Nichtraucher"
	};
	private String[]geschlecht = {
		"männlich", "weiblich"
	};
}
