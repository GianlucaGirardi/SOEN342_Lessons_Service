package AccountManager;

import java.util.ArrayList;

import lessonServices.Booking;
import lessonServices.LessonCatalog;

public class LegalGuardianClient extends Client {
	private ArrayList<Client> legalGuardians;
	private ArrayList<Booking> bookings;

	public LegalGuardianClient(String firstName, String lastName, String userName, String password, LessonCatalog lessonCatalog, int age, ArrayList<Client> legalGuardians) {
		super(firstName, lastName, userName, password, lessonCatalog, age);  // Initialize superclass (Client) fields
		this.legalGuardians = legalGuardians;
		this.bookings = new ArrayList<Booking>();
	}


	public ArrayList<Client> getLegalGuardians() {
		return legalGuardians;
	}


	public void setLegalGuardians(ArrayList<Client> legalGuardians) {
		this.legalGuardians = legalGuardians;
	}


	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public void addLegalGuardian(Client legalGuardian) {
		if (legalGuardian != null) {
			this.legalGuardians.add(legalGuardian);
		} else {
			System.out.println("Cannot add null legal guardian.");
		}
	}

	public boolean removeLegalGuardian(String userName) {
		return legalGuardians.removeIf(legalGuardian -> legalGuardian.getUserName().equals(userName));
	}

	public void addBooking(Booking booking) {
		if (booking != null) {
			this.bookings.add(booking);
		} else {
			System.out.println("Cannot add null booking.");
		}
	}

//	public boolean removeBooking(String bookingId) {
//		return bookings.removeIf(booking -> booking.getBookingId().equals(bookingId));
//	}

//	public Booking findBooking(String bookingId) {
//		return bookings.stream()
//				.filter(booking -> booking.getBookingId().equals(bookingId))
//				.findFirst()
//				.orElse(null);  // Return null if no booking is found
//	}

//	public boolean canBookLesson(Booking booking) {
//		// Iterate through legal guardians to check if any of them have booked the same lesson
//		for (Client guardian : legalGuardians) {
//			for (Booking guardianBooking : guardian.getBookings()) {
//				if (guardianBooking.getLessonId().equals(booking.getLessonId())) {
//					return true;  // Guardian is attending the same lesson, booking is allowed
//				}
//			}
//		}
//		return false;  // No guardian is attending this lesson
//	}

//	public boolean bookLesson(Booking booking) {
//		if (canBookLesson(booking)) {
//			this.addBooking(booking);  // Add the booking if a legal guardian is attending
//			System.out.println("Lesson booked successfully for " + this.getUserName());
//			return true;
//		} else {
//			System.out.println("Booking failed: No legal guardian is attending this lesson.");
//			return false;
//		}
//	}

	@Override
	public String toString() {
		return "LegalGuardianClient " + super.toString() +
				", legalGuardians=" + legalGuardians +
				", bookings=" + bookings;
	}
}
