package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

import de.client.gui.FindLove;
import de.client.gui.LoginFrame;

public class LGrotte implements EntryPoint {

	public void onModuleLoad() {
		RootPanel.get().add(new LoginFrame());
		RootPanel.get().add(new FindLove());
	}
}
