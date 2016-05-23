package de.shared.RO;

/**
 * <p>
 * Diese Klasse wird benötigt, um auf dem Client die ihm vom Server zur
 * Verfügung gestellten <code>Report</code>-Objekte in ein menschenlesbares
 * Format zu überführen.
 * </p>
 * <p>
 * Das Zielformat kann prinzipiell beliebig sein. Methoden zum Auslesen der in
 * das Zielformat überführten Information wird den Subklassen überlassen. In
 * dieser Klasse werden die Signaturen der Methoden deklariert, die für die
 * Prozessierung der Quellinformation zuständig sind.
 * </p>
 * 
 * @author Thies
 */
public abstract class ReportWriter {

  /**
   * Übersetzen eines <code>AllAccountsOfCustomerReport</code> in das
   * Zielformat.
   * 
   * @param r der zu �bersetzende Report
   */
  public abstract void process(PartnervorschlaegeReport r);

  /**
   * Übersetzen eines <code>AllAccountsOfAllCustomersReport</code> in das
   * Zielformat.
   * 
   * @param r der zu �bersetzende Report
   */
  public abstract void process(MeinProfilReport r);
  /**
   * Übersetzen eines <code>MeinProfilReport</code> in das
   * Zielformat.
   * 
   * @param r der zu �bersetzende Report
   */
  public abstract void process(ImpressumReport r);

public void process(AllAccountsOfCustomerReport r) {
	// TODO Auto-generated method stub
	
}

public void process(AllAccountsOfAllCustomersReport r) {
	// TODO Auto-generated method stub
	
}
}
