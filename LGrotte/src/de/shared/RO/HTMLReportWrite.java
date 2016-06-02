package de.shared.RO;

public class HTMLReportWrite extends ReportWriter {
	
	private String reportText ="";
	
	//Methode zum zurücksetzen der Variable
	public void resetReportText(){
		this.reportText="";
	}
	
	//Umwandlung Paragraph-Objekt in HTML
	public String paragraph2HTML(Paragraph p){
		if (p instanceof CompositeParagraph){
			return this.paragraph2HTML((CompositeParagraph)p);
		}
		else {
			return this.paragraph2HTML((SimpleParagraph)p);
		}
		}
	
	//Composite Paragraph in HTML
	public String paragraph2HTML (CompositeParagraph p)
	{
		StringBuffer res = new StringBuffer();
		
		for (int i = 0; i < p.getNumParagraphs();i++){
			res.append("<p>" + p.getParagraphAt(i) + "</p>");
		}
		return res.toString();
	}

	//SimpleParagraph in HTML
	public String paragraph2HTML(SimpleParagraph p){
		return "<p>" + p.toString() + "</p>";
	}
	
	//HTML HeaderText produzieren
	public String getHeader(){
		StringBuffer res = new StringBuffer();
		res.append("<html><head><title></title></head><body>");
		return res.toString();
	}
	
	//HTML Trailer Text 
	public String getTrailer() {
		return "</body></html>";
	}
	
	
	
	@Override
	public void process(PartnervorschlaegeReport r) {
		
		//Zurücksetzen der Variable
		this.resetReportText();
		
		//Buffer für Ergebnisse erstellen
		StringBuffer res = new StringBuffer();
		
		//Auslesen und in HTML übersetzen
		res.append("<H1>" + r.getTitle() + "</H1>");
		res.append("table style =\"width:500px;border:1px solid black\"><tr>");
		res.append("<td valign=\"top\"><b>" + paragraph2HTML(r.getHeaderData()) +"</b></td>");
		//res.append("</tr><tr><td></td><td>" + paragraph2HTML(r.getCreated().toString()+"</td></tr></table>"))
	}
	

	@Override
	public void process(MeinProfilReport r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void process(ImpressumReport r) {
		// TODO Auto-generated method stub
		
	}

}
