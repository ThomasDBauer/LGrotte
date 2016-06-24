package de.client.report;

import java.util.Arrays;
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
	private int report;

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

		/*
		 * Style der einzelnen Report-Elemente
		 */

		sb.append("<div class =\"Report-Container\">");

		sb.append("<div class =\"Report-Aehnlichkeit\">");
		sb.append("<div class =\"Report-Aehnlichkeit-Font\">");
		sb.append(report.getMatch().getMatchResult() + " %");
		sb.append("</div>");
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
		sb.append("<b>" + report.getHeader() + "</b>");
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
		ProfilReport[]array = new ProfilReport[reports.size()];
		 for(int i = 0; i < reports.size(); i++){
			 array[i] = reports.elementAt(i);
		 }
		 Arrays.sort(array);
		 reports.clear();
		 for(int i = 0; i < array.length; i++){
			 reports.add(array[i]);
		 }
		for (int i = 0; i < reports.size(); i++) {
			process(array[i]);
			buffer.append(this.reportText);
		}
		this.reportText = buffer.toString();
	}

}
