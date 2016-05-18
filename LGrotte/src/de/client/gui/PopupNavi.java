package de.client.gui;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class PopupNavi implements EntryPoint {

	static VerticalPanel popupPanel = new VerticalPanel();
	Button profilBearbeitenButton = new Button("Profil bearbeiten");
	Button suchprofilButton = new Button("Suchrpofil");
	Button merkzettelButton = new Button ("Merkzettel");
	Button kontaktsperreButton = new Button ("Kontaktsperren");
	
	private static class NavigationPopup extends PopupPanel{
    public NavigationPopup() {
      // PopupPanel's constructor takes 'auto-hide' as its boolean parameter.
      // If this is set, the panel closes itself automatically when the user
      // clicks outside of it.
      super(true);

      // PopupPanel is a SimplePanel, so you have to set it's widget property to
      // whatever you want its contents to be.
      setWidget(popupPanel);
    }
  }

  public void onModuleLoad() {
    Button b1 = new Button("Click me to show popup");
    b1.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        // Instantiate the popup and show it.
        new NavigationPopup().show();
      }
    });
    RootPanel.get().add(b1);}

    

    private class PopupClickHandler implements ClickHandler{
      public void onClick(ClickEvent e) {
        // Create the new popup.
        final NavigationPopup popup = new NavigationPopup();
        // Position the popup 1/3rd of the way down and across the screen, and
        // show the popup. Since the position calculation is based on the
        // offsetWidth and offsetHeight of the popup, you have to use the
        // setPopupPositionAndShow(callback) method. The alternative would
        // be to call show(), calculate the left and top positions, and
        // call setPopupPosition(left, top). This would have the ugly side
        // effect of the popup jumping from its original position to its
        // new position.
        popup.setPopupPositionAndShow(new PopupPanel.PositionCallback() {
          public void setPosition(int offsetWidth, int offsetHeight) {
            int left = (Window.getClientWidth() - offsetWidth) / 3;
            int top = (Window.getClientHeight() - offsetHeight) / 3;
            popup.setPopupPosition(left, top);
          }
        });
      
    };

  }
}