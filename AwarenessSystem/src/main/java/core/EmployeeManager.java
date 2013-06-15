package core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.data.DateTime;
import com.google.gdata.util.ServiceException;

import exception.NotFoundAppointmentException;
import google.Appointment;
import google.IAppointment;

/**
 * This class implements methods for managing and searching appointments of employees. 
 * This methods are used to find appointments
 * 
 * @authors Dominik, Maximilian, Daniela
 * @version 1.0
 */
public class EmployeeManager implements IEmployeeManager {

	@Override
	public IAppointment getAppointment(List<IEmployee> employees)
			throws NotFoundAppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IAppointment getAppointment(List<IEmployee> employees,
			DateTime duration) throws NotFoundAppointmentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<IAppointment> getAppointments(List<IEmployee> employees,
			DateTime startDate, DateTime duration)
			throws NotFoundAppointmentException, IOException, ServiceException {
		
		List<IAppointment> appointments = new ArrayList<IAppointment>();
		
		//For every employee...
		for(IEmployee em : employees){
			
			//TO DO: NEUE METHODE IN APPOINTMENT "matches" ZUR ÜBERPRÜFUNG DER ÜBEREINSTIMMUNG VON ZWEI FREIEN ZEITEN
			
			long fiveDaysInMs = 432000000;
			DateTime endDate = new DateTime(startDate.getValue() + fiveDaysInMs);
			appointments = em.getAppointments(startDate, endDate);
			IAppointment app = appointments.get(0);
			appointments.remove(0);
			
			//... search for free appointments
			for(IAppointment nextApp : appointments){
				
				//If the time slot between this appointment and the next one is bigger or equal duration...
				if(nextApp.getStartTime().getValue() - app.getEndTime().getValue() >= duration.getValue()){
					
					//Create a new free appointment (take care of the "+ 15 minutes" = "+ 900.000 ms" 
					DateTime startOfFreeApp = new DateTime(app.getEndTime().getValue() + 900000);
					DateTime endOfFreeApp = new DateTime(nextApp.getStartTime().getValue() + 900000);
					IAppointment freeApp = new Appointment(startOfFreeApp, endOfFreeApp);
					em.addFreeAppointment(freeApp);
				}
			}
		}
		
		//Compare all the free appointments of the first employee 
		//with the free appointments of the other employees 
		//if there is one (or more) at the same time
		
		IEmployee first = employees.get(0);
		List<IEmployee> remaining = employees;
		remaining.remove(0);
		
		List<IAppointment> freeAppointments = new ArrayList<IAppointment>();
		
		for(IAppointment free : first.getFreeAppointments()){		//Check all free appointments of the first employee
			
			DateTime latestStartTime = free.getStartTime();
			DateTime earliestEndTime = free.getEndTime();
			
			boolean match = true;
			
			for(IEmployee em : remaining){			//Check for all the other employees...
				
				boolean emMatch = false;
				
				for(IAppointment emFree : em.getFreeAppointments()){		//... for all of his free appointments...
					
					if(free.matches(emFree, duration)){						//... if there is a match, remember it
						emMatch = true;
						if(emFree.getStartTime().getValue() > latestStartTime.getValue()){		//if emFree begins later
							latestStartTime = emFree.getStartTime();			//its startTime is the new latestStartTime
						}
						if(emFree.getEndTime().getValue() < earliestEndTime.getValue()){		//if emFree ends earlier
							earliestEndTime = emFree.getEndTime();				//its endTime is the new earliestEndTime
						}
					}
				}
				
				if(!emMatch){			//if there weren't any matches with this employee's free appointments
					match = false;		
					break;				//the loop of this free appointment can be left
				}
			}
			
			if(match){					//if the appointment free matches with every employee
										//add a new appointment to the freeAppointments list
				freeAppointments.add(new Appointment(latestStartTime, earliestEndTime));
			}
		}
		return freeAppointments;
	}
}
