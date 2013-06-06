package exception;

public class NoAppointmentsException extends Exception {
	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for this exception
	 * @param text exception message
	 */
	public NoAppointmentsException(String text)
	{
		super(text);
	}
}
