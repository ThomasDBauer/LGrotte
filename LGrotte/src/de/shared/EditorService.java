package de.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.shared.BO.Profil;

	/**
	 * The client-side stub for the RPC service.
	 */
	@RemoteServiceRelativePath("editor")
	public interface EditorService extends RemoteService {
		void init() throws IllegalArgumentException;
		public void insertProfil(String fname, String lname, int koerpergroesse, String geschlecht, String religion, String haarfarbe, Date geburtsdatum, String raucher) throws IllegalArgumentException;
	}

