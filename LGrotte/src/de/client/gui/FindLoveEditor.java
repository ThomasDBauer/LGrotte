package de.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FindLoveEditor extends VerticalPanel {
	
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	private VerticalPanel vPanel = new VerticalPanel();

	private Label suchprofilLabel = new Label("Nach was suchen Sie?");
	private Label matchFaktorLabel = new Label ("Übereinstimmung");
	private Label profilNameLabel = new Label ("Name");
	private Label goLabel = new Label ("go");
	private Label noLabel = new Label ("no");
	
	
	private ListBox auswahlSuchprofilBox = new ListBox();
	
	private Button sucheStartenButton = new Button();
	
	private FlexTable anzeigeTabelle = new FlexTable();
	
	
	
	
	public FindLoveEditor(){
		anzeigeTabelle.setWidget(0, 0, matchFaktorLabel);
		anzeigeTabelle.setWidget(0, 1, profilNameLabel);
		anzeigeTabelle.setWidget(0, 2, goLabel);
		anzeigeTabelle.setWidget(0, 3, noLabel);
		
		hPanel.add(suchprofilLabel);
		hPanel.add(auswahlSuchprofilBox);
		hPanel.add(sucheStartenButton);
		
		vPanel.add(anzeigeTabelle);
	}
	
	
}
