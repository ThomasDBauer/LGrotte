package de.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

public class ImageFooter implements IsWidget{

	private Label lb1 = new Label();
	final Image imageLeft = new Image();
	final Image imageRight = new Image();
	private Hyperlink linkHdM = new Hyperlink("https://www.hdm-stuttgart.de", "HdM");

	
	public ImageFooter() {
//		imageLeft.addErrorHandler(new ErrorHandler() {
//			public void onError(ErrorEvent event) {
//				lb1.setText("Fehler aufgetreten");
//			}
//		});
//
//		imageRight.addErrorHandler(new ErrorHandler() {
//			public void onError(ErrorEvent event) {
//				lb1.setText("Fehler aufgetreten");
//			}
//		});
		
//		imageLeft.setUrl("https://www.hdm-stuttgart.de/stylesheets_bilder/Logo_Graustufen_1.gif");
//		imageLeft.setStylePrimaryName("bild_links");
//		imageLeft.addClickHandler(new ImageFooterClickHandler());
//		RootPanel.get("Fusszeile").add(imageLeft);
		
//		imageRight.setUrl("https://idblog.hdm-stuttgart.de/projektkonzept-2015-hdmlogo/files/2015/04/logo_Wirtschaftsinformatik-und-digitale-Meiden.jpg");
//		imageRight.setStylePrimaryName("bild_rechts");
//		RootPanel.get("Fusszeile").add(imageRight);
	}
	
//	private ImageFooterClickHandler implements ClickHandler{
//		public void onClick(ClickEvent e) {
//			String style = ((UIObject) e.getSource()).getStylePrimaryName();
//			if(style.equalsIgnoreCase(imageLeft.getStylePrimaryName()))			
//			
//		}
//	}

	public Widget asWidget() {
		return null;
	}
}
