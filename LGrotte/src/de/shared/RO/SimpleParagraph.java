package de.shared.RO;

import java.io.Serializable;

/**
 * Diese Klasse stellt einzelne Abs�tze dar. Der Absatzinhalt wird als String
 * gespeichert. Der Anwender sollte in diesem Strinig keinerlei
 * Formatierungssymbole einf�gen, da diese in der Regel zielformatspezifisch
 * sind.
 * 
 * @author Thies
 */
public class SimpleParagraph extends Paragraph implements Serializable {

	private static final long serialVersionUID = 1L;

	// Inhalt des Absatzes
	private String text = "";

	/**
	 * <p>
	 * Serialisierbare Klassen, die mittels GWT-RPC transportiert werden sollen,
	 * m�ssen einen No-Argument-Konstruktor besitzen. Ist kein Konstruktor
	 * explizit angegeben, so existiert ini Java-Klassen implizit der
	 * Default-Konstruktor, der dem No-Argument-Konstruktor entspricht.
	 * </p>
	 * <p>
	 * Besitzt eine Klasse mind. einen explizit implementierten Konstruktor, so
	 * gelten nur diese explizit implementierten Konstruktoren. Der
	 * Default-Konstruktor gilt dann nicht. Wenn wir in einer solchen Situation
	 * aber dennoch einen No-Argument-Konstruktor ben�tigen, m�ssen wir diesen
	 * wie in diesem Beispiel explizit implementieren.
	 * </p>
	 * 
	 * @see #SimpleParagraph(String)
	 */
	public SimpleParagraph() {
	}

	/**
	 * Dieser Konstruktor erm�glicht es, bereits bei Instantiierung von
	 * <code>SimpleParagraph</code>-Objekten deren Inhalt angeben zu k�nnen.
	 * 
	 * @param value
	 *            der Inhalt des Absatzes
	 * @see #SimpleParagraph()
	 */
	public SimpleParagraph(String value) {
		this.text = value;
	}

	/**
	 * Auslesen des Inhalts.
	 * 
	 * @return Inhalt als String
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * �berschreiben des Inhalts.
	 * 
	 * @param text
	 *            der neue Inhalt des Absatzes.
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Umwandeln des <code>SimpleParagraph</code>-Objekts in einen String.
	 */
	@Override
	public String toString() {
		return this.text;
	}

}
