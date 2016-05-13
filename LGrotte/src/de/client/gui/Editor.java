package de.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
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
	private TextBox koerpergroesseTextBox= new TextBox();
	
	private ListBox geschlechtListBox = new ListBox();
	private ListBox haarfarbeListBox = new ListBox();
	private ListBox religionListBox = new ListBox();
	private ListBox raucherListBox = new ListBox();
	
	private Label koerpergroesseLabel = new Label("Körpergröße");
	private Label geschlechtLabel = new Label("Geschlecht");
	private Label haarfarbeLabel = new Label("Haarfarbe");
	private Label religionLabel = new Label("Religion");
	private Label raucherLabel = new Label("Raucher");
	
	private Label fNameLabel = new Label("Vorname");
	private Label lNameLabel = new Label("Nachname");
	private Button profilAnlegenButton = new Button("Einschreiben");
	
	
	public Editor(){
		flexTable.setWidget(0, 0, fNameTextBox);
		flexTable.setWidget(0, 1, fNameLabel);
		
		flexTable.setWidget(1, 0, lNameTextBox);
		flexTable.setWidget(1, 1, lNameLabel);
		
		flexTable.setWidget(2, 0, geschlechtListBox);
		flexTable.setWidget(2, 1, geschlechtLabel);
		
		flexTable.setWidget(3, 0, haarfarbeListBox);
		flexTable.setWidget(3, 1, haarfarbeLabel);
		
		flexTable.setWidget(4, 0, koerpergroesseTextBox);
		flexTable.setWidget(4, 1, koerpergroesseLabel);
		
		flexTable.setWidget(5, 0, religionListBox);
		flexTable.setWidget(5, 1, religionLabel);
		
		flexTable.setWidget(6, 0, raucherListBox);
		flexTable.setWidget(6, 1, raucherLabel);
		
		geschlechtListBox.addItem("männlich");
		geschlechtListBox.addItem("weiblich");
		geschlechtListBox.addItem("Andere");
		
		haarfarbeListBox.addItem("blond");
		haarfarbeListBox.addItem("brunette");
		haarfarbeListBox.addItem("schwarz");
		haarfarbeListBox.addItem("rot");
		haarfarbeListBox.addItem("grau");
		
		religionListBox.addItem("christlich");
		religionListBox.addItem("muslimisch");
		religionListBox.addItem("buddhistisch");
		religionListBox.addItem("hinduitsisch");
		religionListBox.addItem("jüdisch");
		
		raucherListBox.addItem("Ja");
		raucherListBox.addItem("Nein");
		raucherListBox.addItem("ab und an");
		
	
		this.add(flexTable);
		
		this.add(profilAnlegenButton);
		profilAnlegenButton.addClickHandler(new ProfilAnlegenClickHandler());
	}
	
	String getGeschlecht(){
	String geschlecht = geschlechtListBox.getItemText(geschlechtListBox.getSelectedIndex());
	return geschlecht;
	}
	String getHaarfarbe() {
	String haarfarbe = haarfarbeListBox.getItemText(haarfarbeListBox.getSelectedIndex());
	return haarfarbe;
	}
	String getReligion() {
	String religion = religionListBox.getItemText(religionListBox.getSelectedIndex());
	return religion;
	}
	String getRaucher() {
	String raucher = raucherListBox.getItemText(raucherListBox.getSelectedIndex());
	return raucher;
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
	ClientSideSettings.getEditorService().insertProfil(fNameTextBox.getText(), lNameTextBox.getText(), getGeschlecht() , getHaarfarbe() , Integer.parseInt(koerpergroesseTextBox.getText()), getReligion(), getRaucher(), new ProfilAnlegenCallback());
			
		}
		
	}
	
	
}
