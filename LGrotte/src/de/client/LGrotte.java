package de.client;

import org.apache.bcel.generic.GOTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InsertPanel.ForIsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.gui.FindLove;
import de.client.gui.ImageFooter;
import de.client.gui.LogOutPopUp;
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
	private final Button logOutButton = new Button("Logout");
	private LogOutPopUp logOutPop = new LogOutPopUp();
	public static String logOutUrl;

	public void onModuleLoad() {

		editorService = GWT.create(EditorService.class);

		RootPanel.get("Einstellungen").add(new SeedButton());
		ClientSideSettings.getLoginService().login(
				GWT.getHostPageBaseURL() + "LGrotte.html",
				new AsyncCallback<Profil>() {
					public void onFailure(Throwable caught) {
						RootPanel.get("Einstellungen").add(
								new Label(caught.toString()));
					}

					public void onSuccess(Profil result) {
						editorService.setUser(result, new SetUserCallback());
						if (result.isLoggedIn()) {
							RootPanel.get("Inhalt_oben").add(
									new Label("Willkommen in der Grotte, "
											+ result.getFname()));
							logOutLink.setHref(result.getLogoutUrl());
							logOutUrl = logOutLink.getHref();
							logOutButton.setStylePrimaryName("navi-button");
							logOutButton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent e) {
									getLogOutPop()
											.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
												public void setPosition(
														int offsetWidth,
														int offsetHeight) {
													int left = logOutButton
															.getAbsoluteLeft() -80;
													int top = logOutButton
															.getAbsoluteTop() + 45;
													getLogOutPop().setPopupPosition(
															left, top);
													getLogOutPop().show();
												}

											});
								}
							});

							RootPanel.get("Logout").add(logOutButton);
							RootPanel.get("Navi").add(new Navigation());
							RootPanel.get("Inhalt_unten").add(
									new MeinProfilEditor());
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

	public LogOutPopUp getLogOutPop() {
		return logOutPop;
	}

	public void setLogOutPop(LogOutPopUp logOutPop) {
		this.logOutPop = logOutPop;
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
