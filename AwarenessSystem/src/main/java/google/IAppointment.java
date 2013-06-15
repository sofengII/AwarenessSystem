package google;

import com.google.gdata.data.DateTime;

/**
 * This interface defines methods to handle an Appointment
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public interface IAppointment {

	/**
	 * Getter for the start time of an appointment
	 * @return
	 */
	public DateTime getStartTime();
	
	/**
	 * Getter for the end time of an appointment
	 * @return 
	 */
	public DateTime getEndTime();
	
	/**
	 * Checks, if another appointment matches with this appointment
	 * that means if the two appointments have an equal time slot of the size of duration
	 * @param otherAppointment
	 * @param duration
	 * @return If the two appointments match or not
	 */
	public boolean matches(IAppointment otherAppointment, DateTime duration);
	
}
