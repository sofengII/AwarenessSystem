package google;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.gdata.client.Query.CustomParameter;
import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;

import exception.NoAppointmentsException;

/**
 * This class define methods to get appointments
 * 
 * @author Dominik, Maximilian, Daniela
 * @version 1.0
 * 
 */
public class Calendar implements ICalendar, Serializable {

	/**
	 * link to the google calendar
	 */
	private final String link;

	/**
	 * list of employees appointments
	 */
	private List<IAppointment> appointments;

	public Calendar(String link, DateTime startWeek, DateTime endWeek) throws NoAppointmentsException{

		if (link.endsWith("/basic"))
			this.link = link.replace("/basic", "/full");
		else
			this.link = link;

		appointments = new ArrayList<IAppointment>();

		try {
			initializeConnection(startWeek, endWeek);
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initializeConnection(DateTime start, DateTime end)
			throws IOException, ServiceException, NoAppointmentsException {

		CalendarService service = new CalendarService("App1");

		URL feedUrl = new URL(link);

		CalendarQuery myQuery = new CalendarQuery(feedUrl);
		myQuery.setMinimumStartTime(start);
		myQuery.setMaximumStartTime(end);
		myQuery.addCustomParameter(new CustomParameter("orderby", "starttime"));
		myQuery.addCustomParameter(new CustomParameter("sortorder", "ascending"));

		CalendarEventFeed resultF = service.query(myQuery,
				CalendarEventFeed.class);

		if (resultF.getEntries().size() != 0) {
			for (CalendarEventEntry en : resultF.getEntries()) {

				DateTime startTime = null;
				DateTime endTime = null;

				if (en.getTimes().size() == 0)
					throw new NoAppointmentsException(
							"There are no Appointments in this week.");
				else {
					for (When w : en.getTimes()) {

						startTime = w.getStartTime();
						endTime = w.getEndTime();
					}

					IAppointment appointment = new Appointment(startTime,
							endTime);

					appointments.add(appointment);
				}
			}
		} else {
			throw new NoAppointmentsException(
					"There are no Appointments in this week");
		}
	}

	@Override
	public List<IAppointment> getAppointments() {
		return appointments;
	}

	@Override
	public List<IAppointment> getAppointments(DateTime start, DateTime end)
			throws IOException, ServiceException, NoAppointmentsException {
		CalendarService service = new CalendarService("App1");

		List<IAppointment> apps = new ArrayList<IAppointment>();
		URL feedUrl = new URL(link);

		CalendarQuery myQuery = new CalendarQuery(feedUrl);
		myQuery.setMinimumStartTime(start);
		myQuery.setMaximumStartTime(end);
		myQuery.addCustomParameter(new CustomParameter("orderby", "starttime"));
		myQuery.addCustomParameter(new CustomParameter("sortorder", "ascending"));

		CalendarEventFeed resultF = service.query(myQuery,
				CalendarEventFeed.class);

		if (resultF.getEntries().size() != 0) {
			for (CalendarEventEntry en : resultF.getEntries()) {

				DateTime startTime = null;
				DateTime endTime = null;

				if (en.getTimes().size() == 0) {
					throw new NoAppointmentsException(
							"There are no Appointments in this week");
				} else {
					for (When w : en.getTimes()) {
						startTime = w.getStartTime();
						endTime = w.getEndTime();
					}

					IAppointment appointment = new Appointment(startTime,
							endTime);

					apps.add(appointment);
				}
			}
			return apps;
		} else {
			throw new NoAppointmentsException(
					"There are no Appointments in this week");
		}
	}

}
