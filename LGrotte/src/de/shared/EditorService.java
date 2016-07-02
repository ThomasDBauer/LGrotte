package de.shared;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Auswahl;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;

/**
 * The client-side stub for the RPC service.
 * 
 * @author Thomas Bauer, Enrico Popaj und Nicolai Ehrmanntraut
 *
 * @version 1.0 
 */
@RemoteServiceRelativePath("editor")
public interface EditorService extends RemoteService {
	
	/**
	 * 
	 * @see de.server.editor.EditorServiceImpl#insertProfil
	 * (String, String, String, int, String, String, String, String, Date);
	 */
	void insertProfil(String email, String fname, String lname, int koerpergroesse, 
			String geschlecht, String religion,
			String haarfarbe, String raucher, Date geburtsdatum) 
					throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfil();
	 */
	Profil getProfil() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getAuswahlForEigenschaft(Eigenschaft);
	 */
	Vector<Auswahl> getAuswahlForEigenschaft(Eigenschaft e) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofil(String);
	 */
	void deleteSuchprofilInfosForUser(Suchprofil sp) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfil();
	 */
	Profil getProfil(String email) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#updateProfil
	 * (String, String, int, String, String, String, String, Date, String);
	 */
	void updateProfil(String fname, String lname, int koerpergroesse, String geschlecht,
			String religion, String haarfarbe, String raucher, 
			Date geburtsdatum, String email) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteProfil();
	 */
	void deleteProfil() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertSuchprofil
	 * (String, String, String, String, int, int, int, int, String);
	 */
	void insertSuchprofil(String suchprofilname, String geschlecht, 
			String raucher, String religion, int minAlter,
			int maxAlter, int minGroesse, int maxGroesse, String haarfarbe) 
					throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#updateSuchprofil
	 * (String, int, int, String, String, String, int, int, String);
	 */
	void updateSuchprofil(String geschlecht, int minAlter, int maxAlter,
			String religion, String haarfarbe, String raucher, 
			int minGroesse, int maxGroesse, String suchprofilname) 
					throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofil(String);
	 */
	void deleteSuchprofil(String suchprofilname) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#getEigenschaften();
	 */
	Vector<Eigenschaft> getEigenschaften() throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#insertProfilInfo(Info);
	 */
	void insertProfilInfo(Info info) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertSuchprofilInfo(Suchprofil, Info);
	 */
	void insertSuchprofilInfo(Suchprofil sp, Info info) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#getSuchprofile();
	 */
	Vector<Suchprofil> getSuchprofile() throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#setUser(Profil);
	 */
	void setUser(Profil p);
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteProfilInfosForUser();
	 */
	void deleteProfilInfosForUser() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfilesForEditor();
	 */
	Vector<Profil> getProfilesForEditor() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertMerkzettel(String);
	 */
	void insertMerkzettel(Vector<String> emails) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofil(String);
	 */
	void deleteMerkzettel(Vector<String> emails) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getMerkzettelByOwner();
	 */
	Vector<Merkzettel> getMerkzettelByOwner() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getMerkzettelByOwner();
	 */
	Vector<Profil> getMerkzettelProfileByOwner() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertKontaktsperren(Vector);
	 */
	void insertKontaktsperren(Vector<String>emails) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertKontaktsperre(String);
	 */
	void deleteKontaktsperre(Vector<String> emails) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getSuchprofileByName(String);
	 */
	Suchprofil getSuchprofileByName(String suchprofilname) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteInfo(Info);
	 */
	void deleteInfo(Info info) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteProfilInfo(ProfilInfo);
	 */
	void deleteProfilInfo(ProfilInfo pi) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfilEigenschaften();
	 */
	Vector<ProfilEigenschaft> getProfilEigenschaften() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getSuchprofilEigenschaften(String);
	 */
	Vector<ProfilEigenschaft> getSuchprofilEigenschaften(String spname) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfilEigenschaften(String);
	 */
	Vector<ProfilEigenschaft> getProfilEigenschaften(String email) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getKontaktsperrenByOwner();
	 */
	Vector<Profil> getKontaktsperrenByOwner() throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertBesuch(Profil);
	 */
	void insertBesuch(Profil besuchtesProfil) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertMerkzettel(String);
	 */
	void insertMerkzettel(String email) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofil(String);
	 */
	void insertKontaktsperre(String email) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofilInfo(Suchprofil, Info);
	 */
	void deleteSuchprofilInfo(Suchprofil sp, Info info) throws Exception;
}
