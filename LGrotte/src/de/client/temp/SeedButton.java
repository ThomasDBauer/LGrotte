package de.client.temp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.TestService;
import de.client.TestServiceAsync;


//Die Klasse SeedButton ist ein Panel, in dem auch Fehlermeldungen angezeigt werden.
public class SeedButton extends VerticalPanel {
	
	//eine ä#nderung. verrückt
	
	/* Um auf das Panel in den inneren Klassen Callback und ClickHandler zuzugreifen,
	 * wird eine Instanzvariable gesetzt.
	 */
	private VerticalPanel vpanel = this;	
	
	//Beim Erzeugen der Klasse wird dem Panel direkt der eigentliche Button hinzugefügt.
	public SeedButton(){
		this.add(new Button("Seed", new SeedHandler()));
		
	}
	
	//Der ClickHandler ruft die service-Methode auf.
	private class SeedHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			TestServiceAsync greetingService = GWT.create(TestService.class);
			try {
				greetingService.seed(new SeedCallback());
			} catch (Exception e1) {
				vpanel.add(new Label(e1.toString()));
				e1.printStackTrace();
			}
		}
	}

	// Das Callback, das für die service-Methode des ClickHandlers benötigt wird:
	private class SeedCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			 vpanel.add(new Label(caught.toString()));
		}

		public void onSuccess(Object result) {
			 vpanel.add(new Label("onSuccess@callback"));
		}
	}
}
