package de.shared.RO;


import java.io.Serializable;
import java.util.Date;

public abstract class Report implements Serializable {

	private static final long serialVersionUID = 1L;
	
	  private String imprint = null;

	  private String title = "Report";

	  public String getImprint() {
	    return this.imprint;
	  }

	  public void setImprint(String imprint) {
	    this.imprint = imprint;
	  }

	  public String getTitle() {
	    return this.title;
	  }

	  public void setTitle(String title) {
	    this.title = title;
	  }

}
