package de.client;

import java.util.Vector;

import org.apache.bcel.generic.GOTO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
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
import de.shared.LoginServiceAsync;
import de.shared.ReportService;
import de.shared.ReportServiceAsync;
import de.shared.BO.Profil;
import de.shared.RO.ProfilInformation;

public class LGrotte implements EntryPoint {

	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(
			"Melde dich jetzt mit deinem Google-Account an und finde deine neue Liebe!");
	private Anchor signInLink = new Anchor("Anmelden");
	private final Button loginButton = new Button("Anmelden");
	private final Anchor logOutLink = new Anchor("Abmelden");
	private EditorServiceAsync editorService;
	private ReportServiceAsync reportService;
	private TestServiceAsync testService = GWT.create(TestService.class);
	private LoginServiceAsync loginService;
	private Button logOutButton = new Button("Abmelden");
	public LogOutPopUp logOutPop = new LogOutPopUp();
	public static String logOutUrl;
	private HorizontalPanel naviPanel = new HorizontalPanel();

	public void onModuleLoad() {
		
		editorService = ClientSideSettings.getEditorService();
		loginService = ClientSideSettings.getLoginService();
		
		RootPanel.get("Einstellungen").add(new SeedButton());
		loginService.login(GWT.getHostPageBaseURL() + "LGrotte.html",
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
							RootPanel.get("Inhalt_unten").add(
									new MeinProfilEditor());
							RootPanel.get("Fusszeile").add(new ImageFooter());
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
							RootPanel.get("Inhalt_unten").add(loginPanel);
						}
					}

				});
		/*****************************************************************************
		 * 		TEST START
		 * **************************************************************************/
		 
//		try {
//			testService.getProfilInfos(new AsyncCallback<Vector<ProfilInformation>>() {
//				public void onFailure(Throwable caught) {
//					
//				}
//				public void onSuccess(Vector<ProfilInformation> result) {
//					FlexTable table = new FlexTable();
//					for(int i = 0; i < result.size(); i++){
//						table.setWidget(i, 0, new Label(result.elementAt(i).getName()));
//						table.setWidget(i, 1, new Label(result.elementAt(i).getWert()));
//					}
//					RootPanel.get().add(table);
//				}
//			});
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		/*****************************************************************************
		 * 		TEST ENDE
		 * **************************************************************************/
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
//				RootPanel.get().add(new FindLove());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}