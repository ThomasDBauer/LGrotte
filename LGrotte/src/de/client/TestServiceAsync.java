package de.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface TestServiceAsync {
	void seed(AsyncCallback callback) throws Exception;
}