package de.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;
import de.shared.BO.ProfilInfo;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("editor")
public interface EditorService extends RemoteService {
	void init() throws IllegalArgumentException;

	void insertProfil(String email, String fname, String lname, int koerpergroesse, String geschlecht, String religion,
			String haarfarbe, String raucher, Date geburtsdatum) throws IllegalArgumentException;
	
	Profil getProfil() throws Exception;
	
	Profil getProfil(String email) throws Exception;
	
	void updateProfil(String fname, String lname, int koerpergroesse, String geschlecht,
			String religion, String haarfarbe, String raucher, Date geburtsdatum, String email) throws Exception;

	void deleteProfil(String email) throws IllegalArgumentException;

	void insertSuchprofil(String suchprofilname, String geschlecht, String raucher, String religion, int minAlter,
			int maxAlter, int minGroesse, int maxGroesse, String haarfarbe) throws Exception;
	
	void updateSuchprofil(String geschlecht, int minAlter, int maxAlter,
			String religion, String haarfarbe, String raucher, int minGroesse, int maxGroesse, String suchprofilname) throws Exception;

	void deleteSuchprofil(String suchprofilname) throws Exception;

	Vector<Eigenschaft> getEigenschaften() throws Exception;

	void insertProfilInfo(Info info) throws Exception;
	
	void insertSuchprofilInfo(Suchprofil sp, Info info) throws Exception;

	Vector<Suchprofil> getSuchprofile() throws Exception;

	void setUser(Profil p);
	
	Vector<Profil> getProfilesForEditor() throws Exception;
	
	void insertMerkzettel(Vector<String> emails) throws Exception;
	
	void deleteMerkzettel(Vector<String> emails) throws Exception;
	
	Vector<Merkzettel> getMerkzettelByOwner() throws Exception;
	
	Vector<Profil> getMerkzettelProfileByOwner() throws Exception;
	
	void insertKontaktsperren(Vector<String>emails) throws Exception;
	
	void deleteKontaktsperre(Vector<String> emails) throws Exception;
	
	Suchprofil getSuchprofileByName(String suchprofilname) throws Exception;
	
//	void insertProfilInfo(ProfilInfo pi) throws Exception;
	
	void deleteInfo(Info info) throws Exception;
	
	void deleteProfilInfo(ProfilInfo pi) throws Exception;
	
	Vector<ProfilEigenschaft> getProfilEigenschaften() throws Exception;
	
	Vector<Profil> getKontaktsperrenByOwner() throws Exception;
}
