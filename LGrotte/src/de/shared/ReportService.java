package de.shared;

import com.google.gwt.user.client.rpc.RemoteService;

public interface ReportService extends RemoteService {
	public void init() throws IllegalArgumentException;
}