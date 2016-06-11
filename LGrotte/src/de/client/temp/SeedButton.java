package de.client.temp;

import java.util.Vector;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.client.TestService;
import de.client.TestServiceAsync;
import de.shared.BO.Info;
import de.shared.BO.Suchprofil;


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
			
			TestServiceAsync testService = GWT.create(TestService.class);
			try {
				testService.seed(new SeedCallback());
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
//			Suchprofil sp = new Suchprofil();
//			sp.setSuchprofilname("KleineDickeRaucherinnen");
//			
//			
//			Info info = new Info();
//			info.setEigenschaft(2);
//			info.setValue("Behindert Tanzen");
//			
//			try {
//				ClientSideSettings.getEditorService().insertSuchprofilInfo(sp, info, new AsyncCallback(){
//					public void onFailure(Throwable caught) {
//						RootPanel.get().add(new Label("SuchprofilInfoSeeds@insert" + caught.toString()));
//					}
//					public void onSuccess(Object result) {
//					}
//				});
//			} catch (Exception e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
	}
}
