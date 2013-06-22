package beans;

import java.util.ArrayList;
import java.util.List;

public class DurationList {
	
	private List<String> durations;
	
	public DurationList(){
		this.durations = new ArrayList<String>();
		fillDurationList();
	}
	
	private void fillDurationList() {
		this.durations.add("1 h");
		this.durations.add("2 h");
		this.durations.add("3 h");
		this.durations.add("4 h");
		this.durations.add("5 h");
		this.durations.add("6 h");
	}
	
	public List<String> getDurations() {
		return this.durations;
	}
}
