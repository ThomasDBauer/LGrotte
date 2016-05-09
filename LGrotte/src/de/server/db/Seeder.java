package de.server.db;

import de.server.db.seeds.EigenschaftSeeds;
import de.server.db.seeds.InfoSeeds;
import de.server.db.seeds.ProfilSeeds;
import de.shared.BO.Profil;

public class Seeder {
	/* !!Was ist das und warum?
	 * Der Seeder erstellt erst die nï¿½tigen Tabellen und fï¿½llt sie mit Dummy-Daten. 
	 * Wï¿½hrend der Entwicklung ist das sehr praktisch:
	 * 
	 * 1. Im Laufe der Entwicklung muss die Datenbank immer wieder neu 
	 * aufgesetzt werden: zB weil neue Spalten hinzugefï¿½gt oder gelï¿½scht 
	 * werden mï¿½ssen. Jedes Mal neue Tabellen zu erstellen und die Daten 
	 * dafï¿½r einzugeben ist sehr anstrengend.
	 * 
	 * 2. Mit Testdaten lï¿½sst sich die Funktionalitï¿½t der Datenbank/Applikation 
	 * schnell testen. Wenn Tabellen und Daten nicht richtig zusammenpassen, 
	 * fï¿½llt das mit einem Seeder schnell auf.
	 */
	
	/* !!HowTo:
	 * 
	 * 0. In das Package mit den MapperKlassen diese SeederKlasse implementieren.
	 * 
	 * 1. ein neuen Block starten. zB **** profilTable start ****
	 * 
	 * 2. eine Methode schreiben. zB seedProfilTable()
	 * 
	 * 3. fï¿½r jedes Attribut des BusinessObjects einen Array anlegen
	 * 	  mit den benï¿½tigten Werten. zB 10xVornamen, 10xNachnamen und 10xAlter
	 * 
	 * 4. Eine Schleife in die seedProfilTable() schreiben, die die Mapper.insert()
	 *    benutzt und dafï¿½r die Werte aus den Arrays aus 3. benutzt.
	 *    
	 * 5. Die neue Methode aus 2. in die seed() Methode schreiben. 
	 *    5.1 Die createTable() des Mappers in die migrate() Methode schreiben.
	 * 
	 * 6. Von wo auch immer die seed() Methode aufrufen. Die ruft alle andern Methoden
	 *    auf und befï¿½llt eure Datenbank.
	 *    
	 * 7. Sich freuen wie n Iltis! 
	 * 
	 */
	

	/** Diese Methode startet den ganzen Prozess) 
	 * @throws Exception */
	public void init() throws Exception{
//		migrate();
//		seed();
		deleteProfil();
	}
	

	/**Hier mï¿½ssen die createTable() Methoden der Mapper eingefï¿½gt werden 
	 * @throws Exception */
	private void migrate() throws Exception{
		ProfilMapper.profilMapper().createProfilTable();
		EigenschaftMapper.eigenschaftMapper().createEigenschaftTable();
		InfoMapper.infoMapper().createInfoTable();
//		MerkzettelMapper.merkzettelMapper().createMerkzettelTable();
//		//KontaktsperreMapper.kontaktsperreMapper().createKontaktsperreTable();
//		AehnlichkeitMapper.aehnlichkeitMapper().createAehnlichkeitTable();
		
	}

	private void seed() throws Exception{
		ProfilSeeds ps = new ProfilSeeds();
		ps.seedProfilTable();
		EigenschaftSeeds es = new EigenschaftSeeds();
		es.seedEigenschaftTable();
		InfoSeeds is = new InfoSeeds();
		is.seedInfoTable();
	}
	
	private void deleteProfil() throws Exception{
		Profil p = new Profil();
		p.setFname("Gerd");
		p.setLname("Meyer");
		p.setAlter(111213);
		p.setId(1);
		p.setGeschlecht("männlich");
		p.setHaarfarbe("blond");
		p.setKoerpergroesse(191);
		p.setRaucher("jo");
		p.setReligion("sehr");
		ProfilMapper.profilMapper().deleteProfil(p);
	}

}
