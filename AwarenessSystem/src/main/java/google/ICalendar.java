package google;

import java.util.List;

/**
 * This interface define methods to get appointments
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public interface ICalendar {

	/**
	 * This method is used to get all appointments of the given calendar
	 * @return list of appointments
	 */
	public List<IAppointment> getAppointments();
	
	
}
