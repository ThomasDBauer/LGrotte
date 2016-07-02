package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.gui.LogOutPopUp;
import de.client.gui.Navigation;
import de.client.gui.ProfilAnzeigenEditor;
import de.client.gui.ProfilErstellenEditor;
import de.client.temp.SeedButton;
import de.shared.EditorServiceAsync;
import de.shared.LoginServiceAsync;
import de.shared.BO.Profil;

public class LGrotte implements EntryPoint {

	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Melde dich jetzt mit deinem Google-Account an und finde deine neue Liebe!");
	private Anchor signInLink = new Anchor("Anmelden");
	private final Button loginButton = new Button("Anmelden");
	private final Anchor logOutLink = new Anchor("Abmelden");
	private EditorServiceAsync editorService;
	private LoginServiceAsync loginService;
	private Button logOutButton = new Button("Abmelden");
	public static LogOutPopUp logOutPop = new LogOutPopUp();
	public static String logOutUrl;
	private HorizontalPanel naviPanel = new HorizontalPanel();

	public void onModuleLoad() {
		
		editorService = ClientSideSettings.getEditorService();
		loginService = ClientSideSettings.getLoginService();
		
		RootPanel.get("Zusatz").add(new SeedButton());
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html",
				new AsyncCallback<Profil>() {
					public void onFailure(Throwable caught) {
						RootPanel.get("Zusatz").add(
								new Label("Entry.login " + caught.toString()));
					}

					public void onSuccess(Profil result) {
						editorService.setUser(result, new SetUserCallback());
						if (result.isLoggedIn()) {
							if (result.getFname() == null) {
								try {
									RootPanel.get("Inhalt").add(new ProfilErstellenEditor());
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} else {
							RootPanel.get("Inhalt").add(
									new HTML("<h2>Willkommen in der Grotte, "
											+ result.getFname() + "</h2>"));
							RootPanel.get("Inhalt").add(
									new ProfilAnzeigenEditor());
							}
							logOutLink.setHref(result.getLogoutUrl());
							logOutUrl = logOutLink.getHref();
							logOutButton.setStylePrimaryName("navi-button");
							logOutButton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent e) {
									getLogOutPop().setPopupPositionAndShow(
											new PopupPanel.PositionCallback() {
												public void setPosition(
														int offsetWidth,
														int offsetHeight) {
													int left = logOutButton
															.getAbsoluteLeft() - 80;
													int top = logOutButton
															.getAbsoluteTop() + 45;
													getLogOutPop()
															.setPopupPosition(
																	left, top);
													getLogOutPop().show();
												}

											});
								}
							});

							naviPanel.add(new Navigation());
							naviPanel.add(logOutButton);
							naviPanel.addStyleName("navi-panel");
							RootPanel.get("Navi").add(naviPanel);
						} else {
							signInLink.setHref(result.getLoginUrl());
							loginLabel.setStylePrimaryName("login-label");
							loginPanel.add(loginLabel);
							loginButton.addClickHandler(new ClickHandler(){
								public void onClick(ClickEvent e) {
								Window.open(signInLink.getHref(), "_self", "");
								}
							});
							loginButton.setStylePrimaryName("login-button");
							loginPanel.add(loginButton);
							RootPanel.get("Inhalt").add(loginPanel);
						}
					}

				});
	}

	public static LogOutPopUp getLogOutPop() {
		return logOutPop;
	}

	public static void setLogOutPop(LogOutPopUp hideIt) {
		logOutPop = hideIt;
	}

	private class SetUserCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
		}

		public void onSuccess(Object result) {
			try {
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}