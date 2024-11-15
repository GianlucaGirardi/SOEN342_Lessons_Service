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
		// Lesson has space & can be booked
		Booking newBooking = new Booking(lesson);
		// Add the new booking to the BookingCatalog
		bookings.add(newBooking);
		// Add the new booking to the lesson's list of bookings
		lesson.addBooking(newBooking);
		// Update the # of spots
		lesson.setCurrentCapacity(lesson.getCurrentCapacity() + 1);

		return newBooking;
	}

	public boolean unBookLesson(long bookingId) {
		// Iterate through bookings
		for (Booking booking : bookings) {
			// match the id to the booking
			if (booking.getBookingId() == bookingId) {
				// Get the associated lesson
				Lesson lesson = booking.getLesson();
				// remove the booking from the lesson bookings & update capacity
				lesson.removeBooking(booking);
				lesson.setCurrentCapacity(lesson.getCurrentCapacity() - 1);
				bookings.remove(booking);

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
