package de.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.google.gwt.user.datepicker.client.DatePicker;

import de.client.ClientSideSettings;
import de.shared.EditorService;
import de.shared.EditorServiceAsync;
import de.shared.BO.Profil;

public class ProfilErstellenEditor extends VerticalPanel {
	private VerticalPanel panel = this;
	private FlexTable flexTable = new FlexTable();

	//Erstellen von TextBoxen
	private TextBox fNameTextBox = new TextBox();
	private TextBox lNameTextBox = new TextBox();
	private TextBox koerpergroesseTextBox = new TextBox();
	private ListBox geschlechtListBox = new ListBox();
	private ListBox haarfarbeListBox = new ListBox();
	private ListBox religionListBox = new ListBox();
	private ListBox raucherListBox = new ListBox();

	//Erstellen von Labeln
	private Label koerpergroesseLabel = new Label("Körpergröße");
	private Label geschlechtLabel = new Label("Geschlecht");
	private Label haarfarbeLabel = new Label("Haarfarbe");
	private Label religionLabel = new Label("Religion");
	private Label raucherLabel = new Label("Raucher");
	private DateTimeFormat datumsFormat = DateTimeFormat.getFormat("yyyy-MM-dd");
	private Label datumsinhalt = new Label();
	private Label geburtsdatumLabel = new Label("Geburtsdatum");
	private Label fNameLabel = new Label("Vorname");
	private Label lNameLabel = new Label("Nachname");

	//Erstellen der DateBox
	private DateBox datumsBox = new DateBox();
	
	//Erstellen des Buttons
	private Button profilUpdateButton = new Button("Speichern");

	//Konstruktor
	public ProfilErstellenEditor() {
		this.addStyleName("Attribute-bearbeiten");
		profilUpdateButton.setStylePrimaryName("grotte-Button");
		koerpergroesseLabel.setStyleName("Profilbearbeiten-Boxen", true);
		geschlechtLabel.setStyleName("Profilbearbeiten-Boxen", true);
		haarfarbeLabel.setStyleName("Profilbearbeiten-Boxen", true);
		religionLabel.setStyleName("Profilbearbeiten-Boxen", true);
		raucherLabel.setStyleName("Profilbearbeiten-Boxen", true);
		geburtsdatumLabel.setStyleName("Profilbearbeiten-Boxen", true);
		datumsinhalt.setStyleName("Profilbearbeiten-Boxen", true);
		fNameLabel.setStyleName("Profilbearbeiten-Boxen", true);
		lNameLabel.setStyleName("Profilbearbeiten-Boxen", true);
		flexTable.setStylePrimaryName("Table-Margin");
		
		Image speicherImage = new Image("speichern.png");
		speicherImage.setStylePrimaryName("Button-img-Image");
		profilUpdateButton.getElement().appendChild(speicherImage.getElement());
		profilUpdateButton.setStylePrimaryName("Button-img");
		
		try {
			ClientSideSettings.getEditorService().getProfil(new ProfilAuslesenCallback());
		} catch (Exception e) {
			e.printStackTrace();
		}

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
		koerpergroesseTextBox.setVisibleLength(3);
		koerpergroesseTextBox.setMaxLength(3);

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

		//Befüllen der List Box
		geschlechtListBox.addItem("Männlich");
		geschlechtListBox.addItem("Weiblich");
		geschlechtListBox.addItem("Andere");

		haarfarbeListBox.addItem("Blond");
		haarfarbeListBox.addItem("Brunette");
		haarfarbeListBox.addItem("Schwarz");
		haarfarbeListBox.addItem("Rot");
		haarfarbeListBox.addItem("Grau");
		haarfarbeListBox.addItem("Andere");

		religionListBox.addItem("Christlich");
		religionListBox.addItem("Muslimisch");
		religionListBox.addItem("Buddhistisch");
		religionListBox.addItem("Hinduistisch");
		religionListBox.addItem("Jüdisch");
		religionListBox.addItem("Keine");
		religionListBox.addItem("Andere");

		raucherListBox.addItem("Ja");
		raucherListBox.addItem("Nein");
		raucherListBox.addItem("Ab und an");

		//Anfügen der FlexTable und Buttons an das Panel
		this.add(profilUpdateButton);
		this.add(flexTable);
		profilUpdateButton.addClickHandler(new ProfilUpdateClickHandler());
	}

	//Getter für Attribute
	String getGeschlecht() {
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

	Date getGeburtsdatum() {
		Date geburtsdatum = datumsFormat.parse(datumsinhalt.getText());
		java.sql.Date sqlDate = new java.sql.Date(geburtsdatum.getTime());
		return sqlDate;
	}

	// Profil updaten
	private class ProfilUpdateCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			panel.add(new Label("Fehler!!" + caught.toString()));
		}

		public void onSuccess(Object result) {
			RootPanel.get("Zusatz").clear();
			RootPanel.get("Inhalt").clear();
			RootPanel.get("Content").clear();
			RootPanel.get("Inhalt").add(new HTML("<h2>Dein Profil</h2>"));
			RootPanel.get("Inhalt").add(new ProfilAnzeigenEditor());
		}

	}

	private class ProfilUpdateClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			Profil email = new Profil();
				try {
					ClientSideSettings.getEditorService().updateProfil(fNameTextBox.getText(), lNameTextBox.getText(),
							Integer.parseInt(koerpergroesseTextBox.getText()), getGeschlecht(), getReligion(),
							getHaarfarbe(), getRaucher(), getGeburtsdatum(), email.getEmail(), new ProfilUpdateCallback());
				} catch (Exception e) {
					Window.alert("catch " + e.toString());
					e.printStackTrace();
				}
			String input = fNameTextBox.getText();
			if (input.matches("")) {
				Window.alert("'" + fNameTextBox.getText() + 
						"'beinhaltet ein ungültiges Symbol oder kein Symbol");
				return;
			}
			String input1 = lNameTextBox.getText();
			if (input1.matches("")) {
				Window.alert("'" + lNameTextBox.getText() + 
						"'beinhaltet ein ungültiges Symbol oder kein Symbol");
				return;
			}
			String input2 = koerpergroesseTextBox.getText();
			if (!input2.matches("[0-9]*") || input2.matches("")) {
				Window.alert(
						"'" + koerpergroesseTextBox.getText() +
						"'beinhaltet ein ungültiges Symbol oder kein Symbol");
				return;
			}
		}
	}
	
	// Profil auslesen
	private class ProfilAuslesenCallback implements AsyncCallback<Profil> {
		public void onFailure(Throwable caught) {
			RootPanel.get().add(new Label(caught.toString()));
		}

		public void onSuccess(Profil result) {

			if (result.getFname() == "null") {
				fNameTextBox.setText("");
				lNameTextBox.setText("");
			} else {
				fNameTextBox.setText(result.getFname());
				lNameTextBox.setText(result.getLname());
			}

			for (int g = 0; g < 3; g++) {
				if (geschlechtListBox.getValue(g) == result.getGeschlecht()) {
					geschlechtListBox.setSelectedIndex(g);
				}
			}
			for (int hf = 0; hf < 5; hf++) {
				if (haarfarbeListBox.getValue(hf) == result.getHaarfarbe()) {
					haarfarbeListBox.setSelectedIndex(hf);
				}
			}
			if (result.getKoerpergroesse() == 0) {
				koerpergroesseTextBox.setText("");
			} else {
				koerpergroesseTextBox.setText(Integer.toString(result.getKoerpergroesse()));
			}
			for (int re = 0; re < 5; re++) {
				if (religionListBox.getValue(re) == result.getReligion()) {
					religionListBox.setSelectedIndex(re);
				}
			}
			for (int ra = 0; ra < 3; ra++) {
				if (raucherListBox.getValue(ra) == result.getRaucher()) {
					raucherListBox.setSelectedIndex(ra);
				}
			}
			datumsBox.setValue(result.getGeburtsdatum());

		}

}

}