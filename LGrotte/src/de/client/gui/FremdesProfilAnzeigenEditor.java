package de.client.gui;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FremdesProfilAnzeigenEditor extends VerticalPanel{
	
		private VerticalPanel vpanel = this;
		private Label test = new Label("Es klappt");
		private Button testButon = new Button("Test");
		
		public FremdesProfilAnzeigenEditor() {
			this.add(test);
			this.add(testButon);
		}
		
}
