package google;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.google.gdata.data.DateTime;
import com.google.gdata.util.ServiceException;

import exception.NoAppointmentsException;

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
	
	/**
	 * This method is used to get all appointments between a given start date and an end date
	 * @param start The start date of the query
	 * @param end The end date of the query
	 * @return list of appointments between start and end
	 * @throws MalformedURLException 
	 * @throws NoAppointmentsException 
	 * @throws ServiceException 
	 * @throws IOException 
	 */
	public List<IAppointment> getAppointments(DateTime start, DateTime end) throws IOException, ServiceException;
	
	
}
