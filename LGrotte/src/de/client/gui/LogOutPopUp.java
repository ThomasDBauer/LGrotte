package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.client.LGrotte;

public class LogOutPopUp extends PopupPanel {
		private VerticalPanel popupPanel = new VerticalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();
		public final Button jaButton = new Button(
				"Ja", new LogOutClickHandler());
		public final Button neinButton = new Button("Nein", new HideClickHandler());
		private Label logOutText = new Label("Möchtest du dich wirklich abmelden?");

		public LogOutPopUp() {
			super(true);
			jaButton.setStylePrimaryName("logout-ja");
			neinButton.setStylePrimaryName("logout-nein");
			logOutText.setStylePrimaryName("logout-Text");
			popupPanel.add(logOutText);
			buttonPanel.add(jaButton);
			buttonPanel.add(neinButton);
			popupPanel.add(buttonPanel);
			setWidget(popupPanel);
		}
		
		public class HideClickHandler implements ClickHandler{
			public void onClick(ClickEvent e) {
				
				LogOutPopUp hideIt= LGrotte.getLogOutPop();
				hideIt.hide();
				LGrotte.setLogOutPop(hideIt);
			}
		}

		private class LogOutClickHandler implements ClickHandler {
			public void onClick(ClickEvent e) {
				Window.open(LGrotte.logOutUrl, "_self", "");
			}
		}

		
}
