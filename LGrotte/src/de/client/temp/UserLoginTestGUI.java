package de.client.temp;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;

public class UserLoginTestGUI extends VerticalPanel{
	private VerticalPanel panel = this;
	
	public UserLoginTestGUI(){
		ClientSideSettings.getLoginService().hallo(new AsyncCallback<String>(){
			public void onFailure(Throwable caught) {
				panel.add(new Label(caught.toString()));
			}
			public void onSuccess(String result) {
				panel.add(new Label(result));
			}
		});
	}
	
}
