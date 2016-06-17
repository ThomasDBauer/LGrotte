package de.client.gui;

import java.util.Vector;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Profil;

public class FindLove extends VerticalPanel {

	private FlexTable table = new FlexTable();
	private Vector<String> emailBuffer = new Vector<String>();
	private Button merkButton = new Button("Profile merken", new MerkHandler());
	private Button sperrButton = new Button("Profile sperren",
			new SperrHandler());
	private VerticalPanel resultPanel = new VerticalPanel();

	 FlexCellFormatter cellFormatter = table.getFlexCellFormatter();

	public FindLove() throws Exception {
		HorizontalPanel controlPanel = new HorizontalPanel();
		controlPanel.add(merkButton);
		controlPanel.add(sperrButton);
		table.addStyleName("findLove-table");
		table.setWidth("45em");
		this.add(controlPanel);
		this.add(resultPanel);
		loadProfiles();
	}

	public void loadProfiles() throws Exception {
		resultPanel.clear();
		table.clear();
		resultPanel.add(table);
		ClientSideSettings.getEditorService().getProfilesForEditor(
				new GetProfileCallback());
	}

	private class GetProfileCallback implements AsyncCallback<Vector<Profil>> {

		public void onFailure(Throwable caught) {
			RootPanel.get().add(
					new Label(caught.toString()
							+ " @FindLove.GetProfileCallback"));
		}

		public void onSuccess(Vector<Profil> result) {
			if (result.size() == 0) {
				resultPanel.add(new Label("Keine Ergebnisse"));
			} else {
				for (int i = 0; i < result.size(); i++) {
					Profil p = result.elementAt(i);
					CheckBox cb = new CheckBox();
					cb.addClickHandler(new CheckProfilHandler(p.getEmail()));
					VerticalPanel findLovePanel = new VerticalPanel();
					HorizontalPanel nameGeschlechtGroesse = new HorizontalPanel();
					HorizontalPanel haareReligionRaucher = new HorizontalPanel();
					Label fName = new Label("Name: " + p.getFname() + " "
							+ p.getLname());
					Label geschlecht = new Label("Geschlecht: "
							+ p.getGeschlecht());
					Label kGroesse = new Label("Koerpergroesse(cm): "
							+ Integer.toString(p.getKoerpergroesse()));
					Label haarfarbe = new Label("Haarfrabe: "
							+ p.getHaarfarbe());
					Label religion = new Label("Religion: " + p.getReligion());
					Label raucher = new Label("Raucher: " + p.getRaucher());
					
					fName.addStyleName("findLove-Label");
					geschlecht.addStyleName("findLove-Labelmid");
					kGroesse.addStyleName("findLove-Label");
					haarfarbe.addStyleName("findLove-Label");
					religion.addStyleName("findLove-Labelmid");
					raucher.addStyleName("findLove-Label");
					
					nameGeschlechtGroesse.add(fName);
					nameGeschlechtGroesse.add(geschlecht);
					nameGeschlechtGroesse.add(kGroesse);
					haareReligionRaucher.add(haarfarbe);
					haareReligionRaucher.add(religion);
					haareReligionRaucher.add(raucher);
					nameGeschlechtGroesse.addStyleName("findLove-HPaneltop");
					haareReligionRaucher.addStyleName("findLove-HPanelbot");
					findLovePanel.add(nameGeschlechtGroesse);
					findLovePanel.add(haareReligionRaucher);
					findLovePanel.getElement().getStyle().setMargin(10, Unit.PX);
					findLovePanel.addStyleName("findLove-Panel");
					table.setWidget(i, 0, cb);
					table.setWidget(i, 1, findLovePanel);
					Button anzeigen = new Button("Profil anzeigen");
					findLovePanel.add(anzeigen);
					anzeigen.addClickHandler(new FremdesPAClickHandler(p));
				}
			}
		}
	}

	private class FremdesPAClickHandler implements ClickHandler {
		
		private Profil profil;
		public FremdesPAClickHandler(Profil profil){
			this.profil = profil;
		}
		
		public void onClick(ClickEvent event) {
			RootPanel.get("Inhalt_unten").clear();
			RootPanel.get("Mitte").clear();
			RootPanel.get("Einstellungen").clear();
			RootPanel.get("Inhalt_oben").clear();
			RootPanel.get("Inhalt_oben")
			.add(new HTML(
					"<h2 style = \"color: #c0c0c0\">Fremd Profil</h2>"));
			try{
			RootPanel.get("Inhalt_unten").add(new FremdesProfilAnzeigenEditor(profil));
			ClientSideSettings.getEditorService().insertBesuch(profil, new AsyncCallback(){
				public void onFailure(Throwable caught) {
					RootPanel.get().add(new Label(
							"FindLove.FremdesPAClickHandler " + caught.toString()));
				}
				public void onSuccess(Object result) {
				}
			});
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}	
	}
	
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
			RootPanel.get().add(
					new Label(caught.toString() + " @FindLove.InsertCallback"));
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
