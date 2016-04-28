package de.server;

import de.client.GreetingService;
import de.server.db.DBConnection;
import de.shared.FieldVerifier;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

} 
