package de.client.gui;

import org.eclipse.jdt.core.dom.ThisExpression;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SuchprofilEditor extends VerticalPanel{
	
	private VerticalPanel vPanel = this;
	private HorizontalPanel hPanel = new HorizontalPanel();
	// Button zum Suchprofil anlegen und deren Inhalt wenn er geklickt wird
	private Button spAnlegenButton = new Button("Anlegen");
	private FlexTable anlegenTable = new FlexTable();
	
	private Label spNameLabel = new Label("Benenne dein Suchprofil:");
	private TextBox spNameTextBox = new TextBox();
	
	private Label geschlechtLabel = new Label("Geschlecht:");
	private ListBox geschlechtListBox = new ListBox();
	
	private Label raucherLabel = new Label("Raucher:");
	private ListBox raucherListBox = new ListBox();
	
	private Label haarfarbeLabel = new Label("Haarfarbe:");
	private ListBox haarfarbeListBox = new ListBox();
	
	private Label koerpergLabel = new Label("Körpergröße:");
	private TextBox koerpergTextBox = new TextBox();
	
	private Label religionLabel = new Label("Religion:");
	private ListBox religionListBox = new ListBox();
	
	private Label bdayLabel = new Label("Geburtsdatum:");
	// box für bday
	
	private Button anlegenButton = new Button("Anlegen");
	
	// Button zum Suchprofil löschen und dessen Inhalt wenn er gelöscht wird
	private Button spLoeschenButton = new Button("Löschen");
	private FlexTable loeschenTable = new FlexTable();
	
	private Label aussuchenLabel = new Label("Wählen Sie bitte das zu löschende Suchprofil aus:");
	private ListBox spListBox = new ListBox();
	private Button loeschenButton = new Button("Löschen");
	
	
	public SuchprofilEditor() {
	spAnlegenButton.addClickHandler(new SuchprofilAnlegenClickHandler());
	spLoeschenButton.addClickHandler(new SuchprofilLoeschenClickHandler());
	hPanel.add(spAnlegenButton);
	hPanel.add(spLoeschenButton);
	this.add(hPanel);
	}
	

	private class SuchprofilAnlegenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {	
		
			// Der FlexTable unsere Labels und Listboxen geben
			anlegenTable.setWidget(0, 0, spNameLabel);
			anlegenTable.setWidget(0, 1, spNameTextBox);
			
			anlegenTable.setWidget(1, 0, geschlechtLabel);
			anlegenTable.setWidget(1, 1, geschlechtListBox);
			
			anlegenTable.setWidget(2, 0, raucherLabel);
			anlegenTable.setWidget(2, 1, raucherListBox);
			
			anlegenTable.setWidget(3, 0, haarfarbeLabel);
			anlegenTable.setWidget(3, 1, haarfarbeListBox);
			
			anlegenTable.setWidget(4, 0, koerpergLabel);
			anlegenTable.setWidget(4, 1, koerpergTextBox);
			
			anlegenTable.setWidget(5, 0, religionLabel);
			anlegenTable.setWidget(5, 1, religionListBox);
			
			anlegenTable.setWidget(6, 0, bdayLabel);
			// table bday
			
			// anhängen der Items zur Auswahl
			geschlechtListBox.addItem("männlich");
			geschlechtListBox.addItem("weiblich");
			geschlechtListBox.addItem("Andere");
			
			haarfarbeListBox.addItem("blond");
			haarfarbeListBox.addItem("brunette");
			haarfarbeListBox.addItem("schwarz");
			haarfarbeListBox.addItem("rot");
			haarfarbeListBox.addItem("grau");
			haarfarbeListBox.addItem("egal");
			
			religionListBox.addItem("christlich");
			religionListBox.addItem("muslimisch");
			religionListBox.addItem("buddhistisch");
			religionListBox.addItem("hinduitsisch");
			religionListBox.addItem("jüdisch");
			religionListBox.addItem("egal");
			
			raucherListBox.addItem("Ja");
			raucherListBox.addItem("Nein");
			raucherListBox.addItem("ab und an");
			raucherListBox.addItem("egal");
			
			// anheften an Panels
			vPanel.add(anlegenTable);
			vPanel.add(anlegenButton);
			
		}
	}
	
	private class SuchprofilLoeschenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			
			//Ankleben an FlexTable 
			loeschenTable.setWidget(0, 0, aussuchenLabel);
			loeschenTable.setWidget(1, 0, spListBox);
			// Hier muss die Box der zu auswählenden Profile noch hin
			
			// Panels
			vPanel.add(loeschenTable);
			vPanel.add(loeschenButton);
		
		}	
	}
	
	
	
}
