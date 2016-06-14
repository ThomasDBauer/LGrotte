package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;
import de.shared.BO.Suchprofil;
import de.shared.RO.ProfilEigenschaft;

public class SuchprofilInfoEditor extends VerticalPanel{

		private VerticalPanel editPanel = new VerticalPanel();
		private HorizontalPanel buttonPanel = new HorizontalPanel();
		private Button addEigenschaftenButton = new Button("+", new AddEigenschaftenClickHandler());
		private Button speicherButton = new Button("Speichern", new SpeichernClickHandler());
		private Vector<ListBox> eigenschaftenListboxen = new Vector<ListBox>();
		private Vector<TextBox> infoTextboxen = new Vector<TextBox>();
		private Vector<Eigenschaft> eigenschaften;
		private VerticalPanel eigenschaftenPanel = new VerticalPanel();
		private HorizontalPanel listBoxPanel = new HorizontalPanel();
		private ListBox spListBox = new ListBox();
		
		public SuchprofilInfoEditor() throws Exception {
			
			
			ClientSideSettings.getEditorService().getSuchprofilEigenschaften("DickeFrauen", 
					new AsyncCallback<Vector<ProfilEigenschaft>>(){

						@Override
						public void onFailure(Throwable caught) {
							
						}
						public void onSuccess(Vector<ProfilEigenschaft> result) {
							RootPanel.get().add(new Label(result.elementAt(0).getName()));
							RootPanel.get().add(new Label(result.elementAt(0).getWert()));
						}
			});
			
			buttonPanel.add(addEigenschaftenButton);
			listBoxPanel.add(spListBox);
			this.add(listBoxPanel);
			this.add(buttonPanel);
			this.add(editPanel);
			this.add(eigenschaftenPanel);
			ClientSideSettings.getEditorService().getSuchprofile(new GetSuchprofileCallback());
			ClientSideSettings.getEditorService().getEigenschaften(new GetEigenschaftenCallback());
//			loadSuchprofilEigenschaften();
		}
		
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
		
		public void loadSuchprofilEigenschaften() throws Exception {
			eigenschaftenPanel.clear();
			ClientSideSettings.getEditorService().getSuchprofilEigenschaften(spListBox.getItemText(spListBox.getSelectedIndex()), new LoadSuchprofileCallback());
		}
		
		private class LoadSuchprofileCallback implements AsyncCallback<Vector<ProfilEigenschaft>> {
			public void onFailure(Throwable caught) {
				eigenschaftenPanel.add(new Label("LoadProfileCallback " + caught.toString()));
			}
			public void onSuccess(Vector<ProfilEigenschaft> result) {
				if (result.size() == 0)
					eigenschaftenPanel.add(new Label("Erz�hle etwas �ber ich!"));
				FlexTable table = new FlexTable();
				for (int i = 0; i < result.size(); i++) {
					table.setWidget(i, 0, new Label(result.elementAt(i).getName() + ": "));
					table.setWidget(i, 1, new Label(result.elementAt(i).getWert()));
					table.setWidget(i, 2, new Button("X", new LoeschenClickHandler(
							result.elementAt(i).getInfo())));
				}
				eigenschaftenPanel.add(table);
			}
		}
		
		
		private class LoeschenClickHandler implements ClickHandler {
			private Info info;
			public LoeschenClickHandler(Info info){
				this.info = info;
			}
			public void onClick(ClickEvent e){
				try {
					ClientSideSettings.getEditorService().deleteInfo(info, new AsyncCallback(){
						public void onFailure(Throwable caught) {
						}
						public void onSuccess(Object result) {
							try {
								loadSuchprofilEigenschaften();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
		
		private class AddEigenschaftenClickHandler implements ClickHandler {
			public void onClick(ClickEvent e) {
				// wir wollen eine listbox und eine textbox
				ListBox listbox = new ListBox(false);
				TextBox infotextbox = new TextBox();
				buttonPanel.add(speicherButton);
				// um sie sp�ter auszulesen, werden sie au�erhalb der methode
				// gespeichert
				eigenschaftenListboxen.add(listbox);
				infoTextboxen.add(infotextbox);
				// f�llen der listbox mit allen eigenschaften
				for (int i = 0; i < eigenschaften.size(); i++) {
					listbox.addItem(eigenschaften.elementAt(i).getErlaeuterung());
				}
				// h�bsch anordnen
				HorizontalPanel hpanel = new HorizontalPanel();
				hpanel.add(listbox);
				hpanel.add(infotextbox);
				editPanel.add(hpanel);
			}
		}
		
		private class GetEigenschaftenCallback implements AsyncCallback<Vector<Eigenschaft>> {
			public void onFailure(Throwable caught) {
			}
			public void onSuccess(Vector<Eigenschaft> result) {
				eigenschaften = result;
			}
		}

		private class SpeichernClickHandler implements ClickHandler {
			public void onClick(ClickEvent e) {
				editPanel.clear();
				for (int i = 0; i < eigenschaftenListboxen.size(); i++) {
					ListBox lb = eigenschaftenListboxen.elementAt(i);
					int eigenschaftsID = 0;
					for (int o = 0; o < eigenschaften.size(); o++) {
						if (lb.getItemText(lb.getSelectedIndex()).equals(eigenschaften.elementAt(o).getErlaeuterung())) {
							eigenschaftsID = eigenschaften.elementAt(o).getId();
						}
					}
					Info info = new Info();
					info.setEigenschaft(eigenschaftsID);
					info.setValue(infoTextboxen.elementAt(i).getText());
					Suchprofil sp = new Suchprofil();
					sp.setSuchprofilname(spListBox.getItemText(spListBox.getSelectedIndex())); 
					try {
						ClientSideSettings.getEditorService().insertSuchprofilInfo(sp, info, new InsertSuchprofilInfoCallback());
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		}
		
		private class InsertSuchprofilInfoCallback implements AsyncCallback {
			public void onFailure(Throwable caught) {
			}

			public void onSuccess(Object result) {
				try {
					loadSuchprofilEigenschaften();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		
		
}
