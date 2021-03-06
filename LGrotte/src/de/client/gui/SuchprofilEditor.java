package de.client.gui;
import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Suchprofil;

/**
 * GUI Klasse die das VerticalPanel erbt
 * 
 * @author Enrico Popaj, Nicolai Ehrmanntraut und Lukas Kircher
 *
 * @version 1.0
 */

public class SuchprofilEditor extends VerticalPanel {
	/**
	 * Verschiedene Panels werden instanziert
	 */
		private VerticalPanel suchprofilPanel = this;
		private HorizontalPanel buttonPanel =  new HorizontalPanel();
		private HorizontalPanel loeschenPanel = new HorizontalPanel();
		private HorizontalPanel updatePanel = new HorizontalPanel();
		private HorizontalPanel speichernButtonPanel = new HorizontalPanel();
		private HorizontalPanel listBoxPanel = new HorizontalPanel();
		
		/**
		 * Buttons uns Labels werden erstellt
		 */
		private ListBox spListBox = new ListBox();
		private Button spHinzufuegenButton = 
				new Button("Suchprofil hinzuf&uumlgen", 
				new SuchprofilHinzufuegenClickHandler());
		private Button loeschenButton = new Button("L&oumlschen", 
				new DeleteSuchprofilClickHandler());
		private Button speichernButton = new Button("Speichern", 
				new UpdateSuchprofilClickHandler());
		private Label keinSPLabel = 
				new Label("Hey erstell doch ein Suchprofil!");
		private Button abbrechenButton = new Button("Abbrechen", 
				new SuchProfilAbbrechenClickHandler());
		/**
		 * Anzeiegn Tabelle wird erstellt zusammen mit verschieden Labels
		 */
		private FlexTable anzeigenTable = new FlexTable();
		private HorizontalPanel groessenAnzeigenPanel = new HorizontalPanel();
		private HorizontalPanel alterAnzeigenPanel = new HorizontalPanel();
		private TextBox minGroesseAnzeigenTextBox = new TextBox();
		private TextBox maxGroesseAnzeigenTextBox= new TextBox();
		private TextBox minAlterAnzeigenTextBox = new TextBox();
		private TextBox maxAlterAnzeigenTextBox = new TextBox();
		
		/**
		 * Buttons, Labels und Table für die Suchprofilmaske hinzufügen
		 */
		private FlexTable anlegenTable = new FlexTable();

		private Label spNameLabel = new Label("Name");
		private TextBox spNameTextBox = new TextBox();

		private Label geschlechtLabel = new Label("Geschlecht");
		private ListBox geschlechtListBox = new ListBox();

		private Label raucherLabel = new Label("Raucher");
		private ListBox raucherListBox = new ListBox();

		private Label haarfarbeLabel = new Label("Haarfarbe:");
		private ListBox haarfarbeListBox = new ListBox();

		private HorizontalPanel groessenPanel = new HorizontalPanel();
		private Label koerpergLabel = new Label("Körpergröße(cm)");
		private TextBox minGroesseTextBox = new TextBox();
		private TextBox maxGroesseTextBox= new TextBox();
	

		private Label religionLabel = new Label("Religion");
		private ListBox religionListBox = new ListBox();
	
		
		private HorizontalPanel alterPanel = new HorizontalPanel();
		private Label alterLabel = new Label("Alter");
		private TextBox minAlterTextBox = new TextBox();
		private TextBox maxAlterTextBox = new TextBox();
		private Label von = new Label("von");
		private Label bis = new Label("bis");
		
		private Button anlegenButton = new Button("Speichern", 
				new SuchProfilAnlegenClickHandler());

		private SuchprofilInfoEditor eigenschaftenEditor = 
				new SuchprofilInfoEditor();
		
		/**
		 * Konstruktor des Suchprofils
		 * @param spie
		 */
		public SuchprofilEditor(SuchprofilInfoEditor spie) throws Exception {
			this.eigenschaftenEditor = spie;
			
			/*
			 * Den Buttons uns Labels wird ein CSS-Sytle hinzugefügt
			 */
			Image hinzImage = new Image("hinzufuegen.png"); 
			hinzImage.setStylePrimaryName("Button-img-Image");
			spHinzufuegenButton.getElement().
			appendChild(hinzImage.getElement());
			spHinzufuegenButton.setStylePrimaryName("Button-img");
			spHinzufuegenButton.setStyleName("Margin-Bottom", true);
			
			Image loeschenImage = new Image("loeschen.png"); 
			loeschenImage.setStylePrimaryName("Button-img-Image");
			loeschenButton.getElement().
			appendChild(loeschenImage.getElement());
			loeschenButton.setStylePrimaryName("Button-img");
			loeschenButton.setStyleName("Margin-Bottom", true);
			
			speichernButton.setStylePrimaryName("grotte-Button");
			Image updateImage = new Image("speichern.png");
			updateImage.setStylePrimaryName("Button-img-Image");
			speichernButton.getElement().
			appendChild(updateImage.getElement());
			speichernButton.setStylePrimaryName("Button-img");
			speichernButton.setStyleName("Margin-Bottom", true);
			
			anlegenButton.setStylePrimaryName("grotte-Button");
			Image speicherImage = new Image("speichern.png"); 
			speicherImage.setStylePrimaryName("Button-img-Image");
			anlegenButton.getElement().
			appendChild(speicherImage.getElement());
			anlegenButton.setStylePrimaryName("Button-img");
			anlegenButton.setStyleName("Margin-Bottom", true);
			
			abbrechenButton.setStyleName("grotte-Button");
			Image abbrImage = new Image("abbrechen.png");
			abbrImage.setStylePrimaryName("Button-img-Image");
			abbrechenButton.getElement().
			appendChild(abbrImage.getElement());
			abbrechenButton.setStylePrimaryName("Button-img");
			abbrechenButton.setStyleName("Margin-Bottom", true);
			
			von.setStylePrimaryName("Text-Box-Connector");
			bis.setStylePrimaryName("Text-Box-Connector");
			RootPanel.get().add(von);
			spNameLabel.setStyleName("Profilbearbeiten-Boxen", true); 
			geschlechtLabel.setStyleName("Profilbearbeiten-Boxen", true);
			raucherLabel.setStyleName("Profilbearbeiten-Boxen", true);
			haarfarbeLabel.setStyleName("Profilbearbeiten-Boxen", true);
			koerpergLabel.setStyleName("Profilbearbeiten-Boxen", true);
			
			/*
			 * Click- und ChangeHandler für ListBox damit wir keinen Anzeigen Button brauchen
			 */
			anlegenTable.clear();
			spListBox.addClickHandler(new SuchProfilAnzeigenClickHandler());
			spListBox.addChangeHandler(new ChangeHandler(){
				public void onChange(ChangeEvent event) {
					spListBox.addClickHandler
					(new SuchProfilAnzeigenClickHandler());
				}
			});
			
			/*
			 *  Anhängen der Panels
			 */
			buttonPanel.add(spHinzufuegenButton);
			listBoxPanel.add(spListBox);
			this.add(buttonPanel);
			this.add(listBoxPanel);
			
			/*
			 * Anhängen der Items zur Auswahl
			 */
			geschlechtListBox.addItem("Männlich");
			geschlechtListBox.addItem("Weiblich");
			geschlechtListBox.addItem("Andere");
			geschlechtListBox.addItem("Egal");
			
			haarfarbeListBox.addItem("Blond");
			haarfarbeListBox.addItem("Brunette");
			haarfarbeListBox.addItem("Schwarz");
			haarfarbeListBox.addItem("Rot");
			haarfarbeListBox.addItem("Grau");
			haarfarbeListBox.addItem("Andere");
			haarfarbeListBox.addItem("Egal");
			
			religionListBox.addItem("Christlich");
			religionListBox.addItem("Muslimisch");
			religionListBox.addItem("Buddhistisch");
			religionListBox.addItem("Hinduistisch");
			religionListBox.addItem("Jüdisch");
			religionListBox.addItem("Andere");
			religionListBox.addItem("Keine");
			religionListBox.addItem("Egal");
			
			raucherListBox.addItem("Ja");
			raucherListBox.addItem("Nein");
			raucherListBox.addItem("Ab und an");
			raucherListBox.addItem("Egal");
			
			loadPage();
		}
		
	
		/**
		 * loadPage Methode die unseren Browser automatisch aktualisiert
		 * un im Konstruktor aufgerufen wird
		 */
		public void loadPage(){
			listBoxPanel.clear();
			buttonPanel.clear();
			spListBox.clear();
			buttonPanel.add(spHinzufuegenButton);
			listBoxPanel.add(spListBox);
			
			try {
				ClientSideSettings.getEditorService().getSuchprofile(
						new GetSuchprofileCallback());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 *  ClickHandler zum Suchprofil hizufügen Funktion
		 */
		private class SuchprofilHinzufuegenClickHandler 
		implements ClickHandler {
			public void onClick(ClickEvent event) {
				keinSPLabel.setText("");
				spHinzufuegenButton.setVisible(false);
				anzeigenTable.clear();
				groessenPanel.clear();
				alterPanel.clear();
				updatePanel.clear();
				loeschenPanel.clear();
				listBoxPanel.clear();
				buttonPanel.add(speichernButtonPanel);
				speichernButtonPanel.add(abbrechenButton);
				speichernButtonPanel.add(anlegenButton);
				geschlechtListBox.setSelectedIndex(0);
				raucherListBox.setSelectedIndex(0);
				haarfarbeListBox.setSelectedIndex(0);
				religionListBox.setSelectedIndex(0);
				
				
				RootPanel.get("Zusatz").clear();
				
				/*
				 *  Der FlexTable unsere Labels und Listboxen geben
				 */
				anlegenTable.setWidget(0, 0, spNameLabel);
				anlegenTable.setWidget(0, 1, spNameTextBox);

				anlegenTable.setWidget(1, 0, geschlechtLabel);
				anlegenTable.setWidget(1, 1, geschlechtListBox);
				
				anlegenTable.setWidget(2, 0, raucherLabel);
				anlegenTable.setWidget(2, 1, raucherListBox);

				anlegenTable.setWidget(3, 0, haarfarbeLabel);
				anlegenTable.setWidget(3, 1, haarfarbeListBox);

				anlegenTable.setWidget(5, 0, koerpergLabel);
				
				groessenPanel.add(new Label("von"));
				groessenPanel.getWidget(0).
				setStylePrimaryName("Text-Box-Connector");
				groessenPanel.add(minGroesseTextBox);
				groessenPanel.add(new Label("bis"));
				groessenPanel.getWidget(2).
				setStylePrimaryName("Text-Box-Connector");
				groessenPanel.add(maxGroesseTextBox);
				anlegenTable.setWidget(5, 1, groessenPanel);
				
				anlegenTable.setWidget(4, 0, religionLabel);
				anlegenTable.setWidget(4, 1, religionListBox);
				
				minGroesseTextBox.setVisibleLength(3);
				minGroesseTextBox.setMaxLength(3);
				maxGroesseTextBox.setVisibleLength(3);
				maxGroesseTextBox.setMaxLength(3);

				anlegenTable.setWidget(6, 0, alterLabel);
				alterPanel.add(von);
				alterPanel.add(minAlterTextBox);
				alterPanel.add(bis);
				alterPanel.add(maxAlterTextBox);
				anlegenTable.setWidget(6, 1, alterPanel);
				
				minAlterTextBox.setVisibleLength(3);
				minAlterTextBox.setMaxLength(3);
				maxAlterTextBox.setVisibleLength(3);
				maxAlterTextBox.setMaxLength(3);


				/*
				 *  Anheften an Panels
				 */
				suchprofilPanel.add(anlegenTable);
				

			}
		}
		
		/**
		 * ClickHandler um das hinzufügen eines Suchprofil abzubrechen
		 */
		private class SuchProfilAbbrechenClickHandler 
		implements ClickHandler {
			public void onClick(ClickEvent event) {
				spHinzufuegenButton.setVisible(true);
				anlegenTable.clear();
				speichernButtonPanel.clear();
				listBoxPanel.add(spListBox);
			}		
		}
		
		
		/**
		 * ClickHandler um das neue Suchprofil in die Datenbank zu schreiben
		 */
		private class SuchProfilAnlegenClickHandler 
		implements ClickHandler {

			private PopupPanel popup;
			public void onClick(ClickEvent event) {
				spHinzufuegenButton.setVisible(true);
				try {
					ClientSideSettings.getEditorService().
					insertSuchprofil(spNameTextBox.getText(),
							geschlechtListBox.
							getItemText(geschlechtListBox.getSelectedIndex()),
							raucherListBox.
							getItemText(raucherListBox.getSelectedIndex()),
							religionListBox.
							getItemText(religionListBox.getSelectedIndex()),
							Integer.parseInt(minAlterTextBox.getText()), 
							Integer.parseInt(maxAlterTextBox.getText()),
							Integer.parseInt(minGroesseTextBox.getText()), 
							Integer.parseInt(maxGroesseTextBox.getText()),
							haarfarbeListBox.
							getItemText(haarfarbeListBox.getSelectedIndex()), 
							new SPAnlegenCallback());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*
				 * Überprüfung der Eingabefelder bei nicht korektem Zeichen 
				 * oder leerer Angabe wird er Nutzer über Window.alert informiert
				 */
					String input = minAlterTextBox.getText();
					if (!input.matches("[0-9]*") || input.matches("")) {
						Window.alert("'"+ minAlterTextBox.getText() + 
								"'Beinhaltet ein ungültiges Symbol");
						return;
					}else{
					String input2 = maxAlterTextBox.getText();
					if (!input2.matches("[0-9]*") || input.matches("")) {
						Window.alert("'"+ maxAlterTextBox.getText() + 
								"'beinhaltet ein ungültiges Symbol");
						return;
					}
					String input3 = minGroesseTextBox.getText();
					if (!input3.matches("[0-9]*") || input.matches("")) {
						Window.alert("'"+ minGroesseTextBox.getText() + 
								"'beinhaltet ein ung&uumlltiges Symbol");
						return;
					}
					String input4 = maxGroesseTextBox.getText();
					if (!input4.matches("[0-9]*") || input.matches("")) {
						Window.alert("'"+ maxGroesseTextBox.getText() + 
								"'beinhaltet ein ungültiges Symbol");
						return;
					}else {
						/**
						 * clearen der Suchprofilmaske
						 */
						anzeigenTable.clear();
						alterAnzeigenPanel.clear();
						groessenAnzeigenPanel.clear();
						anlegenTable.clear();
						alterPanel.clear();
						groessenPanel.clear();
						speichernButtonPanel.clear();
						spNameTextBox.setText("");
						minAlterTextBox.setText("");
						minGroesseTextBox.setText("");
						maxAlterTextBox.setText("");
						maxGroesseTextBox.setText("");
						RootPanel.get("Zusatz").clear();
					}
					}
					/*
					 * Popup um den Nutzer ein feedback zu geben
					 */
				this.popup = new PopupPanel(true,true);
				this.popup.add(new Label("Suchprofil wurde angelegt "
						+ "zum ausbelnden der Meldung "
						+ "einfach ausserhalb des Feldes Clicken"));
				this.popup.center();
			}
		}
		/**
		 * Callback zum anlegen des Suchprofils
		 */
		private class SPAnlegenCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label
						("Fehler in SuchprofilAnlegenEditor.SPAnlegenCallback"
				+ caught.toString()));
			}
			public void onSuccess(Object result) {
					loadPage();
			}
		}
		
		/**
		 * ClickHandler um das Suchprofil auch aus der Datenbank zu löschen
		 */
			private class DeleteSuchprofilClickHandler implements ClickHandler{
				private PopupPanel popup;

				public void onClick(ClickEvent event) {
					try {
						ClientSideSettings.getEditorService().
						deleteSuchprofil(spListBox.
								getItemText(spListBox.getSelectedIndex()), 
								new SPdeleteCallback());
					} catch (Exception e) {
						e.printStackTrace();
					}
					anzeigenTable.clear();
					keinSPLabel.setText("");
					alterAnzeigenPanel.clear();
					groessenAnzeigenPanel.clear();
					RootPanel.get("Zusatz").clear();
					/**
					 *  Hiermit sieht der Nutzer das sein Suchprofil gelöscht wurde
					 */
					this.popup = new PopupPanel(true,true);
					this.popup.add(new Label("Suchprofil wurde angelegt "
							+ "zum Ausbelnden der Meldung "
							+ "ausserhalb des Feldes Clicken"));
					this.popup.center();
				}
				
			}
			/**
			 * Callback zum löschen des Suchprofils
			 */
			private class SPdeleteCallback implements AsyncCallback{
				public void onFailure(Throwable caught) {
					suchprofilPanel.add(new Label (caught.toString()));
				}
				public void onSuccess(Object result) {
					loadPage();
				}
			}
			
			
		/**
		 * Clickhandler zum Updaten des Suchprofils
		 */
		private class UpdateSuchprofilClickHandler implements ClickHandler{

			private PopupPanel popup;
			public void onClick(ClickEvent event) {
				try {
					ClientSideSettings.getEditorService().
					updateSuchprofil(geschlechtListBox.
							getItemText(geschlechtListBox.getSelectedIndex()),
							Integer.parseInt
							(minAlterAnzeigenTextBox.getText()), 
							Integer.parseInt
							(maxAlterAnzeigenTextBox.getText()),
							religionListBox.
							getItemText(religionListBox.getSelectedIndex()),
							haarfarbeListBox.
							getItemText(haarfarbeListBox.getSelectedIndex()),
							raucherListBox.
							getItemText(raucherListBox.getSelectedIndex()), 
							Integer.parseInt
							(minGroesseAnzeigenTextBox.getText()), 
							Integer.parseInt
							(maxGroesseAnzeigenTextBox.getText()),
							spListBox.
							getItemText(spListBox.getSelectedIndex()), 
							new UpdateCallback());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				keinSPLabel.setText("");
				/*
				 * Field Verfier zum überprüfen ob die richtigen
				 *  Zeichen eingegeben wurden
				 */
				String input = minAlterAnzeigenTextBox.getText();
				if (!input.matches("[0-9]*") || input.matches("")) {
					Window.alert("'"+ minAlterAnzeigenTextBox.getText() + 
							"'beinhaltet ein ungültiges Symbol");
					return;
				} else {
					String input2 = maxAlterAnzeigenTextBox.getText();
					if (!input2.matches("[0-9]*") || input.matches("")) {
						Window.alert("'"+ maxAlterAnzeigenTextBox.getText() + 
								"'beinhaltet ein ungültiges Symbol");
						return;
					}
				
					String input3 = minGroesseAnzeigenTextBox.getText();
					if (!input3.matches("[0-9]*") || input.matches("")) {
						Window.alert("'"+ minGroesseAnzeigenTextBox.getText() + 
								"'beinhaltet ein ungültiges Symbol");
						return;
					}
					String input4 = maxGroesseAnzeigenTextBox.getText();
					if (!input4.matches("[0-9]*") || input.matches("")) {
						Window.alert("'"+ maxGroesseAnzeigenTextBox.getText() + 
								"'beinhaltet ein ungültiges Symbol");
						return;
					
				} else {	
					/**
					 * clearen des Suchprofils nach updaten
					 */
					updatePanel.clear();
					loeschenPanel.clear();
					anzeigenTable.clear();
					alterAnzeigenPanel.clear();
					groessenAnzeigenPanel.clear();
					RootPanel.get("Zusatz").clear();
					}
				}
				/*
				 * Anzeige, dass das Suchprofil geändert wurde
				 */
				this.popup = new PopupPanel(true,true);
				this.popup.add(new Label("Suchprofil wurde aktualisiert "
						+ "zum Ausbelnden der Meldung "
						+ "ausserhalb des Feldes Clicken"));
				this.popup.center();
			
			}
			
		}

		/**
		 * Callback zum Updaten des Suchprofils 
		 */
		private class UpdateCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {				
			}
			public void onSuccess(Object result) {
				loadPage();
			}
		}
		/**
		 * Callback zum Anzeigen der neuen Suchprofile in unserer Listbox
		 */
		private class GetSuchprofileCallback implements 
		AsyncCallback<Vector<Suchprofil>> {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label(caught.toString()));
			}

			public void onSuccess(Vector<Suchprofil> result) {
				if (result.size() == 0) {
					spListBox.removeFromParent();
					suchprofilPanel.add(keinSPLabel);
				}
				if (result.size() != 0) {
				keinSPLabel.removeFromParent();
				}
				for (int i = 0; i < result.size(); i++) {
					spListBox.addItem(result.elementAt(i).getSuchprofilname());
				}
			}
		}
		/**
		 * ClickHandler zum Anzeigen des Suchprofils
		 */
		private class SuchProfilAnzeigenClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				try {
					ClientSideSettings.getEditorService().getSuchprofileByName
					(spListBox.getItemText(spListBox.getSelectedIndex()), 
							new GetSuchprofileKomplettCallback());
				} catch (Exception e) {
					e.printStackTrace();
				}
				/*
				 * Editor erscheinen lassen per Click
				 */
				RootPanel.get("Zusatz").clear();
				keinSPLabel.setText("");
				try {
					RootPanel.get("Zusatz").add(eigenschaftenEditor);
				} catch (Exception e) {
				}
			}
		}	
		/**
		 * Callback zum Anzeigen des kompletten Suchprofils
		 */
		private class GetSuchprofileKomplettCallback implements 
		AsyncCallback<Suchprofil> {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label(caught.toString()));
			}

			public void onSuccess(Suchprofil result) {
				
					try {
						eigenschaftenEditor.setSuchprofil(result);
					} catch (Exception e) {
						e.printStackTrace();
					}
				/*
				 * clearen nach Ausführen des ClickHandlers
				 */
					anlegenTable.clear();
					alterPanel.clear();
					groessenPanel.clear();
					alterAnzeigenPanel.clear();
					groessenAnzeigenPanel.clear();
					speichernButtonPanel.clear();
					
					/*
					 * Setzen von Indexen für die Listboxen für späteres auslesen
					 */
					anzeigenTable.setWidget(1, 0, geschlechtLabel);
					anzeigenTable.setWidget(1, 1, geschlechtListBox);
					for (int g = 0; g < 4;g++) {
						if(geschlechtListBox.getValue(g) == 
								result.getGeschlecht()){
							geschlechtListBox.setSelectedIndex(g);
						}
					}

					anzeigenTable.setWidget(2, 0, raucherLabel);
					anzeigenTable.setWidget(2, 1, raucherListBox);
					for (int g = 0; g < 4;g++) {
						if(raucherListBox.getValue(g) == result.getRaucher()){
							raucherListBox.setSelectedIndex(g);
						}
					}
					

					anzeigenTable.setWidget(3, 0, haarfarbeLabel);
					anzeigenTable.setWidget(3, 1, haarfarbeListBox);
					for (int g = 0; g < 7;g++) {
						if(haarfarbeListBox.getValue(g) == 
								result.getHaarfarbe()){
							haarfarbeListBox.setSelectedIndex(g);
						}
					}
					
					anzeigenTable.setWidget(4, 0, religionLabel);
					anzeigenTable.setWidget(4, 1, religionListBox);
					for (int g = 0; g < 8;g++) {
						if(religionListBox.getValue(g) == 
								result.getReligion()){
							religionListBox.setSelectedIndex(g);
						}
					}
					
					/*
					 * Anheften Textboxen für Groesse an Panels
					 * sowie stylen durch CSS
					 */
					anzeigenTable.setWidget(5, 0, koerpergLabel);
					groessenAnzeigenPanel.add(new Label("von"));
					groessenAnzeigenPanel.getWidget(0).
					setStylePrimaryName("Text-Box-Connector");
					groessenAnzeigenPanel.add(minGroesseAnzeigenTextBox);
					groessenAnzeigenPanel.add(new Label("bis"));
					groessenAnzeigenPanel.getWidget(2).
					setStylePrimaryName("Text-Box-Connector");
					groessenAnzeigenPanel.add(maxGroesseAnzeigenTextBox);
					anzeigenTable.setWidget(5, 1, groessenAnzeigenPanel);
					minGroesseAnzeigenTextBox.setText
					(Integer.toString(result.getMinGroesse()));
					maxGroesseAnzeigenTextBox.setText
					(Integer.toString(result.getMaxGroesse()));
					
					/*
					 * festsetzen wie viel zeichen in eine Textbox kommen dürfen
					 */
					minGroesseAnzeigenTextBox.setVisibleLength(3);
					minGroesseAnzeigenTextBox.setMaxLength(3);
					maxGroesseAnzeigenTextBox.setVisibleLength(3);
					maxGroesseAnzeigenTextBox.setMaxLength(3);

					/*
					 * Anheften Textboxen für Alter an Panels
					 * sowie stylen durch CSS
					 */
					anzeigenTable.setWidget(6, 0, alterLabel);
					alterAnzeigenPanel.add(von);
					alterAnzeigenPanel.add(minAlterAnzeigenTextBox);
					alterAnzeigenPanel.add(bis);
					alterAnzeigenPanel.add(maxAlterAnzeigenTextBox);
					anzeigenTable.setWidget(6, 1, alterAnzeigenPanel);
					minAlterAnzeigenTextBox.setText
					(Integer.toString(result.getMinAlter()));
					maxAlterAnzeigenTextBox.setText
					(Integer.toString(result.getMaxAlter()));
					
					/*
					 * festsetzen wie viel zeichen in eine Textbox kommen dürfen
					 */
					minAlterAnzeigenTextBox.setVisibleLength(3);
					minAlterAnzeigenTextBox.setMaxLength(3);
					maxAlterAnzeigenTextBox.setVisibleLength(3);
					maxAlterAnzeigenTextBox.setMaxLength(3);
				
				buttonPanel.add(loeschenPanel);
				loeschenPanel.add(loeschenButton);
				buttonPanel.add(updatePanel);
				updatePanel.add(speichernButton);
				suchprofilPanel.add(anzeigenTable);
				
			}
			
		}
	
}

	