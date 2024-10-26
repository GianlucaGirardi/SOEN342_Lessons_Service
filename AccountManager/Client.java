package AccountManager;

import lessonServices.BookingCatalog;
import lessonServices.OfferingCatalog;

public class Client extends Account{
	private int age;
	private BookingCatalog bookingCatalog;
	
	public Client() {
		this.age = 0;
		this.bookingCatalog = new BookingCatalog();
	}
	
	public Client(String firstName, String lastName, OfferingCatalog offeringCatalog, int age) {
		super(firstName, lastName, offeringCatalog);
		this.age = 0;
		this.bookingCatalog = new BookingCatalog();
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age = age;
	}
	
	public BookingCatalog getBookingCatalog() {
		return this.bookingCatalog;
	}
	
	public void  setBookingCatalog(BookingCatalog bookingCatalog) {
		this.bookingCatalog = bookingCatalog;
	}
	
	public void displayAllAssociatedBookings() {
		this.bookingCatalog.displayAllBookings();
	}
	
	public String toString() {
		return super.toString() + "age= " + this.age;
	}
	
}

