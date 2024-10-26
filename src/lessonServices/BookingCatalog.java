package lessonServices;

import java.util.ArrayList;

public class BookingCatalog {
	private ArrayList<Booking> bookings;

	public BookingCatalog() {
		this.bookings = new ArrayList<Booking>();
	}

	public void  displayAllBookings() {
		this.bookings.forEach(el -> System.out.println(el));;
	}
	
}
