public class ParseException extends RuntimeException {

	public ParseException(String message) {
		super("SYNTAX ERROR: " + message);
	}
}