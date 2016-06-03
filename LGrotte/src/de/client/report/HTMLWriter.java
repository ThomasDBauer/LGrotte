package de.client.report;

import de.shared.RO.ProfilReport;

public class HTMLWriter {
	
	private String reportText;
	
	public void process(ProfilReport report){
		StringBuffer sb = new StringBuffer();
		
		sb.append("<h2 style = \"font-size:16px\" color:\"grey\">"+report.getTitle()+"</h2>");
		
		sb.append("<br>");
		
		sb.append(report.getRows().elementAt(0).getColumnAt(0));
		sb.append(report.getRows().elementAt(0).getColumnAt(1));
		
		sb.append("<br>");
		
		sb.append(report.getImprint());
		
		this.reportText = sb.toString();
	}
		
		public String getReportText(){
			return this.reportText;
		}
	
	
}
