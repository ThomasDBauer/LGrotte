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

public interface EditorServiceAsync {
	void init(AsyncCallback callback) throws Exception;

	void insertProfil(String email, String fname, String lname,
			int koerpergroesse, String geschlecht, String religion,
			String haarfarbe, String raucher, Date geburtsdatum,
			AsyncCallback callback) throws IllegalArgumentException;

	void deleteProfil(AsyncCallback callback) throws Exception;

	void getProfil(AsyncCallback<Profil> callback) throws Exception;
	
	void getAuswahlForEigenschaft(Eigenschaft e, AsyncCallback<Vector<Auswahl>>callback) throws Exception;
	
	void getProfil(String email, AsyncCallback<Profil> callback) throws Exception;
	
	void deleteSuchprofilInfosForUser(Suchprofil sp, AsyncCallback callback) throws Exception;
	
	void deleteProfilInfosForUser(AsyncCallback callback) throws Exception;

	void updateProfil(String fname, String lname, int koerpergroesse,
			String geschlecht, String religion, String haarfarbe,
			String raucher, Date geburtsdatum, String email,
			AsyncCallback callback) throws Exception;

	void insertSuchprofil(String suchprofilname, String geschlecht,
			String raucher, String religion, int minAlter, int maxAlter,
			int minGroesse, int maxGroesse, String haarfarbe,
			AsyncCallback callback) throws Exception;

	void updateSuchprofil(String geschlecht, int minAlter, int maxAlter,
			String religion, String haarfarbe, String raucher, int minGroesse, int maxGroesse, String suchprofilname, AsyncCallback callback)
			throws Exception;

	void deleteSuchprofil(String suchprofilname, AsyncCallback callback)
			throws Exception;

	void getEigenschaften(AsyncCallback<Vector<Eigenschaft>> callback)
			throws Exception;

	void insertProfilInfo(Info info, AsyncCallback callback) throws Exception;
	
	void insertSuchprofilInfo(Suchprofil sp, Info info, AsyncCallback callback) throws Exception;

	void getSuchprofile(AsyncCallback<Vector<Suchprofil>> callback)
			throws Exception;

	void setUser(Profil p, AsyncCallback callback);

	void getProfilesForEditor(AsyncCallback<Vector<Profil>> callback)throws Exception;
	
	void insertMerkzettel(Vector<String> emails, AsyncCallback callback) throws Exception;
	
	void getMerkzettelByOwner(AsyncCallback<Vector<Merkzettel>> callback) throws Exception;
	
	void getMerkzettelProfileByOwner(AsyncCallback<Vector<Profil>> callback) throws Exception;
	
	void insertKontaktsperren(Vector<String>emails, AsyncCallback callback) throws Exception;
	
	void getSuchprofileByName(String suchprofilname, AsyncCallback<Suchprofil> callback) throws Exception;
	
//	void insertProfilInfo(ProfilInfo pi, AsyncCallback callback) throws Exception;
	
	void deleteInfo(Info info, AsyncCallback callback) throws Exception;

	void deleteProfilInfo(ProfilInfo pi, AsyncCallback callback)
			throws Exception;

	void deleteMerkzettel(Vector<String> emails, AsyncCallback callback);
	
	void getProfilEigenschaften(AsyncCallback<Vector<ProfilEigenschaft>>callback) throws Exception;
	
	void getSuchprofilEigenschaften(String spname, AsyncCallback<Vector<ProfilEigenschaft>>callback) throws Exception;
	
	void getProfilEigenschaften(String email, AsyncCallback<Vector<ProfilEigenschaft>>callback) throws Exception;
	
	void getKontaktsperrenByOwner(AsyncCallback<Vector<Profil>>callback) throws Exception;
	
	void deleteKontaktsperre(Vector<String> emails, AsyncCallback callback);
	
	void insertBesuch(Profil besuchtesProfil, AsyncCallback callback) throws Exception;
	
	void insertMerkzettel(String email, AsyncCallback callback) throws Exception;
	
	void insertKontaktsperre(String email, AsyncCallback callback) throws Exception;
	
	void deleteSuchprofilInfo(Suchprofil sp, Info info, AsyncCallback callback) throws Exception;
}
