package lessonServices;

import AccountManager.Instructor;
import scheduleManager.Schedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson {
	private static long lessonsCnter = 1;
	private long LESSON_ID;
	private String name;
	private int initialCapacity;
	private int currentCapacity;
	private String lessonType;
	private Schedule schedule;
	private ArrayList<Booking> bookings;
	private Instructor instructor = null;

	public Lesson(String lessonName, int initialCapacity, String city, String locationName, String space,
				  LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		LESSON_ID = lessonsCnter++;
		this.name = validateLessonName(lessonName);
		this.initialCapacity = initialCapacity;
		this.currentCapacity = 0;
		this.lessonType = getLessonType(initialCapacity);
		this.schedule = new Schedule(city, locationName, space, startDate, endDate, daysOfWeek, startHour, endHour);
		this.bookings = new ArrayList<>();
	}

	public long getLESSON_ID() {
		return LESSON_ID;
	}

	public static void setLessonsCounter(long value) {
		lessonsCnter = value;
	}

	private String validateLessonName(String lessonName) {
		if (lessonName == null || lessonName.trim().isEmpty()) {
			throw new IllegalArgumentException("Lesson name cannot be null or empty.");
		}
		return lessonName;
	}

	public Schedule getSchedule(){
		return this.schedule;
	}

	public int getInitialCapacity() {
		return this.initialCapacity;
	}

	public int getCurrentCapacity() {
		return this.currentCapacity;
	}


	public Instructor getInstructor(){
		return this.instructor;
	}

	public void setInstructor(Instructor instructor){
		this.instructor = instructor;
	}

	public String getLessonType(int initialCapacity) {
		return initialCapacity > 1 ? "group" : "private";
	}

	public boolean checkForScheduleOverlap(String city, String locationName, LocalDate startDate, LocalDate endDate,
										   String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		return this.schedule.checkForOverlap(city, locationName, startDate, endDate, daysOfWeek, startHour, endHour);
	}

	public boolean isAvailable() {
		return currentCapacity >= 0 && currentCapacity < this.getInitialCapacity();
	}

	public void addBooking(Booking booking) {
		bookings.add(booking);
	}
	public void setCurrentCapacity(int newCapacity) {
		this.currentCapacity = newCapacity;
	}

//	public void addBooking(Booking booking) {
//		if (isAvailable()) {
//			bookings.add(booking);
//			setCurrentCapacity(currentCapacity - 1);
//		} else {
//			System.out.println("No more spots available for this lesson.");
//		}
//	}

	public boolean removeBooking(Booking booking) {
		System.out.println(booking.getBookingId());
		return bookings.remove(booking);
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public String getName() {
		return name;
	}



	@Override
	public String toString() {
		return "Lesson ID " + this.LESSON_ID + ", Name: " + this.name + ", Type: " + this.lessonType + ", Schedule: " + this.schedule;
	}
}
