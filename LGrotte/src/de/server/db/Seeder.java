package de.server.db;

import de.server.db.seeds.EigenschaftSeeds;
import de.server.db.seeds.InfoSeeds;
import de.server.db.seeds.MerkzettelSeeds;
import de.server.db.seeds.ProfilInfoSeeds;
import de.server.db.seeds.ProfilSeeds;
import de.server.db.seeds.SuchprofilInfoSeeds;
import de.server.db.seeds.SuchprofilSeeds;

public class Seeder {
	/* !!Was ist das und warum?
	 * Der Seeder erstellt erst die n�tigen Tabellen und f�llt sie mit Dummy-Daten. 
	 * W�hrend der Entwicklung ist das sehr praktisch:
	 * 
	 * 1. Im Laufe der Entwicklung muss die Datenbank immer wieder neu 
	 * aufgesetzt werden: zB weil neue Spalten hinzugef�gt oder gel�scht 
	 * werden m�ssen. Jedes Mal neue Tabellen zu erstellen und die Daten 
	 * daf�r einzugeben ist sehr anstrengend.
	 * 
	 * 2. Mit Testdaten l�sst sich die Funktionalit�t der Datenbank/Applikation 
	 * schnell testen. Wenn Tabellen und Daten nicht richtig zusammenpassen, 
	 * f�llt das mit einem Seeder schnell auf.
	 */
	
	/* !!HowTo:
	 * 
	 * 0. In das Package mit den MapperKlassen diese SeederKlasse implementieren.
	 * 
	 * 1. ein neuen Block starten. zB **** profilTable start ****
	 * 
	 * 2. eine Methode schreiben. zB seedProfilTable()
	 * 
	 * 3. f�r jedes Attribut des BusinessObjects einen Array anlegen
	 * 	  mit den ben�tigten Werten. zB 10xVornamen, 10xNachnamen und 10xAlter
	 * 
	 * 4. Eine Schleife in die seedProfilTable() schreiben, die die Mapper.insert()
	 *    benutzt und daf�r die Werte aus den Arrays aus 3. benutzt.
	 *    
	 * 5. Die neue Methode aus 2. in die seed() Methode schreiben. 
	 *    5.1 Die createTable() des Mappers in die migrate() Methode schreiben.
	 * 
	 * 6. Von wo auch immer die seed() Methode aufrufen. Die ruft alle andern Methoden
	 *    auf und bef�llt eure Datenbank.
	 *    
	 * 7. Sich freuen wie n Iltis! 
	 * 
	 */

	/** Diese Methode startet den ganzen Prozess) 
	 * @throws Exception */
	public void init() throws Exception{
		migrate();
		seed();
	}
	
	/**Hier m�ssen die createTable() Methoden der Mapper eingef�gt werden 
	 * @throws Exception */
	private void migrate() throws Exception{
//		Migrations m = new Migrations();
//		m.migrate();
		
		
		EigenschaftMapper.eigenschaftMapper().createEigenschaftTable();
		ProfilMapper.profilMapper().createProfilTable();
		InfoMapper.infoMapper().createInfoTable();
		ProfilinfoMapper.profilinfoMapper().createProfilInfo();
		MerkzettelMapper.merkzettelMapper().createMerkzettelTable();
		SuchprofilMapper.suchprofilMapper().createSuchprofilTable();
		KontaktsperreMapper.kontaktsperreMapper().createKontaktsperreTable();
		SuchprofilInfoMapper.suchprofilInfoMapper().createSuchProfilInfoTable();
		BesucheMapper.besucheMapper().createBesucheTable();
		AuswahlMapper.auswahlMapper().createAuswahlTable();
//		AehnlichkeitMapper.aehnlichkeitMapper().createAehnlichkeitTable();    <-- brauch mer nimmer
	}

	private void seed() throws Exception{
//		//Profile
		ProfilSeeds ps = new ProfilSeeds();
		ps.seedProfilTable();
		//Eigenschaften
		EigenschaftSeeds es = new EigenschaftSeeds();
		es.seedEigenschaftTable();
		//Infos
		InfoSeeds is = new InfoSeeds();
		is.seedInfoTable();
		//ProfilInfos
		ProfilInfoSeeds pis = new ProfilInfoSeeds();
		pis.seedProfilinfoTable();
//		SuchprofilInfoSeeds spis = new SuchprofilInfoSeeds();
//		spis.seedSuchprofilInfo();
		
		SuchprofilSeeds sps = new SuchprofilSeeds();
		sps.seedSuchprofilTable();
		
		// MerkzettelSeeds
		//MerkzettelSeeds mz = new MerkzettelSeeds();
		//mz.seedMerkzettelTable();
	}
}
