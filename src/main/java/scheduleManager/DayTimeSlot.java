package scheduleManager;

import java.time.LocalTime;
import java.util.Objects;

public class DayTimeSlot {
	private String day;
	private LocalTime start;
	private LocalTime end;

	public DayTimeSlot(String day, LocalTime start, LocalTime end) {
		this.day = day;
		this.start = start;
		this.end = end;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof DayTimeSlot)) return false;
		DayTimeSlot timeslot = (DayTimeSlot) o;
		return day.equals(timeslot.day) && start.equals(timeslot.start) && end.equals(timeslot.end);
	}

	@Override
	public int hashCode() {
		return Objects.hash(day, start, end);
	}

	@Override
	public String toString(){
		return "\nday " + this.day + "\nstart " + this.start  + "\nend " + this.end;
	}
	
}
