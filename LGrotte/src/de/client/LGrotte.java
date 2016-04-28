package de.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class LGrotte implements EntryPoint {

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public void onModuleLoad() {
		
		
		Button hallo = new Button("hallo", new ClickHandler(){
			public void onClick(ClickEvent e){
				try {
					greetingService.seed(new AsyncCallback(){
						public void onFailure(Throwable caught) {
							RootPanel.get().add(new Label(caught.toString()));
						}
						public void onSuccess(Object result) {
							RootPanel.get().add(new Label("juhuuu"));
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		RootPanel.get().add(hallo);
	}
}
