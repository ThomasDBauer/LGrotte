package de.client.gui;

import java.util.Vector;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.thirdparty.javascript.jscomp.Result;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import de.client.ClientSideSettings;
import de.shared.BO.Eigenschaft;
import de.shared.BO.Info;

public class ProfilEigenschaftEditor extends VerticalPanel {

	private VerticalPanel editPanel = new VerticalPanel();
	private Button addEigenschaftenButton = new Button("+", new AddEigenschaftenClickHandler());
	private Button speicherButton = new Button("Speichern", new SpeicherClickHandler());
	private Vector<ListBox> eigenschaftenListboxen = new Vector<ListBox>();
	private Vector<TextBox> eigenschaftenTextboxen = new Vector<TextBox>();
	private Vector<Eigenschaft> eigenschaften;

	public ProfilEigenschaftEditor() throws Exception {
		this.add(addEigenschaftenButton);
		this.add(editPanel);
		this.add(speicherButton);
		ClientSideSettings.getEditorService().getEigenschaften(new GetEigenschaftenCallback());

	}

	private class AddEigenschaftenClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {

			// wir wollen eine listbox und eine textbox
			ListBox listbox = new ListBox(false);
			TextBox textbox = new TextBox();

			// um sie später auszulesen, werden sie außerhalb der methode
			// gespeichert
			eigenschaftenListboxen.add(listbox);
			eigenschaftenTextboxen.add(textbox);

			// füllen der listbox mit allen eigenschaften
			for (int i = 0; i < eigenschaften.size(); i++) {
				listbox.addItem(eigenschaften.elementAt(i).getErlaeuterung());
			}

			// hübsch anordnen
			HorizontalPanel hpanel = new HorizontalPanel();
			hpanel.add(listbox);
			hpanel.add(textbox);
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

	private class SpeicherClickHandler implements ClickHandler {
		public void onClick(ClickEvent e) {
			for (int i = 0; i < eigenschaftenListboxen.size(); i++) {
				ListBox lb = eigenschaftenListboxen.elementAt(i);
				int eigenschaftsID = 0;
				for (int o = 0; o < eigenschaften.size(); o++) {
					if (lb.getItemText(lb.getSelectedIndex()).equals(eigenschaften.elementAt(o).getErlaeuterung())) {
						eigenschaftsID = eigenschaften.elementAt(o).getId();
					}
				}
				Info info = new Info();
				info.setId(eigenschaftsID);
				info.setValue(eigenschaftenTextboxen.elementAt(i).getText());
				try {
					ClientSideSettings.getEditorService().insertInfo(info, new InsertInfoCallback());
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		}
	}

	private class InsertInfoCallback implements AsyncCallback {
		public void onFailure(Throwable caught) {
		}
		public void onSuccess(Object result) {
		}
	}
}

/*
 * private VerticalPanel vPanel = this; private Button
 * eigenschaftHinzufÃ¼genButton = new Button("+"); private Button
 * speichernButton = new Button("speichern");
 * 
 * private ListBox eigenschaftenListBox = new ListBox();
 * 
 * private TextBox infoObjektTextBox = new TextBox();
 * 
 * 
 * ProfilEigenschaftEditor(){ vPanel.add(eigenschaftHinzufÃ¼genButton);
 * eigenschaftHinzufÃ¼genButton.addClickHandler(new
 * eigenschaftAnzeigenClickHandler()); } private class
 * eigenschaftAnzeigenClickHandler implements ClickHandler {
 * 
 * public void onClick(ClickEvent event) { vPanel.add(eigenschaftenListBox);
 * for(int i = 0; i < ClientSideSettings.getEditorService(). )
 * vPanel.add(speichernButton);
 * 
 * }
 * 
 * }
 */
