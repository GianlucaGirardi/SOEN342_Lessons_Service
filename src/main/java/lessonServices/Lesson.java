package lessonServices;

import scheduleManager.Schedule;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Lesson {
	private static long lessonsCnter = 1;
	private long LESSON_ID;
	private String name;
	private int initialCapacity;
	private String lessonType;
	private Schedule schedule;
	private ArrayList<Booking> bookings;

	public Lesson(String lessonName, int initialCapacity, String city, String locationName, String space,
				  LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		LESSON_ID = lessonsCnter++;
		this.name = validateLessonName(lessonName);
		this.initialCapacity = initialCapacity;
		this.lessonType = getLessonType(initialCapacity);
		this.schedule = new Schedule(city, locationName, space, startDate, endDate, daysOfWeek, startHour, endHour);
		this.bookings = new ArrayList<>();
	}

	public long getLESSON_ID(){
		return LESSON_ID;
	}

	private String validateLessonName(String lessonName) {
		if (lessonName == null || lessonName.trim().isEmpty()) {
			throw new IllegalArgumentException("Lesson name cannot be null or empty.");
		}
		return lessonName;
	}

	public int getInitialCapacity(){
		return this.initialCapacity;
	}

	public String getLessonType(int initialCapacity) {
		if (initialCapacity > 1) {
			return "group";
		} else {
			return "private";
		}
	}

	public boolean checkForScheduleOverlap(String city, String locationName, LocalDate startDate, LocalDate endDate,
										   String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		return this.schedule.checkForOverlap(city, locationName, startDate, endDate, daysOfWeek, startHour, endHour);
	}

	public boolean isAvailable() {
		return bookings.size() < this.getInitialCapacity();
	}

	public void addBooking(Booking booking) {
		if (isAvailable()) {
			bookings.add(booking);
		} else {
			System.out.println("No more spots available for this lesson.");
		}
	}

	public boolean removeBooking(String bookingId) {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == Long.parseLong(bookingId)) {
				bookings.remove(booking);
				return true;
			}
		}
		return false;
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
