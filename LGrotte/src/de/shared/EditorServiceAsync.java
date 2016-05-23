package de.shared;

import java.util.Date;
import java.util.Vector;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;

public interface EditorServiceAsync {
	public void init(AsyncCallback callback) throws IllegalArgumentException;
	public void insertProfil(String email, String fname, String lname, int koerpergroesse, 
			String geschlecht, String religion, String haarfarbe, String raucher, Date geburtsdatum, 
			AsyncCallback callback) throws IllegalArgumentException;
	public void deleteProfil(String email, AsyncCallback callback) throws IllegalArgumentException;

	void getEigenschaften(AsyncCallback<Vector<Eigenschaft>> callback) throws Exception;
	void insertInfo(Info info, AsyncCallback callback) throws Exception;
}
