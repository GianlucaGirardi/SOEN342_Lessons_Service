package AccountManager;

import lessonServices.BookingCatalog;
import lessonServices.LessonCatalog;

public class Client extends Account{
	private int age;
	private BookingCatalog bookingCatalog;
	
	public Client(String firstName, String lastName, String userName, String password, LessonCatalog lessonCatalog, int age) {
		super(firstName, lastName, userName, password, lessonCatalog);
		this.age = age;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Client client = (Client) obj;
		return getUserName().equals(client.getUserName());
	}

	@Override
	public int hashCode() {
		return getUserName().hashCode();
	}
	
	public String toString() {
		return super.toString() + "age= " + this.age;
	}
}

