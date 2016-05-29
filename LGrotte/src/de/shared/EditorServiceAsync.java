package de.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Merkzettel;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;

public interface EditorServiceAsync {
	void init(AsyncCallback callback) throws IllegalArgumentException;
	
	void insertProfil(String email, String fname, String lname, int koerpergroesse, 
			String geschlecht, String religion, String haarfarbe, String raucher, Date geburtsdatum, 
			AsyncCallback callback) throws IllegalArgumentException;
	
	void deleteProfil(String email, AsyncCallback callback) throws IllegalArgumentException;
	
	void insertSuchprofil(String suchprofilname, String geschlecht, String raucher, 
			String religion, int minAlter, int maxAlter, int koerpergroesse, String haarfarbe, 
			AsyncCallback callback) throws Exception;
	
	void deleteSuchprofil(String suchprofilname, AsyncCallback callback) throws Exception;
	
	void getEigenschaften(AsyncCallback<Vector<Eigenschaft>> callback) throws Exception;
	
	void insertInfo(Info info, AsyncCallback callback) throws Exception;
	
	void getSuchprofile(AsyncCallback<Vector<Suchprofil>>callback) throws Exception;
	
	void getProfile(AsyncCallback<Vector<Profil>> callback) throws Exception;
	
	void insertMerkzettel(Vector<String> emails, AsyncCallback callback) throws Exception;
	
	void getProfilesForEditor(AsyncCallback<Vector<Profil>> troll) throws Exception;
	
	
}
