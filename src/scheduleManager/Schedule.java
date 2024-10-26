package scheduleManager;

import java.util.ArrayList;
import java.util.Objects;

public class Schedule {
	private int startDate;
	private int endDate;
	private String startMonth;
	private String endMonth;
	private Location location;
	private ArrayList<DayTimeSlot> dayTimeSlots;
	
	public Schedule() {
		this.startDate = 1;
		this.endDate = 1;
		this.startMonth = null;
		this.endMonth = null;
		this.location = null;
	}
	
	
	public Schedule(int startDate, int endDate, String startMonth, String endMonth, String locationName, String city) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.startMonth = startMonth;
		this.endMonth = endMonth;
		this.location = new Location(locationName, city);
		this.dayTimeSlots = new ArrayList<DayTimeSlot>();
	}
	
	public void addTimeSlot(String dayOfTheWeek, String startTime, String endTime) {
		this.dayTimeSlots.add(new DayTimeSlot(dayOfTheWeek, startTime, endTime));
	}


	public int getStartDate() {
		return startDate;
	}


	public void setStartDate(int startDate) {
		this.startDate = startDate;
	}


	public int getEndDate() {
		return endDate;
	}


	public void setEndDate(int endDate) {
		this.endDate = endDate;
	}


	public String getStartMonth() {
		return startMonth;
	}


	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}


	public String getEndMonth() {
		return endMonth;
	}


	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedule other = (Schedule) obj;
		return endDate == other.endDate && Objects.equals(endMonth, other.endMonth) && startDate == other.startDate
				&& Objects.equals(startMonth, other.startMonth);
	}


	@Override
	public String toString() {
		return "Schedule startDate= " + startDate + ", endDate= " + endDate + ", startMonth= " + startMonth
				+ ", endMonth= " + endMonth + " , location" + location;
	}
	
}
