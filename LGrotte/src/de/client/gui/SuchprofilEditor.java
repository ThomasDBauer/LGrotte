package de.client.gui;

import java.util.Vector;

import org.eclipse.jdt.core.dom.ThisExpression;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Suchprofil;

public class SuchprofilEditor extends VerticalPanel {

	// Verschiedene Panels
	private VerticalPanel suchprofilPanel = this;
	private HorizontalPanel auswaehlenAnzeigenPanel = new HorizontalPanel();
	private HorizontalPanel anlegenLoeschenPanel = new HorizontalPanel();
	
	// Startseite Buttons und Labels
	private Label aussuchenLabel = new Label("Wählen Sie ein Suchprofil aus:");
	private ListBox spListBox = new ListBox();
	private FlexTable komplettTable = new FlexTable(); 
	private Button anzeigenButton = new Button("Anzeigen", new SuchProfilAnzeigenClickHandler());
	private Button ausblendenButton= new Button ("Ausblenden");
	private Button spHinzufuegenButton = new Button("Neues Suchprofil hinzufügen", new SuchprofilHinzufuegenClickHandler());
	private Button loeschenButton = new Button("Löschen", new DeleteSuchprofilClickHandler());
	
	// AnzeigenTable
	private FlexTable anzeigenTable = new FlexTable();
	private Label nameAnzeigenLabel = new Label("Name des Suchprofils:");
	
	// Buttons, Labels und Table fürs Suchprofil hinzufügen
	private FlexTable anlegenTable = new FlexTable();

	private Label spNameLabel = new Label("Benenne dein Suchprofil:");
	private TextBox spNameTextBox = new TextBox();

	private Label geschlechtLabel = new Label("Geschlecht:");
	private ListBox geschlechtListBox = new ListBox();

	private Label raucherLabel = new Label("Raucher:");
	private ListBox raucherListBox = new ListBox();

	private Label haarfarbeLabel = new Label("Haarfarbe:");
	private ListBox haarfarbeListBox = new ListBox();

	private HorizontalPanel groessenPanel = new HorizontalPanel();
	private Label koerpergLabel = new Label("Körpergröße:");
	private TextBox minGroesse = new TextBox();
	private TextBox maxGroesse= new TextBox();

	private Label religionLabel = new Label("Religion:");
	private ListBox religionListBox = new ListBox();
	
	private HorizontalPanel alterPanel = new HorizontalPanel();
	private Label alterLabel = new Label("Alter:");
	private TextBox minAlter = new TextBox();
	private TextBox maxAlter = new TextBox();

	private Vector<Suchprofil> suchprofile = new Vector<Suchprofil>();

	private Button anlegenButton = new Button("Anlegen", new SuchProfilAnlegenClickHandler());


	// Editor 
	public SuchprofilEditor() throws Exception {
		
		try {
			ClientSideSettings.getEditorService().getSuchprofile(
					new GetSuchprofileCallback());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Anhängen der Panels
		komplettTable.setWidget(0, 0, aussuchenLabel);
		komplettTable.setWidget(1, 0, spListBox);
		komplettTable.setWidget(1, 1, anzeigenButton);
		komplettTable.setWidget(3, 0, spHinzufuegenButton);
		komplettTable.setWidget(3, 1, loeschenButton);
		this.add(komplettTable);
	}
	
	// ClickHandler zum suchprofil hizufügen Funktion
	private class SuchprofilHinzufuegenClickHandler implements ClickHandler {
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
			groessenPanel.add(new Label("von "));
			groessenPanel.add(minGroesse);
			groessenPanel.add(new Label(" bis "));
			groessenPanel.add(maxGroesse);
			anlegenTable.setWidget(4, 1, groessenPanel);

			anlegenTable.setWidget(5, 0, religionLabel);
			anlegenTable.setWidget(5, 1, religionListBox);

			anlegenTable.setWidget(6, 0, alterLabel);
			alterPanel.add(new Label("von "));
			alterPanel.add(minAlter);
			alterPanel.add(new Label(" bis "));
			alterPanel.add(maxAlter);
			anlegenTable.setWidget(6, 1, alterPanel);

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

			// Anheften an Panels
			suchprofilPanel.add(anlegenTable);
			suchprofilPanel.add(anlegenButton);
			suchprofilPanel.add(ausblendenButton);

		}
	}
	
	// ClickHandler zum Anzeigen des Suchprofils
	private class SuchProfilAnzeigenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			try {
				ClientSideSettings.getEditorService().getSuchprofile(new GetSuchprofileKomplettCallback());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
		}
		
	}
	// ClickHandler zum Ausblenden des Suchprofils ??
		private class SuchProfilAusblendenClickHandler implements ClickHandler {
			public void onClick(ClickEvent event) {
				try {
					ClientSideSettings.getEditorService().getSuchprofile(new GetSuchprofileKomplettCallback());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}

	// ClickHandler um das neue Suchprofil in die Datenbank zu schreiben
	private class SuchProfilAnlegenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			try {
				ClientSideSettings.getEditorService().insertSuchprofil(spNameTextBox.getText(),
						geschlechtListBox.getItemText(geschlechtListBox.getSelectedIndex()),
						raucherListBox.getItemText(raucherListBox.getSelectedIndex()),
						religionListBox.getItemText(raucherListBox.getSelectedIndex()),
						Integer.parseInt(minAlter.getText()), Integer.parseInt(maxAlter.getText()),
						Integer.parseInt(minGroesse.getText()), Integer.parseInt(maxGroesse.getText()),
						haarfarbeListBox.getItemText(haarfarbeListBox.getSelectedIndex()), new SPAnlegenCallback());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			// Hiermit merkt der Nutzer das sein Suchprofil hinzugefügt wurde
			suchprofilPanel.add(new Label("Suchprofil angelegt"));
		}
	}
	
	// ClickHandler um das Suchprofil auch aus der Datenbank zu löschen
	private class DeleteSuchprofilClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			try {
				ClientSideSettings.getEditorService().deleteSuchprofil(spListBox.getItemText(spListBox.getSelectedIndex()), new SPdeleteCallback());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Hiermit sieht der Nutzer das sein Suchprofil gelöscht wurde
			suchprofilPanel.add(new Label("Suchprofil gelöscht"));
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
	
	// Callback zum anlegen des Suchprofils
	private class SPAnlegenCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(new Label(caught.toString()));
		}

		public void onSuccess(Object result) {
			RootPanel.get().add(new Label(result.toString()));
		}
	}

	// Callback zum Anzeigen der neuen Suchprofile in unserer Listbox
	private class GetSuchprofileCallback implements AsyncCallback<Vector<Suchprofil>> {
		public void onFailure(Throwable caught) {
			RootPanel.get().add(new Label(caught.toString()));
		}

		public void onSuccess(Vector<Suchprofil> result) {
			suchprofile = result;
			for (int i = 0; i < result.size(); i++) {
				spListBox.addItem(result.elementAt(i).getSuchprofilname());
			}
		}
	}
	
	// Callback zum Anzeigen des kompletten Suchprofils
	private class GetSuchprofileKomplettCallback implements AsyncCallback<Vector<Suchprofil>> {
		public void onFailure(Throwable caught) {
			RootPanel.get().add(new Label(caught.toString()));
		}

		public void onSuccess(Vector<Suchprofil> result) {
			suchprofile = result;
			for (int i = spListBox.getSelectedIndex(); i < result.size(); i++) {
				anzeigenTable.clear();
				alterPanel.clear();
				groessenPanel.clear();
				
				anzeigenTable.setWidget(0, 0, nameAnzeigenLabel);
				anzeigenTable.setWidget(0, 1, new Label(result.elementAt(i).getSuchprofilname()));

				anzeigenTable.setWidget(1, 0, geschlechtLabel);
				anzeigenTable.setWidget(1, 1, new Label(result.elementAt(i).getGeschlecht()));

				anzeigenTable.setWidget(2, 0, raucherLabel);
				anzeigenTable.setWidget(2, 1, new Label(result.elementAt(i).getRaucher()));

				anzeigenTable.setWidget(3, 0, haarfarbeLabel);
				anzeigenTable.setWidget(3, 1, new Label(result.elementAt(i).getHaarfarbe()));
				
				String minKoerperString = String.valueOf(result.elementAt(i).getMinGroesse());
				String maxKoerperString = String.valueOf(result.elementAt(i).getMaxGroesse());
				anzeigenTable.setWidget(4, 0, koerpergLabel);
				groessenPanel.add(new Label("von "));
				groessenPanel.add(new Label(minKoerperString));
				groessenPanel.add(new Label(" bis "));
				groessenPanel.add(new Label(maxKoerperString));
				anzeigenTable.setWidget(4, 1, groessenPanel);

				anzeigenTable.setWidget(5, 0, religionLabel);
				anzeigenTable.setWidget(5, 1, new Label(result.elementAt(i).getReligion()));
				
				String minAlterString = String.valueOf(result.elementAt(i).getMinAlter());
				String maxAlterString = String.valueOf(result.elementAt(i).getMaxAlter());
				anzeigenTable.setWidget(6, 0, alterLabel);
				alterPanel.add(new Label("von "));
				alterPanel.add(new Label(minAlterString));
				alterPanel.add(new Label(" bis "));
				alterPanel.add(new Label(maxAlterString));
				anzeigenTable.setWidget(6, 1, alterPanel);
				
				komplettTable.setWidget(2, 0, anzeigenTable);
				
			}
		}
		
	}
	

}
