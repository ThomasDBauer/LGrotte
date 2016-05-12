package de.client.temp;

import java.util.Vector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import de.client.TestService;
import de.client.TestServiceAsync;
import de.shared.BO.Profil;

//Der Button ist egtl ein Panel, dem auch Fehler/Erfolgsmeldungen hinzugefügt werden können
public class GetAllProfilesButton extends VerticalPanel {

	/*
	 * Um auf das Panel in den inneren Klassen Callback und ClickHandler
	 * zuzugreifen, wird eine Instanzvariable gesetzt.
	 */
	private VerticalPanel vpanel = this;

	// Beim Erzeugen der Klasse wird dem Panel direkt der eigentliche Button
	// hinzugefügt.
	public GetAllProfilesButton() {
		this.add(new Button("GetAllProfiles", new SeedHandler()));

	}

	// Der ClickHandler ruft die service-Methode auf.
	private class SeedHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			TestServiceAsync testService = GWT.create(TestService.class);
			try {
				testService.getAllProfiles(new GetAllProfilesCallback());
			} catch (Exception e1) {
				vpanel.add(new Label(e1.toString()));
				e1.printStackTrace();
			}
		}
	}

	private class GetAllProfilesCallback implements AsyncCallback<Vector<Profil>> {
		public void onFailure(Throwable caught) {
			vpanel.add(new Label(caught.toString()));
		}

		public void onSuccess(Vector<Profil> result) {
			for (int i = 0; i < result.size(); i++) {
				vpanel.add(new Label(result.elementAt(i).getFname()));
			}
		}

	}
}
