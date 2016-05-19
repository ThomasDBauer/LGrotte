package de.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.client.TestService;
import de.client.TestServiceAsync;
import de.client.temp.SeedButton;
import de.client.temp.UserLoginTestGUI;
import de.shared.BO.Profil;

public class LoginFrame extends VerticalPanel {
	
	private final TestServiceAsync greetingService = GWT.create(TestService.class);
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the StockWatcher application.");
	private Anchor signInLink = new Anchor("Sign In");
	final Anchor logOutLink = new Anchor("Logout");
	
	public LoginFrame() {
		RootPanel.get("Einstellungen_oben").add(new SeedButton());
		ClientSideSettings.getLoginService().login(GWT.getHostPageBaseURL() + "LGrotte.html",
				new AsyncCallback<Profil>() {
					public void onFailure(Throwable caught) {
						RootPanel.get("Einstellungen_oben").add(new Label(caught.toString()));
					}

					public void onSuccess(Profil result) {
						if (result.isLoggedIn()) {
							RootPanel.get("Navi").add(new Label("Willkommen in der Grotte, " + result.getFname()));
							logOutLink.setHref(result.getLogoutUrl());
							RootPanel.get("Einstellungen_oben").add(logOutLink);
							RootPanel.get("Navi").add(new Navigation());
							RootPanel.get("Inhalt_unten").add(new MeinProfilEditor());
							RootPanel.get("Einstellungen_unten").add(new UserLoginTestGUI());
						} else {
							signInLink.setHref(result.getLoginUrl());
							loginPanel.add(loginLabel);
							loginPanel.add(signInLink);
							RootPanel.get("Inhalt_oben").add(loginPanel);
						}
					}

				});
	}
}
