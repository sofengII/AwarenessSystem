package core;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.api.client.util.DateTime;

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
			throws NotFoundAppointmentException {
		
		List<IAppointment> appointments = new ArrayList<IAppointment>();
		
		//For every employee...
		for(IEmployee em : employees){
			
			//TO DO: NEUE OV List<IAppointment> freeAppointments FÜR JEDEN EMPLOYEE?
			//TO DO: INKL. METHODEN "addFreeAppointment", "resetFreeAppointments", "getFreeAppointments"
			//TO DO: NEUE METHODE IN APPOINTMENT "matches" ZUR ÜBERPRÜFUNG DER ÜBEREINSTIMMUNG VON ZWEI FREIEN ZEITEN
			
			appointments = em.getAppointments();
			
			//... search for free appointments
			for(IAppointment app : appointments){
				//If (startTime of nextAppointment) - (endTime of app) >= duration
				//IAppointment freeApp = new Appointment(endTime of app + 15 minutes, startTime of nextAppointment + 15 minutes)
				//em.addFreeAppointment(freeApp);
			}
		}
		
		//Compare all the free appointments of the employees if there is one (or more) at the same time
		IEmployee first = employees.get(0);
		List<IEmployee> remaining = employees;
		remaining.remove(0);
		
		List<IAppointment> freeAppointments = new ArrayList<IAppointment>();
		
		
		
		/*for(IAppointment free : first.getFreeAppointments()){		//Gehe alle freien Termine von "first" durch
		 * 
		 *	boolean match = true;
		 * 
		 * 	for(IEmployee em : remaining){		//Prüfe für alle übrigen Mitarbeiter...
		 * 
		 * 		boolean emMatch = false;
		 * 
		 * 		for(IAppointment emFree : em.getFreeAppointments()){	//... ob mind. einer seiner freien Termine mit "free" übereinstimmt
		 * 
		 * 			if(free.matches(emFree)){
		 * 				emMatch = true;
		 * 			}
		 * 		}
		 * 
		 * 		if(!emMatch){		//Bei einem Mitarbeiter wurde keine Übereinstimmung gefunden...
		 * 			match = false;
		 * 			break;		//...dann kann sofort das nächste Appointment "free" untersucht werden.
		 * 		}
		 * 
		 * 	}
		 *
		 * 	if(match){		//Das Appointment "free" findet bei allen Mitarbeitern Übereinstimmung
		 * 		//Zu den gemeinsamen freien Terminen wird ein neuer hinzugefügt
		 * 		//TO DO: WIE FINDET MAN ANFANGS- UND ENDZEIT DIESES FREIEN TERMINS HERAUS?
		 * 		//EVTL. OBEN IN DER SCHLEIFE SPÄTESTEN ANFANGSZEITPUNKT MERKEN UND ALS ENDZEIT "AZ + duration"?
		 * 		//SPÄTESTER ANFANGSZEITPUNKT EVTL. ALS RÜCKGABEWERT VON "free.matches()" STATT TRUE/FALSE?
		 * 		//BEI RÜCKGABEWERT "null" WURDE KEIN MATCH GEFUNDEN
		 * 	}
		 * 
		 * 
		 * }*/
		
		
		// TODO Auto-generated method stub
		return null;
	}

	

}
