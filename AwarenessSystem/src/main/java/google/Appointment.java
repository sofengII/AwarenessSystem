package google;

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
	public DateTime getStartTime() {
		return startDate;
	}

	@Override
	public DateTime getEndTime() {
		return endDate;
	}

	@Override
	public boolean matches(IAppointment otherAppointment, DateTime duration) {
		
		//does the other appointment start AFTER this appointment ends?
		if(otherAppointment.getStartTime().getValue() >= this.getEndTime().getValue()){
			return false;
		}
		else{
			
			//does the time slot between the other appointment's start and this appointment's end take shorter than duration?
			if((this.getEndTime().getValue() - otherAppointment.getStartTime().getValue()) < duration.getValue()){
				return false;
			}
			else{
				
				//does the time slot between the other appointment's end and this appointment's start take shorter than duration?
				if((otherAppointment.getEndTime().getValue() - this.getStartTime().getValue()) < duration.getValue()){
					return false;
				}
				else{
					
					//MATCH!
					return true;
				}
			}
		}
	}
}
