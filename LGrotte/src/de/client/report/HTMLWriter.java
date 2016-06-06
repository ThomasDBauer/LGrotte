package de.client.report;

import de.shared.RO.ProfilReport;

public class HTMLWriter {

	/**
	 * @author Thomas Bauer
	 * 
	 *         Die Klasse nimmt Report-Objects und formatiert sie in die
	 *         gew�nschte HTML-Darstellung.
	 * 
	 */

	/*
	 * Der HTML Code. Da der HTML Code in den process(Report) Methoden entsteht,
	 * gibt es eine get() aber keine set() Methode.
	 */
	private String reportText;

	/*
	 * Zum Auslesen des HTML Codes.
	 */
	public String getReportText() {
		return this.reportText;
	}

	/*
	 * Erstellt HTML Code f�r ein einzelnes, detaillierte dargestelltes Profil
	 */
	public void process(ProfilReport report) {
		
		StringBuffer sb = new StringBuffer();

		sb.append("<h2 style = \"font-size:16px\" color:\"grey\">" + report.getTitle() + "</h2>");
		sb.append("<div style = \"width:400px; border:1px solid silver; margin: 10px; background-color:#fa8072;\">");

		sb.append("Email: " + "<br>");
		sb.append("Raucher: "  + "<br>");
		sb.append("Religion: " + "<br>");
		sb.append("</div>");
		
		//sb.append("<h2>" + p.getFname() + " " + p.getLname() + "<br/>" + "</h2>");
//		sb.append("<b>" + "Email: " + "</b>" + p.getEmail() + " ");
//		sb.append("<b>" + "Raucher: " + "</b>" + p.getRaucher() + " ");
//		sb.append("<b>" + "Religion: " + "</b>" + p.getReligion());

		this.reportText = sb.toString();
	}

}
