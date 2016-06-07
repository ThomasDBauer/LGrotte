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

		//Style der Überschrift
		sb.append("<div style = \"height: auto; margin:10; padding-bottom: 0 auto;"
				+ "padding-top: 0 auto; font:bold 150%/1em arial; color: #aeaeae"
				+ "\">" + report.getHeader() + "</div>");
		sb.append("<br/>");
		
		//Style der einzelnen Report-Elemente
		sb.append("<div style = \"margin: 5; border-bottom: 4px solid #fff; padding: 10px; background: #B6E5FF; "
				+ "text-align: left; font: bold 180%/1em arial, geneva, sans-serif; color: #000; width: 100%;"
				+ "text-transform: uppercase; letter-spacing: 0.1em; width: auto;\">");

		sb.append(report.getAttribute().elementAt(0).getName());
		sb.append(": ");
		sb.append(report.getAttribute().elementAt(0).getWert());
//		sb.append(report.getHeader());
//		sb.append("Nachname: "  + "<br>");
//		sb.append("Geschlecht: " + "<br>");
//		sb.append("Email: " + "<br>");
//		sb.append("Raucher: "  + "<br>");
//		sb.append("Religion: " + "<br>");
		sb.append("</div>");
		
		//sb.append("<h2>" + p.getFname() + " " + p.getLname() + "<br/>" + "</h2>");
//		sb.append("<b>" + "Email: " + "</b>" + p.getEmail() + " ");
//		sb.append("<b>" + "Raucher: " + "</b>" + p.getRaucher() + " ");
//		sb.append("<b>" + "Religion: " + "</b>" + p.getReligion());

		this.reportText = sb.toString();
	}

}
