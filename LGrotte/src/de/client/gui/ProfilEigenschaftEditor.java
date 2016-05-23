package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Eigenschaft;

public class ProfilEigenschaftEditor extends VerticalPanel {

	private VerticalPanel vpanel = this;
	private Button addEigenschaftenButton = new Button("+");
	private ListBox eigenschaftenListbox = new ListBox(false);
	
	public ProfilEigenschaftEditor(){
		this.add(addEigenschaftenButton);
		
	}
	
	private class AddEigenschaftenClickHandler{
		public void onClick(ClickEvent e) throws Exception{
			ClientSideSettings.getEditorService().getEigenschaften(new AddEigenschaftenCallback());
		}
	}
	
	private class AddEigenschaftenCallback implements AsyncCallback <Vector<Eigenschaft>>{
		public void onFailure(Throwable caught) {
			
		}
		public void onSuccess(Vector<Eigenschaft> result) {
			for(int i = 0; i < result.size(); i++){
				eigenschaftenListbox.addItem(result.elementAt(i).getErlaeuterung());
			}
		}
		
	}
	
	
	
	/*private VerticalPanel vPanel = this;
	private Button eigenschaftHinzufügenButton = new Button("+");
	private Button speichernButton = new Button("speichern");
	
	private ListBox eigenschaftenListBox = new ListBox();
	
	private TextBox infoObjektTextBox = new TextBox();
	
	
	ProfilEigenschaftEditor(){	
		vPanel.add(eigenschaftHinzufügenButton);
		eigenschaftHinzufügenButton.addClickHandler(new eigenschaftAnzeigenClickHandler());
	}
	private class eigenschaftAnzeigenClickHandler implements ClickHandler {
		
		public void onClick(ClickEvent event) {
		    vPanel.add(eigenschaftenListBox);
			for(int i = 0; i < ClientSideSettings.getEditorService(). )
			vPanel.add(speichernButton);
			
		}
		
	}*/
	
}
