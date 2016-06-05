package de.client.report;

import de.shared.RO.ProfilReport;

public class HTMLWriter {

	/**
	 * @author Thomas Bauer
	 * 
	 *         Die Klasse nimmt Report-Objects und formatiert sie in die
	 *         gewünschte HTML-Darstellung.
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
	 * Erstellt HTML Code für ein einzelnes, detaillierte dargestelltes Profil
	 */
	public void process(ProfilReport report) {
		StringBuffer sb = new StringBuffer();

		sb.append("<h2 style = \"font-size:16px\" color:\"grey\">" + report.getTitle() + "</h2>");
		sb.append("<div style = \"border:1px solid black; margin: 10px; background-color: #f08080;\">");

		sb.append("<b>" + "Email: " + "</b>" + "<br>");
		sb.append("<b>" + "Raucher: " + "</b>" + "<br>");
		sb.append("<b>" + "Religion: " + "</b>" + "<br>");
		
		//sb.append("<h2>" + p.getFname() + " " + p.getLname() + "<br/>" + "</h2>");
//		sb.append("<b>" + "Email: " + "</b>" + p.getEmail() + " ");
//		sb.append("<b>" + "Raucher: " + "</b>" + p.getRaucher() + " ");
//		sb.append("<b>" + "Religion: " + "</b>" + p.getReligion());
//		sb.append("</div>");

		this.reportText = sb.toString();
	}

}
