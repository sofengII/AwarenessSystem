package google;

import java.util.Date;

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
	public Date getStartTime();
	
	/**
	 * Getter for the end time of an appointment
	 * @return
	 */
	public Date getEndTime();
	
	
}
