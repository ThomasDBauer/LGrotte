package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.gui.FindLove;
import de.client.gui.ImageFooter;
import de.client.gui.MeinProfilEditor;
import de.client.gui.Navigation;
import de.client.temp.SeedButton;
import de.shared.EditorService;
import de.shared.EditorServiceAsync;
import de.shared.BO.Profil;




public class LGrotte implements EntryPoint {
	
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Please sign in to your Google Account to access the StockWatcher application.");
	private Anchor signInLink = new Anchor("Sign In");
	private final Anchor logOutLink = new Anchor("Logout");
	private EditorServiceAsync editorService;
	
	
	public void onModuleLoad() {
		
		editorService = GWT.create(EditorService.class);
		
		RootPanel.get("Einstellungen").add(new SeedButton());
		ClientSideSettings.getLoginService().login(GWT.getHostPageBaseURL() + "LGrotte.html",
				new AsyncCallback<Profil>() {
					public void onFailure(Throwable caught) {
						RootPanel.get("Einstellungen").add(new Label(caught.toString()));
					}

					public void onSuccess(Profil result) {
						editorService.setUser(result, new SetUserCallback());
						if (result.isLoggedIn()) {
							RootPanel.get("Inhalt_oben").add(new Label("Willkommen in der Grotte, " + result.getFname()));
							logOutLink.setHref(result.getLogoutUrl());
							RootPanel.get("Einstellungen").add(logOutLink);
							RootPanel.get("Navi").add(new Navigation());
							RootPanel.get("Inhalt_unten").add(new MeinProfilEditor());
							RootPanel.get("Fusszeile").add(new ImageFooter());
						} else {
							signInLink.setHref(result.getLoginUrl());
							loginPanel.add(loginLabel);
							loginPanel.add(signInLink);
							RootPanel.get("Inhalt_oben").add(loginPanel);
						}
					}

				});
	}
	
	private class SetUserCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
		}
		public void onSuccess(Object result) {
			try {
				RootPanel.get().add(new FindLove());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
