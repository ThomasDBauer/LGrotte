package de.shared;
import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Auswahl;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;

/**
 * Das asynchrone Gegenstück des Interface @link {@link EditorService}
 * 
 * @author Thomas Bauer, Enrico Popaj und Nicolai Ehrmanntraut
 *
 * @version 1.0
 */

public interface EditorServiceAsync {

	/**
	 * 
	 * @see de.server.editor.EditorServiceImpl#insertProfil
	 * (String, String, String, int, String, String, String, String, Date);
	 */
	void insertProfil(String email, String fname, String lname,
			int koerpergroesse, String geschlecht, String religion,
			String haarfarbe, String raucher, Date geburtsdatum,
			AsyncCallback callback) throws IllegalArgumentException;

	void deleteProfil(AsyncCallback callback) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#getProfil();
	 */
	void getProfil(AsyncCallback<Profil> callback) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getAuswahlForEigenschaft(Eigenschaft);
	 */
	void getAuswahlForEigenschaft(Eigenschaft e, 
			AsyncCallback<Vector<Auswahl>>callback) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfil();
	 */
	void getProfil(String email, AsyncCallback<Profil> callback) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofil(String);
	 */
	void deleteSuchprofilInfosForUser(Suchprofil sp, AsyncCallback callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteProfilInfosForUser();
	 */
	void deleteProfilInfosForUser(AsyncCallback callback) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#updateProfil
	 * (String, String, int, String, String, String, String, Date, String);
	 */
	void updateProfil(String fname, String lname, int koerpergroesse,
			String geschlecht, String religion, String haarfarbe,
			String raucher, Date geburtsdatum, String email,
			AsyncCallback callback) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#insertSuchprofil
	 * (String, String, String, String, int, int, int, int, String);
	 */
	void insertSuchprofil(String suchprofilname, String geschlecht,
			String raucher, String religion, int minAlter, int maxAlter,
			int minGroesse, int maxGroesse, String haarfarbe,
			AsyncCallback callback) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#updateSuchprofil
	 * (String, int, int, String, String, String, int, int, String);
	 */
	void updateSuchprofil(String geschlecht, int minAlter, int maxAlter,
			String religion, String haarfarbe, String raucher, 
			int minGroesse, int maxGroesse, String suchprofilname, 
			AsyncCallback callback) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofil(String);
	 */
	void deleteSuchprofil(String suchprofilname, AsyncCallback callback)
			throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#getEigenschaften();
	 */
	void getEigenschaften(AsyncCallback<Vector<Eigenschaft>> callback)
			throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#insertProfilInfo(Info);
	 */
	void insertProfilInfo(Info info, AsyncCallback callback) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertSuchprofilInfo(Suchprofil, Info);
	 */
	void insertSuchprofilInfo(Suchprofil sp, Info info, AsyncCallback callback) 
			throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#getSuchprofile();
	 */
	void getSuchprofile(AsyncCallback<Vector<Suchprofil>> callback)
			throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#setUser(Profil);
	 */
	void setUser(Profil p, AsyncCallback callback);

	/**
	 * @see de.server.editor.EditorServiceImpl#getProfilesForEditor();
	 */
	void getProfilesForEditor(AsyncCallback<Vector<Profil>> callback)
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertMerkzettel(Vector);
	 */
	void insertMerkzettel(Vector<String> emails, AsyncCallback callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getMerkzettelByOwner();
	 */
	void getMerkzettelByOwner(AsyncCallback<Vector<Merkzettel>> callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getMerkzettelProfileByOwner();
	 */
	void getMerkzettelProfileByOwner(AsyncCallback<Vector<Profil>> callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertKontaktsperren(Vector);
	 */
	void insertKontaktsperren(Vector<String>emails, AsyncCallback callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getSuchprofileByName(String);
	 */
	void getSuchprofileByName(String suchprofilname, AsyncCallback<Suchprofil> callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteInfo(Info);
	 */
	void deleteInfo(Info info, AsyncCallback callback) throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#deleteProfilInfo(ProfilInfo);
	 */
	void deleteProfilInfo(ProfilInfo pi, AsyncCallback callback)
			throws Exception;

	/**
	 * @see de.server.editor.EditorServiceImpl#deleteMerkzettel(Vector);
	 */
	void deleteMerkzettel(Vector<String> emails, AsyncCallback callback);
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfilEigenschaften();
	 */
	void getProfilEigenschaften(AsyncCallback<Vector<ProfilEigenschaft>>callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getSuchprofilEigenschaften(String);
	 */
	void getSuchprofilEigenschaften(String spname, 
			AsyncCallback<Vector<ProfilEigenschaft>>callback) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getProfilEigenschaften(String);
	 */
	void getProfilEigenschaften(String email, 
			AsyncCallback<Vector<ProfilEigenschaft>>callback) throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#getKontaktsperrenByOwner();
	 */
	void getKontaktsperrenByOwner(AsyncCallback<Vector<Profil>>callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteKontaktsperre(Vector);
	 */
	void deleteKontaktsperre(Vector<String> emails, AsyncCallback callback);
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertBesuch(Profil);
	 */
	void insertBesuch(Profil besuchtesProfil, AsyncCallback callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertMerkzettel(String);
	 */
	void insertMerkzettel(String email, AsyncCallback callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#insertKontaktsperre(String);
	 */
	void insertKontaktsperre(String email, AsyncCallback callback) 
			throws Exception;
	
	/**
	 * @see de.server.editor.EditorServiceImpl#deleteSuchprofilInfo(Suchprofil, Info);
	 */
	void deleteSuchprofilInfo(Suchprofil sp, Info info, AsyncCallback callback) 
			throws Exception;
}
