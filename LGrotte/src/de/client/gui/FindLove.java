package de.client.gui;

import java.util.Vector;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

/**
 * Diese Klasse erbt von VerticalPanel und traegt die aus der Datenbank
 * generierten Partnervorschlaege des Users in eine Tabelle, welche
 * neben zwei Button dem der Klasse angehaengt wird. Zu jeder Spalte
 * mit einem Partnervorschlag wird eine Checkbox zur Interaktion mit den
 * Button erstellt 
 * 
 * @author Nicolai Ehrmanntraut, Thomas Bauer, Enrico Popaj & Lukas Kircher
 * 
 * @version 1.0
 *
 */

public class FindLove extends VerticalPanel {

	/**
	 * Die Tabelle fuer die Vorschlaege mit jeweiler CheckBox
	 */
	private FlexTable table = new FlexTable();
	
	/**
	 * Die Emails der mit einem Kreuz versehnen CheckBoxen
	 * werden in einem Vector als String gespeichert.
	 * Dieser wird hier erzeugt
	 */
	private Vector<String> emailBuffer = new Vector<String>();
	
	/**
	 * Buttons, welche die in dem "emailBuffer" stehenden Strings
	 * auslesen um diese ueber einen aufruf einer Mappermethode
	 *  je nach Button in die Merkliste oder die Kontaktsperre einzutragen
	 */
	private Button merkButton = new Button("Profile merken", 
			new MerkHandler());
	private Button sperrButton = new Button("Profile sperren",
			new SperrHandler());
	
	/**
	 * VerticalPanel, dass angezeigt wird, wenn keine Ergebnisse
	 * vorhanden sind
	 */
	private VerticalPanel resultPanel = new VerticalPanel();

	/**
	 * Formatter zur individuellen Formatierung der FelxTable "table"
	 */
	FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

	
	/**
	 * No-Argument-Konstruktor erzeugen, der den Buttons die Styles zuweisst, 
	 * die Bilder fuer die Buttons setzt, aber auch die in einem Panel befindlichen
	 * Buttons dem durch den Konstruktor erzeugten Objekt anfuegt
	 * und abschliessend die Methode loadProfiles aufruft, welche die bereits
	 * in der Tabelle befindlichen Panels mit Inhalt versieht
	 */
	public FindLove() throws Exception {
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.add(merkButton);
		controlPanel.add(sperrButton);
		Image merkImage = new Image("merken.png"); 
		Image sperrImage = new Image("sperren.png");
		merkImage.setStylePrimaryName("Button-img-Image");
		sperrImage.setStylePrimaryName("Button-img-Image");
		merkButton.getElement().appendChild(merkImage.getElement());
		sperrButton.getElement().appendChild(sperrImage.getElement());
		merkButton.setStylePrimaryName("Button-img");
		sperrButton.setStylePrimaryName("Button-img");
		table.addStyleName("findLove-table");
		this.add(controlPanel);
		this.add(resultPanel);
		loadProfiles();
	}

	/**
	 * Die Methode loadProfiles generiert die Profilvorschlaege 
	 * aus der Datenbank, sie wirft einen GeProfileCallback
	 */
	public void loadProfiles() throws Exception {
		resultPanel.clear();
		table.clear();
		resultPanel.add(table);
		ClientSideSettings.getEditorService().getProfilesForEditor(
				new GetProfileCallback());
	}

	/**
	 * Der Callback, der die generierten Profile beinhaltet.
	 * bei erfolgreichem Callback, wird die Tabelle mit den Profilen
	 * befuellt und zu jedem Profil eine Checkbox in der Tabelle erstellt.
	 * Ein Button, welcher hinter jedem Partnervorschlag steht und das 
	 * jeweilige Profil mit allen Informationen anzeigt wird erzeugt.
	 * Auch werden Styles zu den einzelenen Labels und dem Button angefuegt
	 * zum Schluss wird ein Clickhandler, der 
	 * das einzelne Profil anzeigt hinzugefuegt
	 */
	private class GetProfileCallback implements AsyncCallback<Vector<Profil>> {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(
					new Label(caught.toString()
							+ " @FindLove.GetProfileCallback"));
		}
		
		/**
		 * Bei erfolgreichem Callback, wird geprueft, ob der Vector leer ist, 
		 * da in diesem Fall keine Ergebnisse vorhanden sind 
		 * und dies Ausgegeben wird. 
		 * Ist der Vector nicht leer, so wird eine For-Schleife durchlaufen,
		 * welche fuer jedes Profil im Vector jeweil ein Profil erstellt, dass
		 * mit diesem Profil aus dem Vector Gleichgestellt wird. 
		 * Weiter wird eine CheckBox zu jedem Profil erstellt. Diese CheckBox
		 * bekommt einen ClickHandler mit der zum Profil gehoerenden Email-
		 * Adresse als Uebergabeparameter. Ein Label, dass den Namen des
		 * Profils als Text beinhaltet wird erzeugt und bekommt Styles 
		 * zugewiesen. Die erstellten Obejekte werden der Tabell hinzuegefuegt
		 * und der Button, welcher das jeweilige Profil anzeigt wird gestylt.
		 * 
		 * @param result Vector vom Typ Profil mit allen Profilen, 
		 * außer dem eigenen
		 */
		public void onSuccess(Vector<Profil> result) {
			if (result.size() == 0) {
				resultPanel.add(new Label("Keine Ergebnisse"));
			} else {
				for (int i = 0; i < result.size(); i++) {
					Profil p = result.elementAt(i);
					CheckBox cb = new CheckBox();
					cb.addClickHandler(new CheckProfilHandler(p.getEmail()));
					VerticalPanel findLovePanel = new VerticalPanel();
					HorizontalPanel nameMatchingAnzeigen 
					= new HorizontalPanel();
					Label name = new Label(p.getFname() + " "
							+ p.getLname());
					name.setStyleName("findLove-Name", true);
					name.addStyleName("findLove-Label");
					nameMatchingAnzeigen.add(name);
					nameMatchingAnzeigen.addStyleName("findLove-HPaneltop");
					findLovePanel.add(nameMatchingAnzeigen);
					findLovePanel.getElement().getStyle().setMargin(10, Unit.PX);
					findLovePanel.addStyleName("findLove-Panel");
					table.setWidget(i, 0, cb);
					table.setWidget(i, 1, findLovePanel);
					Button anzeigen = new Button("Anzeigen");
					Image img = new Image("besuchen.png");
					img.setStylePrimaryName("Button-img-Image");
					anzeigen.getElement().appendChild(img.getElement());
					anzeigen.setStylePrimaryName("Button-img");
					nameMatchingAnzeigen.add(anzeigen);
					anzeigen.addClickHandler(new FremdesPAClickHandler(p));
				}
			}
		}
	}

	
	/**
	 * Dieser ClickHandler legt fest, dass bei Klick auf den Button Anzeigen, 
	 * der Editor FremdesProfilAnzeigenEditor erstellt und dem RootPanel 
	 * hinzugefuegt wird. Die anderen Inhalte, welche sich in den Inhalts-Divs
	 * des RootPanels befinden werden im voraus geloescht.
	 */
	private class FremdesPAClickHandler implements ClickHandler {
		
		private Profil profil;
		public FremdesPAClickHandler(Profil profil){
			this.profil = profil;
		}
		
		public void onClick(ClickEvent event) {
			RootPanel.get("Inhalt").clear();
			RootPanel.get("Zusatz").clear();
			RootPanel.get("Content").clear();
			RootPanel.get("Inhalt")
			.add(new HTML(
					"<h2 style = \"color: #c0c0c0\">" + profil.getFname() + 
					"s Profil</h2>"));
			try{
			RootPanel.get("Inhalt")
			.add(new FremdesProfilAnzeigenEditor(profil));
			ClientSideSettings.getEditorService()
			.insertBesuch(profil, new AsyncCallback(){
				public void onFailure(Throwable caught) {
					RootPanel.get().add(new Label(
							"FindLove.FremdesPAClickHandler " 
					+ caught.toString()));
				}
				public void onSuccess(Object result) {
				}
			});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}	
	}
	
	/**
	 * Hier wird festgelegt, was passiert wenn eine CheckBox angeklickt
	 * Die E-Mail wird dem Vector vom Typ String "emailBuffer" angefuegt. 
	 * Bei erneutem klicken, also wenn das Kreuz wieder verschwindet, 
	 * wird die Email dem Buffer wieder entnommen
	 */
	
	private class CheckProfilHandler implements ClickHandler {
		private String userEmail;
		public CheckProfilHandler(String email) {
			this.userEmail = email;
		}
		public void onClick(ClickEvent e) {
			CheckBox cb = (CheckBox) e.getSource();
			if (!cb.getValue()) {
				emailBuffer.remove(userEmail);
			} else {
				emailBuffer.add(userEmail);
			}
		}
	}

	/**
	 * Die gespeicherten Emails werden, beim Klicken des Button
	 * durch den ClickHandler mit dem insertMerkzettel bzw. der 
	 * insertKontaktsperre in die Datenbank eingetragen 
	 * Der Button wird waehrend des Ausfuehrens des ClickHandlers 
	 * gesperrrt. Anschließend wird der emailBuffer geleert.
	 * 
	 * Beim Callback beider insert-Methoden werden die Buttons entsperrt und 
	 * die Profile mit loadProfiles() neu geladen, ausserdem wird der
	 * emailBuffer entleert.
	 */
	
	private class MerkHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			merkButton.setEnabled(false);
			try {
				ClientSideSettings.getEditorService().insertMerkzettel(
						emailBuffer, new InsertCallback());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			emailBuffer.clear();
		}
	}

	private class SperrHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			sperrButton.setEnabled(false);
			try {
				ClientSideSettings.getEditorService().insertKontaktsperren(
						emailBuffer, new InsertCallback());
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	private class InsertCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
			RootPanel.get("Inhalt").add(
					new Label(caught.toString() 
							+ " @FindLove.InsertCallback"));
		}

		public void onSuccess(Object result) {
			sperrButton.setEnabled(true);
			merkButton.setEnabled(true);
			emailBuffer.clear();
			try {
				loadProfiles();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
}
