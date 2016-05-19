package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.gui.MeinProfilEditor;
import de.client.gui.LoginFrame;
import de.client.gui.Navigation;
import de.client.temp.SeedButton;
import de.client.temp.UserLoginTestGUI;
import de.shared.BO.Profil;

public class LGrotte implements EntryPoint {

	public void onModuleLoad() {
		
		RootPanel.get().add(new LoginFrame());
	}
}
