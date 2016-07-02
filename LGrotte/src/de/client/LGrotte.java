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

/**
 * Entry-Point-Klasse des Projektes LGrotte
 * 
 * @author Thomas Bauer & Lukas Kircher
 * 
 * @version 1.0
 *
 */
public class LGrotte implements EntryPoint {

	//Zwei Panels fue die GUI
	private VerticalPanel loginPanel = new VerticalPanel();
	private HorizontalPanel naviPanel = new HorizontalPanel();
	
	//Begruessungstext im Label
	private Label loginLabel = new Label(
			"Melde dich jetzt mit deinem Google-Account an und finde deine neue Liebe!");
	
	//Zur An- und Abmeldung
	private Anchor signInLink = new Anchor("Anmelden");
	private final Button loginButton = new Button("Anmelden");
	private final Anchor logOutLink = new Anchor("Abmelden");
	private Button logOutButton = new Button("Abmelden");
	public static LogOutPopUp logOutPop = new LogOutPopUp();
	public static String logOutUrl;
	
	//Zur Kommunikation mit der Datenbank
	private EditorServiceAsync editorService;
	private LoginServiceAsync loginService;
	
	/**
	 * Da diese Klasse die Implementierung des Interface <code>EntryPoint</code>
	 * zusichert, benötigen wir eine Methode
	 * <code>public void onModuleLoad()</code>. Diese ist das GWT-Pendant der
	 * <code>main()</code>-Methode normaler Java-Applikationen.
	 */

	public void onModuleLoad() {

		/*
		 * Zuerst instanzieren wir jeweils eine EditorService-Instanz & eine
		 * LoginService Instanz
		 */

		editorService = ClientSideSettings.getEditorService();
		loginService = ClientSideSettings.getLoginService();
//		RootPanel.get("Zusatz").add(new SeedButton());

		/*
		 * Wir rufen die Methode login mit den Uebergabeparametern request Uri &
		 * AsyncCallback vom Datentyp Profil auf - welche uns ein Callback mit
		 * einem Profil zurueckgibt Als naechstes setzten wir den User als
		 * eingelogt ueber die Methode editorService.setUser
		 * 
		 * Nun wird geprueft, ob das zurueckgegebene Profil eingeolgt ist Wenn
		 * dies der Fall ist, dann wird geprueft ob, sich der User das erste Mal
		 * anmeldet, dies wird ueber result.getFname() == null geprüft
		 * 
		 * Beim ersten anmelden wird eine Instanz der Klasse
		 * ProfilERstellenEditor erstellt und dem RootPanel hinzugefuegt Der
		 * User kann nun sein noch leeres Profil anlegen
		 * 
		 * Ist bereits ein Profil angelegt, so wird dieses Profil mit dem
		 * erstellen eines Objektes der Klasse ProfilAnzeigenEditor angezeigt
		 */

		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html",
				new AsyncCallback<Profil>() {
					public void onFailure(Throwable caught) {
						RootPanel.get("Zusatz").add(
								new Label("Entry.login " + caught.toString()));
					}

					public void onSuccess(Profil result) {
						editorService.setUser(result, new SetUserCallback());
						if (result.isLoggedIn()) {
							if (result.getFname() == "null") {
								try {
									RootPanel.get("Inhalt").add(
											new ProfilErstellenEditor());
								} catch (Exception e1) {
									e1.printStackTrace();
								}
							} else {
								RootPanel.get("Inhalt").add(
										new HTML(
											"<h2>Willkommen in der Grotte, "
														+ result.getFname()
														+ "</h2>"));
								RootPanel.get("Inhalt").add(
										new ProfilAnzeigenEditor());
							}

							/*
							 * Hier wird der LogoutUrl auf den, 
							 * durch das Einloggen neu erstellten Link 
							 * aktualisiert
							 * 
							 * Weiter Bekommt der LogOutButton 
							 * einen ClickHandler zugewiesen, welcher die 
							 * Position und die Sichtbarkeit des 
							 * Popups LogOutPop festlegt
							 */
							
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
														.getAbsoluteLeft()-80;
													int top = logOutButton
														.getAbsoluteTop()+45;
													getLogOutPop()
															.setPopupPosition(
																	left, top);
													getLogOutPop().show();
												}

											});
								}
							});
							
							/*
							 * Eine neue Instanz der Klasse Navigation wird
							 * erstellt und dem RootPanel angefuegt
							 */
							
							naviPanel.add(new Navigation());
							naviPanel.add(logOutButton);
							naviPanel.addStyleName("navi-panel");
							RootPanel.get("Navi").add(naviPanel);
							
							
							/*
							 * Ist das Ergebnis der Abfrage, ob der User 
							 * eingelogt ist negativ, so wird eine 
							 * GUI angezeigt, die dem User die Anmeldung
							 * ermoeglicht
							 */
						} else {
							signInLink.setHref(result.getLoginUrl());
							loginLabel.setStylePrimaryName("login-label");
							loginPanel.add(loginLabel);
							loginButton.addClickHandler(new ClickHandler() {
								public void onClick(ClickEvent e) {
									Window.open(signInLink.getHref(), "_self",
											"");
								}
							});
							loginButton.setStylePrimaryName("login-button");
							loginPanel.add(loginButton);
							RootPanel.get("Inhalt").add(loginPanel);
						}
					}

				});
	}

	/*
	 * Der getter und setter des LogOutPopUp, die in der Klasse LogOutPopUp
	 * im Package "de.client.gui" zur Uebergabe des 
	 * Objektes aus der Klasse LGrotte benutz wird
	 */
	
	public static LogOutPopUp getLogOutPop() {
		return logOutPop;
	}

	public static void setLogOutPop(LogOutPopUp hideIt) {
		logOutPop = hideIt;
	}

	/*
	 * Hier wird der AsyncCallback der nach dem setzten des aktuellen User
	 * zurueckgegeben wird festgelegt
	 */
	
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