package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.gui.Editor;
import de.client.gui.Navigation;
import de.client.temp.SeedButton;
import de.client.temp.UserLoginTestGUI;
import de.shared.BO.Profil;

public class LGrotte implements EntryPoint {

	private final TestServiceAsync greetingService = GWT.create(TestService.class);

	public void onModuleLoad() {
		
		RootPanel.get("Einstellungen_oben").add(new SeedButton());
		RootPanel.get("Navi").add(new Navigation());
		RootPanel.get("Inhalt_unten").add(new Editor());
		RootPanel.get("Einstellungen_unten").add(new UserLoginTestGUI());
		
		ClientSideSettings.getLoginService().login(new AsyncCallback<Profil>(){
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
			}
			public void onSuccess(Profil result) {
				// TODO Auto-generated method stub
			}
		});
	}
}
