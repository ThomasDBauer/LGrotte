package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Suchprofil;

public class SuchprofilEditor extends VerticalPanel {
	// Verschiedene Panels
		private VerticalPanel suchprofilPanel = this;
		private HorizontalPanel buttonPanel =  new HorizontalPanel();
		private HorizontalPanel loeschenPanel = new HorizontalPanel();
		private HorizontalPanel updatePanel = new HorizontalPanel();
		private HorizontalPanel speichernButtonPanel = new HorizontalPanel();
		private HorizontalPanel listBoxPanel = new HorizontalPanel();
		
		// Startseite Buttons und Labels
		private ListBox spListBox = new ListBox();
		private FlexTable komplettTable = new FlexTable(); 
		private Button spHinzufuegenButton = new Button("+", new SuchprofilHinzufuegenClickHandler());
		private Button loeschenButton = new Button("Löschen", new DeleteSuchprofilClickHandler());
		private Button updateButton = new Button("Update", new UpdateSuchprofilClickHandler());
		// AnzeigenTable
		private FlexTable anzeigenTable = new FlexTable();
		private Label suchprofilNameLabel = new Label("Name:");
		private HorizontalPanel groessenAnzeigenPanel = new HorizontalPanel();
		private HorizontalPanel alterAnzeigenPanel = new HorizontalPanel();
		private TextBox minGroesseAnzeigenTextBox = new TextBox();
		private TextBox maxGroesseAnzeigenTextBox= new TextBox();
		private TextBox minAlterAnzeigenTextBox = new TextBox();
		private TextBox maxAlterAnzeigenTextBox = new TextBox();
		
		// Buttons, Labels und Table fürs Suchprofil hinzufügen
		private FlexTable anlegenTable = new FlexTable();

		private Label spNameLabel = new Label("Name:");
		private TextBox spNameTextBox = new TextBox();

		private Label geschlechtLabel = new Label("Geschlecht:");
		private ListBox geschlechtListBox = new ListBox();

		private Label raucherLabel = new Label("Raucher:");
		private ListBox raucherListBox = new ListBox();

		private Label haarfarbeLabel = new Label("Haarfarbe:");
		private ListBox haarfarbeListBox = new ListBox();

		private HorizontalPanel groessenPanel = new HorizontalPanel();
		private Label koerpergLabel = new Label("Körpergröße:");
		private TextBox minGroesseTextBox = new TextBox();
		private TextBox maxGroesseTextBox= new TextBox();
	

		private Label religionLabel = new Label("Religion:");
		private ListBox religionListBox = new ListBox();
	
		
		private HorizontalPanel alterPanel = new HorizontalPanel();
		private Label alterLabel = new Label("Alter:");
		private TextBox minAlterTextBox = new TextBox();
		private TextBox maxAlterTextBox = new TextBox();
	

		private Vector<Suchprofil> suchprofile = new Vector<Suchprofil>();

		private Button anlegenButton = new Button("Speichern", new SuchProfilAnlegenClickHandler());

		// Editor 
		public SuchprofilEditor() throws Exception {
			loadPage();
		}
		
		public void loadPage(){
			
			try {
				ClientSideSettings.getEditorService().getSuchprofile(
						new GetSuchprofileCallback());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Click- und ChangeHandler für ListBox damit wir keinen Anzeigen Button brauchen
			spListBox.addClickHandler(new SuchProfilAnzeigenClickHandler());
			spListBox.addChangeHandler(new ChangeHandler(){
				public void onChange(ChangeEvent event) {
					spListBox.addClickHandler(new SuchProfilAnzeigenClickHandler());
				}
			});
			
			// Anhängen der Panels
			buttonPanel.add(loeschenPanel);
			loeschenPanel.add(loeschenButton);
			buttonPanel.add(spHinzufuegenButton);
			listBoxPanel.add(spListBox);
			this.add(buttonPanel);
			this.add(listBoxPanel);
			
			
			// Anhängen der Items zur Auswahl
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
		}
		// ClickHandler zum suchprofil hizufügen Funktion
		private class SuchprofilHinzufuegenClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				anzeigenTable.clear();
				groessenPanel.clear();
				alterPanel.clear();
				updatePanel.clear();
				buttonPanel.add(speichernButtonPanel);
				speichernButtonPanel.add(anlegenButton);
				
				
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
				groessenPanel.add(new Label("von "));
				groessenPanel.add(minGroesseTextBox);
				groessenPanel.add(new Label(" bis "));
				groessenPanel.add(maxGroesseTextBox);
				anlegenTable.setWidget(4, 1, groessenPanel);

				anlegenTable.setWidget(5, 0, religionLabel);
				anlegenTable.setWidget(5, 1, religionListBox);

				anlegenTable.setWidget(6, 0, alterLabel);
				alterPanel.add(new Label("von "));
				alterPanel.add(minAlterTextBox);
				alterPanel.add(new Label(" bis "));
				alterPanel.add(maxAlterTextBox);
				anlegenTable.setWidget(6, 1, alterPanel);


				// Anheften an Panels
				suchprofilPanel.add(anlegenTable);
				

			}
		}
		
		
		
		// ClickHandler um das neue Suchprofil in die Datenbank zu schreiben
		private class SuchProfilAnlegenClickHandler implements ClickHandler {

			private PopupPanel popup;
			public void onClick(ClickEvent event) {
				try {
					ClientSideSettings.getEditorService().insertSuchprofil(spNameTextBox.getText(),
							geschlechtListBox.getItemText(geschlechtListBox.getSelectedIndex()),
							raucherListBox.getItemText(raucherListBox.getSelectedIndex()),
							religionListBox.getItemText(raucherListBox.getSelectedIndex()),
							Integer.parseInt(minAlterTextBox.getText()), Integer.parseInt(maxAlterTextBox.getText()),
							Integer.parseInt(minGroesseTextBox.getText()), Integer.parseInt(maxGroesseTextBox.getText()),
							haarfarbeListBox.getItemText(haarfarbeListBox.getSelectedIndex()), new SPAnlegenCallback());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
				anzeigenTable.clear();
				alterAnzeigenPanel.clear();
				groessenAnzeigenPanel.clear();
				anlegenTable.clear();
				alterPanel.clear();
				groessenPanel.clear();
				speichernButtonPanel.clear();
				this.popup = new PopupPanel(true,true);
				this.popup.add(new Label("Suchprofil wurde angelegt "
						+ "zum ausbelnden der Meldung einfach ausserhalb des Feldes Clicken"));
				this.popup.center();
			}
		}
		// Callback zum anlegen des Suchprofils
		private class SPAnlegenCallback implements AsyncCallback {

			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label("Fehler in SuchprofilAnlegenEditor.SPAnlegenCallback" + caught.toString()));
			}

			public void onSuccess(Object result) {
				RootPanel.get().add(new Label(result.toString()));
				loadPage();
			}
		}
			// ClickHandler um das Suchprofil auch aus der Datenbank zu löschen
			private class DeleteSuchprofilClickHandler implements ClickHandler{
				
				private PopupPanel popup;

				public void onClick(ClickEvent event) {
					try {
						ClientSideSettings.getEditorService().deleteSuchprofil(spListBox.getItemText(spListBox.getSelectedIndex()), new SPdeleteCallback());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					anzeigenTable.clear();
					alterAnzeigenPanel.clear();
					groessenAnzeigenPanel.clear();
					// Hiermit sieht der Nutzer das sein Suchprofil gelöscht wurde
					this.popup = new PopupPanel(true,true);
					this.popup.add(new Label("Suchprofil wurde angelegt "
							+ "zum ausbelnden der Meldung einfach ausserhalb des Feldes Clicken"));
					this.popup.center();
				}
				
			}
			// Callback zum löschen des Suchprofils
			private class SPdeleteCallback implements AsyncCallback{
				public void onFailure(Throwable caught) {
					suchprofilPanel.add(new Label (caught.toString()));
				}
				public void onSuccess(Object result) {
				}
			}
			
			
		//Clickhandler zum Updaten des Suchprofils
		private class UpdateSuchprofilClickHandler implements ClickHandler{

			private PopupPanel popup;
			public void onClick(ClickEvent event) {
				try {
					ClientSideSettings.getEditorService().updateSuchprofil(geschlechtListBox.getItemText(geschlechtListBox.getSelectedIndex()),
							raucherListBox.getItemText(raucherListBox.getSelectedIndex()), 
							religionListBox.getItemText(religionListBox.getSelectedIndex()),
							Integer.parseInt(minAlterTextBox.getText()), Integer.parseInt(maxAlterTextBox.getText()),
							Integer.parseInt(minGroesseTextBox.getText()), Integer.parseInt(maxGroesseTextBox.getText()),
							haarfarbeListBox.getItemText(haarfarbeListBox.getSelectedIndex()),
							spListBox.getItemText(spListBox.getSelectedIndex()),
							new GetUpdateCallback());
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				updatePanel.clear();
				anzeigenTable.clear();
				alterAnzeigenPanel.clear();
				groessenAnzeigenPanel.clear();
				this.popup = new PopupPanel(true,true);
				this.popup.add(new Label("Suchprofil wurde aktualisiert "
						+ "zum ausbelnden der Meldung einfach ausserhalb des Feldes Clicken"));
				this.popup.center();
			}
			
		}

		//Callback zum Updaten des Suchprofils 
		private class GetUpdateCallback implements AsyncCallback<Vector<Suchprofil>>{

			public void onFailure(Throwable caught) {
				
			}

			public void onSuccess(Vector<Suchprofil> result) {
				
			}
			// Callback zum Anzeigen der neuen Suchprofile in unserer Listbox
			private class GetSuchprofileCallback implements AsyncCallback<Vector<Suchprofil>> {
				public void onFailure(Throwable caught) {
					RootPanel.get().add(new Label(caught.toString()));
				}

				public void onSuccess(Vector<Suchprofil> result) {
			
					for (int i = 0; i < result.size(); i++) {
						spListBox.addItem(result.elementAt(i).getSuchprofilname());
					}
				}
			}

		}
		// Callback zum Anzeigen der neuen Suchprofile in unserer Listbox
		private class GetSuchprofileCallback implements AsyncCallback<Vector<Suchprofil>> {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label(caught.toString()));
			}

			public void onSuccess(Vector<Suchprofil> result) {
		
				for (int i = 0; i < result.size(); i++) {
					spListBox.addItem(result.elementAt(i).getSuchprofilname());
				}
			}
		}
		// ClickHandler zum Anzeigen des Suchprofils
		private class SuchProfilAnzeigenClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				try {
					ClientSideSettings.getEditorService().getSuchprofileByName(
							spListBox.getItemText(spListBox.getSelectedIndex()), 
							new GetSuchprofileKomplettCallback());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}	
		// Callback zum Anzeigen des kompletten Suchprofils
		private class GetSuchprofileKomplettCallback implements AsyncCallback<Suchprofil> {
			public void onFailure(Throwable caught) {
				RootPanel.get().add(new Label(caught.toString()));
			}

			public void onSuccess(Suchprofil result) {
				
					anlegenTable.clear();
					alterPanel.clear();
					groessenPanel.clear();
					alterAnzeigenPanel.clear();
					groessenAnzeigenPanel.clear();
					speichernButtonPanel.clear();
					
					anzeigenTable.setWidget(1, 0, geschlechtLabel);
					anzeigenTable.setWidget(1, 1, geschlechtListBox);
					for (int g = 0; g < 2;g++) {
						if(geschlechtListBox.getValue(g) == result.getGeschlecht()){
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
					for (int g = 0; g < 5;g++) {
						if(haarfarbeListBox.getValue(g) == result.getHaarfarbe()){
							haarfarbeListBox.setSelectedIndex(g);
						}
					}
					
					anzeigenTable.setWidget(4, 0, religionLabel);
					anzeigenTable.setWidget(4, 1, religionListBox);
					for (int g = 0; g < 6;g++) {
						if(religionListBox.getValue(g) == result.getReligion()){
							religionListBox.setSelectedIndex(g);
						}
					}
					
					
					anzeigenTable.setWidget(5, 0, koerpergLabel);
					groessenAnzeigenPanel.add(new Label("von "));
					groessenAnzeigenPanel.add(minGroesseAnzeigenTextBox);
					groessenAnzeigenPanel.add(new Label(" bis "));
					groessenAnzeigenPanel.add(maxGroesseAnzeigenTextBox);
					anzeigenTable.setWidget(5, 1, groessenAnzeigenPanel);
					minGroesseAnzeigenTextBox.setText(Integer.toString(result.getMinAlter()));
					maxGroesseAnzeigenTextBox.setText(Integer.toString(result.getMaxAlter()));

					
					anzeigenTable.setWidget(6, 0, alterLabel);
					alterAnzeigenPanel.add(new Label("von "));
					alterAnzeigenPanel.add(minAlterAnzeigenTextBox);
					alterAnzeigenPanel.add(new Label(" bis "));
					alterAnzeigenPanel.add(maxAlterAnzeigenTextBox);
					anzeigenTable.setWidget(6, 1, alterAnzeigenPanel);
					minAlterAnzeigenTextBox.setText(Integer.toString(result.getMinGroesse()));
					maxAlterAnzeigenTextBox.setText(Integer.toString(result.getMaxGroesse()));
				
				buttonPanel.add(updatePanel);
				updatePanel.add(updateButton);
				suchprofilPanel.add(anzeigenTable);
				
			}
			
		}
	
}

	