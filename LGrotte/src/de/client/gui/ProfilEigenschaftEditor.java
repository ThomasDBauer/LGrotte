package de.client.gui;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ProfilEigenschaftEditor extends VerticalPanel {

	private VerticalPanel panel = this;
	
	private Label ProfilErweiternLabel = new Label("Profil erweitern");
	
	private ListBox auswahlEigenschaftBox = new ListBox();
	
	ProfilEigenschaftEditor(){
		auswahlEigenschaftBox.addItem("Huhu");
		auswahlEigenschaftBox.addItem("hihi");
		
		this.add(ProfilErweiternLabel);
		this.add(auswahlEigenschaftBox);
		
	}
}
