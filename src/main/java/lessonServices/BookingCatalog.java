package lessonServices;

import java.util.ArrayList;

public class BookingCatalog {
	private ArrayList<Booking> bookings;

	public BookingCatalog() {
		this.bookings = new ArrayList<>();
	}

	public ArrayList<Booking> getBookings() {
		return this.bookings;
	}

	public Booking bookLesson(Lesson lesson) {
		if (lesson.isAvailable()) {
			Booking newBooking = new Booking(lesson);
			bookings.add(newBooking);
			lesson.addBooking(newBooking);
			return newBooking;
		}
		System.out.println("Lesson is full. Cannot book this lesson.");
		return null;
	}

	public boolean unBookLesson(long bookingId) {
		for (Booking booking : bookings) {
			if (booking.getBookingId() == bookingId) {
				Lesson lesson = booking.getLesson();
				lesson.removeBooking(String.valueOf(bookingId));
				bookings.remove(booking);
				lesson.setCurrentCapacity(lesson.getCurrentCapacity() + 1);
				System.out.println("Booking " + bookingId + " successfully removed.");
				return true;
			}
		}
		System.out.println("Booking not found.");
		return false;
	}

	public void displayAllBookings() {
		if (bookings.isEmpty()) {
			System.out.println("No bookings available.");
		} else {
			bookings.forEach(booking -> System.out.println(booking));
		}
	}
}
