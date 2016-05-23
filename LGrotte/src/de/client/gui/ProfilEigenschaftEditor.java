package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;

public class ProfilEigenschaftEditor extends VerticalPanel {

	private Button addEigenschaftenButton = new Button("+");
	
	
	private class AddEigenschaftenClickHandler{
		public void onClick(ClickEvent e){
			
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
