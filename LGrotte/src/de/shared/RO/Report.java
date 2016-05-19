package de.shared.RO;

import java.io.Serializable;
import java.util.Date;

public abstract class Report implements Serializable {
	
	 private static final long serialVersionUID = 1L;
	
	  private String title = "Report";

	  /**
	   * Datum der Erstellung des Berichts.
	   */
	  private Date created = new Date();

	   /**
	   * Auslesen des Berichtstitels.
	   * 
	   * @return Titeltext
	   */
	  public String getTitle() {
	    return this.title;
	  }

	  /**
	   * Setzen des Berichtstitels.
	   * 
	   * @param title Titeltext
	   */
	  public void setTitle(String title) {
	    this.title = title;
	  }

	  /**
	   * Auslesen des Erstellungsdatums.
	   * 
	   * @return Datum der Erstellung des Berichts
	   */
	  public Date getCreated() {
	    return this.created;
	  }

	  /**
	   * Setzen des Erstellungsdatums. <b>Hinweis:</b> Der Aufruf dieser Methoden
	   * ist nicht unbedingt erforderlich, da jeder Report bei seiner Erstellung
	   * automatisch den aktuellen Zeitpunkt festhält.
	   * 
	   * @param created Zeitpunkt der Erstellung
	   */
	  public void setCreated(Date created) {
	    this.created = created;
	  }

}
