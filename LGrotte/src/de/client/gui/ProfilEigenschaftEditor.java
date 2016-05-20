package de.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilEigenschaftEditor extends VerticalPanel {

	private VerticalPanel panel = this;
	
	private HorizontalPanel eigenschaftPanel = new HorizontalPanel();
	private HorizontalPanel infoPanel = new HorizontalPanel();
	
	private Label ProfilErweiternLabel = new Label("Profil erweitern");
	
	private TextBox eigenschaftTextBox = new TextBox();
	private TextBox InfoTextBox = new TextBox();
	
	private Button addEigenschaftButton = new Button("Eigenschaft hinzufügen");
	private Button addInfoButton = new Button("Info hinzufügen");
	
	private ListBox auswahlEigenschaftBox = new ListBox();
	
	ProfilEigenschaftEditor(){
		auswahlEigenschaftBox.addItem("Huhu");
		auswahlEigenschaftBox.addItem("hihi");
		
		this.add(ProfilErweiternLabel);
		this.add(eigenschaftPanel);
		eigenschaftPanel.add(eigenschaftTextBox);
		eigenschaftPanel.add(addEigenschaftButton);
		this.add(infoPanel);
		infoPanel.add(auswahlEigenschaftBox);
		infoPanel.add(InfoTextBox);
		infoPanel.add(addInfoButton);
		
		
	}
	
	
}
