package lessonServices;

import scheduleManager.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class Lesson {
	private static long lessonsCnter = 1;
	private long LESSON_ID;
	private String name;
	private String lessonType;
	private Schedule schedule;
	
	public Lesson(String lessonName, String lessonType, String city, String locationName, String space, LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		LESSON_ID = lessonsCnter++;
		this.name = lessonName;
		this.lessonType = lessonType;
		this.schedule = new Schedule(city, locationName, space, startDate, endDate, daysOfWeek, startHour, endHour);
	}

	public boolean checkForScheduleOverlap(String city, String locationName, LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour){
		return this.schedule.checkForOverlap(city, locationName, startDate, endDate, daysOfWeek, startHour, endHour);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return LESSON_ID == other.LESSON_ID;
	}

	@Override
	public String toString(){
		return "Lesson ID " + this.LESSON_ID + " lesson name " + this.name + " lesson type " + this.lessonType + " schedule " + this.schedule;
	}

	
}
