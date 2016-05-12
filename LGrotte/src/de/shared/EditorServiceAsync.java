package de.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EditorServiceAsync {
	public void init(AsyncCallback callback) throws IllegalArgumentException;
	public void insertProfil(String fname, String lname, String geschlecht, String haarfarbe, int koerpergroesse, String religion, String raucher, AsyncCallback callback) throws IllegalArgumentException;
}
