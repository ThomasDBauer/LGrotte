package de.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Profil;
import de.shared.BO.Suchprofil;

	/**
	 * The client-side stub for the RPC service.
	 */
	@RemoteServiceRelativePath("editor")
	public interface EditorService extends RemoteService {
		void init() throws IllegalArgumentException;
		
		void insertProfil(String email, String fname, String lname, 
				int koerpergroesse, String geschlecht, String religion, 
				String haarfarbe, String raucher, Date geburtsdatum) throws IllegalArgumentException;
		
		void deleteProfil(String email) throws IllegalArgumentException;
		
		void insertSuchprofil(String suchprofilname, String geschlecht, String raucher, 
				String religion, int minAlter, int maxAlter, int minGroesse, int maxGroesse, 
				String haarfarbe) throws Exception;
		void deleteSuchprofil(String suchprofilname) throws Exception;
		
		Vector<Eigenschaft> getEigenschaften() throws Exception;
		
		void insertInfo(Info info) throws Exception;
		
		Vector<Suchprofil> getSuchprofile() throws Exception;	
		}

