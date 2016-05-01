package de.client.temp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.GreetingService;
import de.client.GreetingServiceAsync;

public class SeedButton extends VerticalPanel {

	private VerticalPanel vpanel = this;	
	
	public SeedButton(){
		this.add(new Button("Seed", new SeedHandler()));
		
	}

	private class SeedHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
			try {
				greetingService.seed(new SeedCallback());
			} catch (Exception e1) {
				vpanel.add(new Label(e1.toString()));
				e1.printStackTrace();
			}
		}
	}

	// die onClick ruft eine service-Methode auf, die ein Callback braucht
	private class SeedCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			 vpanel.add(new Label(caught.toString()));
		}

		public void onSuccess(Object result) {
			 vpanel.add(new Label("onSuccess@callback"));
		}
	}
}
