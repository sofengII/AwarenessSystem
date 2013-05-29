package exception;

/**
 * This exception indicates the case of no found appointment in the next 5 days
 * @author Dominik, Maximilian, Daniela
 *
 */
public class NotFoundAppointmentException extends Exception {

	/**
	 * generated serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for this exception
	 * @param text exception message
	 */
	public NotFoundAppointmentException(String text)
	{
		super(text);
	}
}
