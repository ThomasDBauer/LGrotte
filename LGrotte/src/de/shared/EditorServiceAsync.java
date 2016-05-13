package de.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EditorServiceAsync {
	public void init(AsyncCallback callback) throws IllegalArgumentException;
	public void insertProfil(String fname, String lname, String geschlecht, String haarfarbe, int koerpergroesse, String religion, String raucher, Date geburtsdatum, AsyncCallback callback) throws IllegalArgumentException;
}
