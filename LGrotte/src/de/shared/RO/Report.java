package de.shared.RO;

import java.io.Serializable;
import java.util.Date;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.HTML;

import de.shared.BO.Profil;

public class Report implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	SafeHtmlBuilder shb = new SafeHtmlBuilder();
	
	SafeHtmlBuilder createReport(Profil profil){
		
		shb.appendHtmlConstant("<h1>");
		shb.appendEscaped(profil.getFname());
		shb.appendHtmlConstant("</h1>");
		
		return shb;
	}
	
	
//	shb.appendEscaped("Name: ").appendEscaped(name);
//	HTML widget = new HTML(shb.toSafeHtml());
	
	
	 
}
