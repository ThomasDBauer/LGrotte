package de.shared;

import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;

public class AehnlichkeitsLogik {
	
	private Profil referenz = new Profil();
	private Suchprofil vergleich = new Suchprofil();
	private int aehnlichkeit;
	private Prozent prozent = new Prozent(aehnlichkeit);
	

	public AehnlichkeitsLogik(Profil r, Suchprofil v){
		this.referenz = r;
		this.vergleich = v;
		
		r.getGeschlecht();
		r.getHaarfarbe();
		r.getKoerpergroesse();
		r.getRaucher();
		r.getReligion();
		StringBuffer gDatum;
		gDatum = new StringBuffer(r.getGeburtsdatum().toString());
		
		
		
		
		
	};
	
	
}
