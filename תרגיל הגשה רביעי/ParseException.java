/**
 * The class represent a parsing exception
 * 
 * @author Ram and Yuval
 *
 */
public class ParseException extends RuntimeException {

	/**
	 * Creates new ParseException
	 * @param message exception message to be presented
	 */
	public ParseException(String message) {
		super("SYNTAX ERROR: " + message);
	}
}