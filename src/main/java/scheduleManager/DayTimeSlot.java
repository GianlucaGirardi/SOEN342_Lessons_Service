package scheduleManager;

import java.util.Objects;

public class DayTimeSlot {
	private String dayOfTheWeek;
	private String startTime;
	private String endTime;
	
	public DayTimeSlot() {
		this.dayOfTheWeek = null;
		this.startTime = null;
		this.endTime = null;
	}
	
	public DayTimeSlot(String dayOfTheWeek, String startTime, String endTime) {
		this.dayOfTheWeek = dayOfTheWeek;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public String getDayOfTheWeek() {
		return dayOfTheWeek;
	}

	public void setDayOfTheWeek(String dayOfTheWeek) {
		this.dayOfTheWeek = dayOfTheWeek;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DayTimeSlot other = (DayTimeSlot) obj;
		return Objects.equals(dayOfTheWeek, other.dayOfTheWeek) && Objects.equals(endTime, other.endTime)
				&& Objects.equals(startTime, other.startTime);
	}

	@Override
	public String toString() {
		return "DayTimeSlot [dayOfTheWeek=" + dayOfTheWeek + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", getDayOfTheWeek()=" + getDayOfTheWeek() + ", getStartTime()=" + getStartTime() + ", getEndTime()="
				+ getEndTime() + "]";
	}
	
}
