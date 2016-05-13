package de.client;

import com.google.gwt.core.client.GWT;

import de.shared.EditorService;
import de.shared.EditorServiceAsync;
import de.shared.ReportService;
import de.shared.ReportServiceAsync;

public class ClientSideSettings {
	
	private static EditorServiceAsync editorService = null;
	private static ReportServiceAsync reportService = null;
	
	public static EditorServiceAsync getEditorService() {
		if (editorService == null) {
			editorService = GWT.create(EditorService.class);
		}
		return editorService;
	}
	
	public static ReportServiceAsync getReportService() {
		if (reportService == null) {
			reportService = GWT.create(ReportService.class);
		}
		return reportService;
	}
	
}
