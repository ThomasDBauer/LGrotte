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

	private VerticalPanel vPanel = this;
	private VerticalPanel content = new VerticalPanel();
	private HorizontalPanel anlegenLoeschenPanel = new HorizontalPanel();
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

	// min - max Alter
	private HorizontalPanel alterPanel = new HorizontalPanel();
	private Label alterLabel = new Label("Alter:");
	private TextBox minAlter = new TextBox();
	private TextBox maxAlter = new TextBox();

	private Vector<Suchprofil> suchprofile = new Vector<Suchprofil>();

	private Button anlegenButton = new Button("Anlegen", new SuchProfilAnlegenClickHandler());

	// Button zum Suchprofil löschen und dessen Inhalt wenn er gelöscht wird
	private Button spLoeschenButton = new Button("Löschen");
	private FlexTable loeschenTable = new FlexTable();

	private Label aussuchenLabel = new Label("Wählen Sie bitte das zu löschende Suchprofil aus:");
	private ListBox spListBox = new ListBox();
	private Button loeschenButton = new Button("Löschen", new DeleteSuchprofilClickHandler());

	public SuchprofilEditor() throws Exception {
		
		try {
			ClientSideSettings.getEditorService().getSuchprofile(
					new GetSuchprofileCallback());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		spAnlegenButton.addClickHandler(new SuchprofilAnlegenClickHandler());
		spLoeschenButton.addClickHandler(new SuchprofilLoeschenClickHandler());
		anlegenLoeschenPanel.add(spAnlegenButton);
		anlegenLoeschenPanel.add(spLoeschenButton);
		this.add(anlegenLoeschenPanel);
		this.add(content);
	}

	private class SuchprofilAnlegenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			
			content.clear();

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

			anlegenTable.setWidget(6, 0, alterLabel);
			alterPanel.add(new Label("von"));
			alterPanel.add(minAlter);
			alterPanel.add(new Label("bis"));
			alterPanel.add(maxAlter);
			anlegenTable.setWidget(6, 1, alterPanel);
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
			content.add(anlegenTable);
			content.add(anlegenButton);

		}
	}

	private class SuchprofilLoeschenClickHandler implements ClickHandler {
		public void onClick(ClickEvent event) {
			content.clear();
			// Ankleben an FlexTable
			loeschenTable.setWidget(0, 0, aussuchenLabel);
			loeschenTable.setWidget(1, 0, spListBox);
			// Hier muss die Box der zu auswählenden Profile noch hin

			// Panels
			content.add(loeschenTable);
			content.add(loeschenButton);
		}
	}

	private class SuchProfilAnlegenClickHandler implements ClickHandler {

		public void onClick(ClickEvent event) {
			try {
				ClientSideSettings.getEditorService().insertSuchprofil(spNameTextBox.getText(),
						geschlechtListBox.getItemText(geschlechtListBox.getSelectedIndex()),
						raucherListBox.getItemText(raucherListBox.getSelectedIndex()),
						religionListBox.getItemText(raucherListBox.getSelectedIndex()),
						Integer.parseInt(minAlter.getText()), Integer.parseInt(maxAlter.getText()),
						Integer.parseInt(koerpergTextBox.getText()),
						haarfarbeListBox.getItemText(haarfarbeListBox.getSelectedIndex()), new SPAnlegenCallback());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}vPanel.add(new Label("Suchprofil angelegt"));
		}
	}

	private class DeleteSuchprofilClickHandler implements ClickHandler{
		
		public void onClick(ClickEvent event) {
			try {
				ClientSideSettings.getEditorService().deleteSuchprofil(spListBox.getItemText(spListBox.getSelectedIndex()), new SPdeleteCallback());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			vPanel.add(new Label("Suchprofil gelöscht"));
		}
		
	}
	private class SPdeleteCallback implements AsyncCallback{
		public void onFailure(Throwable caught) {
			vPanel.add(new Label (caught.toString()));
		}
		public void onSuccess(Object result) {
		}
		
	}
	
	private class SPAnlegenCallback implements AsyncCallback {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(new Label(caught.toString()));
		}

		public void onSuccess(Object result) {
			RootPanel.get().add(new Label(result.toString()));
		}
	}

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

}
