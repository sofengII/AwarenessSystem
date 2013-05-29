package google;

import java.util.Date;

import com.google.gdata.data.DateTime;

/**
 * This class implements methods to handle an Appointment
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public class Appointment implements IAppointment {

	/**
	 * startDate of an appointment
	 */
	private final DateTime startDate;
	/**
	 * endDate of an appointment
	 */
	private final DateTime endDate;
	
	/**
	 * Constructor for an appointment
	 * @param startDate startDate of an appointment
	 * @param endDate endDate of an appointment
	 */
	public Appointment(DateTime startDate, DateTime endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	@Override
	public Date getStartTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getEndTime() {
		// TODO Auto-generated method stub
		return null;
	}

}
