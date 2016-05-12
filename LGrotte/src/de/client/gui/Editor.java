package de.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.EditorService;
import de.shared.EditorServiceAsync;

public class Editor extends VerticalPanel {
	private VerticalPanel panel = this;
	private FlexTable flexTable = new FlexTable();
	private TextBox fNameTextBox = new TextBox();
	private TextBox lNameTextBox = new TextBox();
	private Label fNameLabel = new Label("Vorname");
	private Label lNameLabel = new Label("Nachname");
	private Button profilAnlegenButton = new Button("Einschreiben");
	
	public Editor(){
		flexTable.setWidget(0, 0, fNameTextBox);
		flexTable.setWidget(0, 1, fNameLabel);
		flexTable.setWidget(1, 0, lNameTextBox);
		flexTable.setWidget(1, 1, lNameLabel);
		this.add(flexTable);
		this.add(profilAnlegenButton);
		profilAnlegenButton.addClickHandler(new ProfilAnlegenClickHandler());
	}
	
	private class ProfilAnlegenCallback implements AsyncCallback{

		@Override
		public void onFailure(Throwable caught) {
			panel.add(new Label(caught.toString()));
			
		}

		@Override
		public void onSuccess(Object result) {
			
			
		}
		
	}
	private class ProfilAnlegenClickHandler implements ClickHandler{

		@Override
		public void onClick(ClickEvent event) {
	ClientSideSettings.getEditorService().insertProfil(fNameTextBox.getText(), lNameTextBox.getText(), "männlich", "Strassenköterblond", 187, "Error", "JA", new ProfilAnlegenCallback());
			
		}
		
	}
	
	
}
