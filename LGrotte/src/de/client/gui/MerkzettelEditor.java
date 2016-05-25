package de.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MerkzettelEditor extends VerticalPanel{
	
	private VerticalPanel vpanel = new VerticalPanel(); 
	private FlexTable flexTable = new FlexTable();
	private Button anzeigeButton = new Button("anzeigen");
	private Button loeschButton = new Button("x");
	
	private ListBox suchprofilListBox = new ListBox();
	
	private Label profilLabel = new Label("ProfilName");
	private Label ueWertLabel = new Label("in %");
	
	
	
	public MerkzettelEditor(){
		flexTable.setWidget(0, 0, profilLabel);
		flexTable.setWidget(0, 1, ueWertLabel);	
		flexTable.setWidget(0, 2, anzeigeButton);
		flexTable.setWidget(0, 3, loeschButton);
		
		vpanel.add(suchprofilListBox);
		vpanel.add(flexTable);
	}
	
}
