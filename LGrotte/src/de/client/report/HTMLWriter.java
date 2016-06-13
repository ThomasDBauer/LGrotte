package de.client.report;

import java.util.Vector;

import de.shared.RO.ProfilReport;

public class HTMLWriter {

	/**
	 * @author Thomas Bauer, Sedat Akar
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

		/*
		 * Der Header des Reports wird aufgerufen
		 */
		sb.append("Profil von " + report.getHeader());

		/*
		 * Style der einzelnen Report-Elemente
		 */
		sb.append(
				"<div style = \"border:1px solid black; margin: 5; border-bottom: 4px solid #fff; padding: 10px; background: #B6E5FF; "
						+ "text-align: left; font: 120%/1em arial, geneva, sans-serif; color: #000; width: 100%;"
						+ "text-transform: uppercase; letter-spacing: 0.1em; width: auto;\">");
		sb.append("<br>");

		for (int i = 0; i < report.getAttribute().size(); i++) {
			sb.append(report.getAttribute().elementAt(i).getName() + ": " +
					report.getAttribute().elementAt(i).getWert()
					+ "<br>");
		}
		sb.append("<br>");

		for (int i = 0; i < report.getEigenschaften().size(); i++) {
			sb.append(report.getEigenschaften().elementAt(i).getName());
			sb.append(": ");
			sb.append(report.getEigenschaften().elementAt(i).getWert());
			sb.append("<br>");
		}

		sb.append("</div>");

		this.reportText = sb.toString();
	}

	public void process(Vector<ProfilReport>reports){
		StringBuffer buffer = new StringBuffer();
		for(int i = 0; i < reports.size(); i++){
			process(reports.elementAt(i));
			buffer.append(this.reportText);
		}
		this.reportText = buffer.toString();
	}
	
	
}
