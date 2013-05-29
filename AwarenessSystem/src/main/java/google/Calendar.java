package google;

import java.io.IOException;
import java.net.URL;
import java.util.List;

import com.google.gdata.client.calendar.CalendarQuery;
import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.Entry;
import com.google.gdata.data.Feed;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.calendar.CalendarEventFeed;
import com.google.gdata.data.extensions.When;
import com.google.gdata.util.ServiceException;

/**
 * This class define methods to get appointments
 * @author Dominik, Maximilian, Daniela
 * @version 1.0
 *
 */
public class Calendar implements ICalendar {

	/**
	 * link to the google calendar
	 */
	private final String link;
	
	public Calendar(String link) {
		this.link = link;
		try {
			initializeConnection();
		} catch (IOException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initializeConnection() throws IOException, ServiceException {
		// TODO Geht so noch nicht, nur hier damit es nicht verloren geht.

		CalendarService service = new CalendarService("App1");

		service.setUserCredentials("sofengii@gmail.com", "sofengIII");

		// URL feedUrl = new
		// URL("https://www.google.com/calendar/feeds/sofengii%40gmail.com/private-2541ddfeaf32c31be3f0fa31e9018acf/full");
		URL feedUrl = new URL(
				"https://www.google.com/calendar/feeds/default/allcalendars/full");

		CalendarQuery myQuery = new CalendarQuery(feedUrl);
		myQuery.setMinimumStartTime(DateTime
				.parseDateTime("2013-01-01T00:00:00"));
		myQuery.setMaximumStartTime(DateTime
				.parseDateTime("2013-05-24T23:59:59"));

		Feed resultFeed = service.query(myQuery, Feed.class);

		for (Entry e : resultFeed.getEntries()) {

			String s = e.getId().replace("default/calendars/", "")
					+ "/private/full";

			feedUrl = new URL(s);

			myQuery = new CalendarQuery(feedUrl);
			myQuery.setMinimumStartTime(DateTime
					.parseDateTime("2013-01-01T00:00:00"));
			myQuery.setMaximumStartTime(DateTime
					.parseDateTime("2013-11-24T23:59:59"));

			CalendarEventFeed resultF = service.query(myQuery,
					CalendarEventFeed.class);

			for (CalendarEventEntry en : resultF.getEntries()) {

				System.out.println("Titel: " + en.getTitle().getPlainText());
				for (When w : en.getTimes()) {
					System.out.println("Start = " + w.getStartTime()
							+ ", End = " + w.getEndTime() + "\n");
				}

			}
		}
	}

	@Override
	public List<IAppointment> getAppointments() {
		// TODO Auto-generated method stub
		return null;
	}

}
