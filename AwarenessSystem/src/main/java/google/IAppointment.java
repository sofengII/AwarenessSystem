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
	
	
}
