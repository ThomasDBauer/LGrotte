package de.server;
import de.client.GreetingService;
import de.server.db.Seeder;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {
	
	//die hier geht nicht. die ruft die methode im merkzettelmapper auf:
	public void seed() throws Exception{
		Seeder seeder = new Seeder();
		seeder.init();
	}
} 
