package de.shared;

import java.util.Date;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface EditorServiceAsync {
	public void init(AsyncCallback callback) throws IllegalArgumentException;
	public void insertProfil(String fname, String lname, int koerpergroesse, String geschlecht, String religion, String haarfarbe, Date geburtsdatum, String raucher, AsyncCallback callback) throws IllegalArgumentException;
}
