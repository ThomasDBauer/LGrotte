package de.client.report;

import java.util.Vector;

import de.shared.RO.ProfilReport;

public class HTMLWriter {

	/**
	 * @author Thomas Bauer, Sedat Akar
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
	private int report;

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

		/*
		 * Der Header des Reports wird aufgerufen
		 */

		/*
		 * Style der einzelnen Report-Elemente
		 */

		sb.append("<div class =\"Report-Container\">");

		sb.append("<div class =\"Report-Aehnlichkeit\">");
//		sb.append(report.getMatch().getMatchResult());
		sb.append("</div>");

		sb.append("<div class = \"Report-Eigenschaft\">");
		for (int i = 0; i < report.getEigenschaften().size(); i++) {
			sb.append(report.getEigenschaften().elementAt(i).getName());
			sb.append(": ");
			sb.append(report.getEigenschaften().elementAt(i).getWert());
			sb.append("</br>");
		}
		sb.append("</div>");

		sb.append("<div class = \"Report-Attribut\">");
		sb.append("Profil von " + report.getHeader());
		sb.append("</br>");
		for (int i = 0; i < report.getAttribute().size(); i++) {
			sb.append(
					report.getAttribute().elementAt(i).getName() + ": " + report.getAttribute().elementAt(i).getWert());
			sb.append("</br>");
		}
		sb.append("</div>");

		sb.append("</div>");

		this.reportText = sb.toString();
	}

	public void process(Vector<ProfilReport> reports) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < reports.size(); i++) {
			process(reports.elementAt(i));
			buffer.append(this.reportText);
		}
		this.reportText = buffer.toString();
	}

}
