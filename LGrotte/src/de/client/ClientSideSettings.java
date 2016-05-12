package de.client;

import com.google.gwt.core.client.GWT;

import de.shared.EditorService;
import de.shared.EditorServiceAsync;
import de.shared.ReportServiceAsync;

public class ClientSideSettings {
	
	private static EditorServiceAsync editorService = null;
	private static ReportServiceAsync reportService = null;
	
	public static EditorServiceAsync getBankVerwaltung() {
	    if (editorService == null) {
	      editorService = GWT.create(EditorService.class);
	    }
	    return editorService;
	  }
	
}
