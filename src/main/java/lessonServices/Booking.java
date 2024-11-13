package lessonServices;

public class Booking {
	private static long bookingCounter = 1;
	private final long BOOKING_ID;

	private Lesson lesson;

	public Booking(Lesson lesson) {
		this.BOOKING_ID = bookingCounter++;
		this.lesson = lesson;
	}

	public long getBookingId() {
		return this.BOOKING_ID;
	}

	public Lesson getLesson() {
		return this.lesson;
	}

	@Override
	public String toString() {
		return "Booking [BOOKING_ID=" + BOOKING_ID + ", lesson=" + this.lesson.toString() + "]";
	}
}
