package scheduleManager;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Schedule {
	private LocalDate startDate;
	private LocalDate endDate;
	private String daysOfWeek;
	private LocalTime startHour;
	private LocalTime endHour;
	private Set<DayTimeSlot> timeslots;
	private String city;
	private String name;
	private String space;

	public Schedule(String city, String name, String space, LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.daysOfWeek = daysOfWeek;
		this.startHour = startHour;
		this.endHour = endHour;
		this.timeslots = generateTimeslots();
		this.city = city;
		this.name = name;
		this.space = space;
	}

	private Set<DayTimeSlot> generateTimeslots() {
		Set<DayTimeSlot> slots = new HashSet<>();
		ArrayList<String> daysOfWeekArr = new ArrayList<>(Arrays.asList(this.daysOfWeek.split("#")));
		for (String day : daysOfWeekArr) {
			LocalTime slotStart = startHour;
			while (!slotStart.isAfter(endHour.minusMinutes(30))) {
				LocalTime slotEnd = slotStart.plusMinutes(30);
				slots.add(new DayTimeSlot(day, slotStart, slotEnd));
				slotStart = slotEnd;
			}
		}
		return slots;
	}

	public  boolean checkForOverlap(String newCity, String newName, LocalDate newStartDate, LocalDate newEndDate, String newDaysOfWeek, LocalTime newStartHour, LocalTime newEndHour) {
		Set<DayTimeSlot> newTimeslots = new HashSet<>();
		ArrayList<String> newDaysOfWeekArr = new ArrayList<>(Arrays.asList(newDaysOfWeek.split("#")));
		for (String day : newDaysOfWeekArr) {
			LocalTime slotStart = newStartHour;
			while (!slotStart.isAfter(newEndHour.minusMinutes(30))) {
				LocalTime slotEnd = slotStart.plusMinutes(30);
				newTimeslots.add(new DayTimeSlot(day, slotStart, slotEnd));
				slotStart = slotEnd;
			}
		}
			if ((this.startDate.isBefore(newEndDate) || this.endDate.isAfter(newStartDate)) && this.city.equals(newCity) && this.name.equals(newName)) {
				for (DayTimeSlot el : newTimeslots) {
					if (this.timeslots.contains(el)) {
						return true;
					}
				}
			}
		return false;
	}

	@Override
	public String toString() {
		return "Schedule " +
				"startDate " + startDate +
				", endDate " + endDate +
				", daysOfWeek " + daysOfWeek +
				", startHour " + startHour +
				", endHour " + endHour +
				", timeslots " + timeslots +
				", location " + city + " " + name  +
				", space " + space;
	}
}
