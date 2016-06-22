package de.client.gui;

import java.util.Date;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;

import de.client.ClientSideSettings;


public class ProfilErstellenEditor extends VerticalPanel{
	private VerticalPanel panel = new VerticalPanel();
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
	private DateTimeFormat datumsFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	private Label datumsinhalt = new Label();
	private Label geburtsdatumLabel = new Label ("Geburtsdatum");
	
	private Label fNameLabel = new Label("Vorname");
	private Label lNameLabel = new Label("Nachname");
	
	private DateBox datumsBox = new DateBox();
	
	private Button profilAnlegenButton = new Button("Profil erstellen");
	
	public ProfilErstellenEditor(){
		flexTable.setWidget(0, 1, fNameTextBox);
		flexTable.setWidget(0, 0, fNameLabel);
		
		flexTable.setWidget(1, 1, lNameTextBox);
		flexTable.setWidget(1, 0, lNameLabel);
		
		flexTable.setWidget(2, 1, geschlechtListBox);
		flexTable.setWidget(2, 0, geschlechtLabel);
		
		flexTable.setWidget(3, 1, haarfarbeListBox);
		flexTable.setWidget(3, 0, haarfarbeLabel);
		
		flexTable.setWidget(4, 1, koerpergroesseTextBox);
		flexTable.setWidget(4, 0, koerpergroesseLabel);
		
		flexTable.setWidget(5, 1, religionListBox);
		flexTable.setWidget(5, 0, religionLabel);
		
		flexTable.setWidget(6, 1, raucherListBox);
		flexTable.setWidget(6, 0, raucherLabel);
		
		
		flexTable.setWidget(7, 1, datumsBox);
		flexTable.setWidget(7, 0, geburtsdatumLabel);
	
		
		datumsBox.setFormat(new DateBox.DefaultFormat(datumsFormat));
	    datumsBox.getDatePicker().setYearAndMonthDropdownVisible(true);
		datumsBox.getDatePicker().setVisibleYearCount(20);
		datumsBox.addValueChangeHandler(new ValueChangeHandler<Date>() {
			public void onValueChange(ValueChangeEvent<Date> event) {
				Date datum = event.getValue();
				String datumsString = DateTimeFormat.getFormat("yyyy-MM-dd").format(datum);
				datumsinhalt.setText(datumsString);
			}
		});
		
		datumsBox.setValue(new Date());
		
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
		
	
		panel.add(flexTable);
		profilAnlegenButton.setStylePrimaryName("grotte-Button");
		panel.add(profilAnlegenButton);
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
	Date getGeburtsdatum(){
		Date geburtsdatum = datumsFormat.parse(datumsinhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
	}
	// Profil anlegen
	private class ProfilAnlegenCallback implements AsyncCallback{
		public void onFailure(Throwable caught) {
			panel.add(new Label(caught.toString()));
		}
		public void onSuccess(Object result) {
		}
		
	}
	private class ProfilAnlegenClickHandler implements ClickHandler{
		public void onClick(ClickEvent event) {
	ClientSideSettings.getEditorService().insertProfil("fakemail", fNameTextBox.getText(), 
			lNameTextBox.getText(), Integer.parseInt(koerpergroesseTextBox.getText()), 
			getGeschlecht(), getHaarfarbe(), getReligion(), getRaucher(), getGeburtsdatum(), new ProfilAnlegenCallback());
			
		}
	}
	

	
	
	
}

