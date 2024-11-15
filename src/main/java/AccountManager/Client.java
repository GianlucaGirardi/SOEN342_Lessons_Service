package AccountManager;

import lessonServices.Booking;
import lessonServices.BookingCatalog;
import lessonServices.Lesson;
import lessonServices.LessonCatalog;
import java.util.ArrayList;

public class Client extends Account {
	private int age;
	private BookingCatalog bookingCatalog;
	private ArrayList<MinorClient> dependents;

	public Client(String firstName, String lastName, String userName, String password, LessonCatalog lessonCatalog, int age) {
		super(firstName, lastName, userName, password, lessonCatalog);
		this.age = age;
		this.bookingCatalog = new BookingCatalog();
		this.dependents = new ArrayList<MinorClient>();
	}

	public ArrayList<MinorClient> getDependents(){
		return this.dependents;
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

	public void setBookingCatalog(BookingCatalog bookingCatalog) {
		this.bookingCatalog = bookingCatalog;
	}

	public void displayAllAssociatedBookings() {
		this.bookingCatalog.displayAllBookings();
	}

	public Booking bookLesson(long lessonId) {
		// verify that a client is not rebooking a lesson
		for (Booking booking : this.bookingCatalog.getBookings()) {
			if (booking.getLesson().getLESSON_ID() == lessonId) {
				System.out.println(booking.getLesson().getLESSON_ID());
				System.out.println("Client has already booked this lesson.");
				return null;
			}
		}
		// Obtain the lesson
		Lesson lesson = super.getLessonCatalog().searchLessonById(lessonId);
		// Verify that the lesson has space
		if (lesson == null || !lesson.isAvailable()) {
			System.out.println("Lesson is full. Cannot book this lesson.");
			return null;
		}
		return bookingCatalog.bookLesson(lesson);
	}

public boolean unBookLesson(long lessonId){
	return bookingCatalog.unBookLesson(lessonId);
}

public boolean emptyBookings() {
	// Make a copy in order to not alter the list being iterated over
	ArrayList<Booking> bookings = new ArrayList<>(this.getBookingCatalog().getBookings());
	for (Booking booking : bookings) {
		System.out.println("count");
		boolean success = this.unBookLesson(booking.getLesson().getLESSON_ID());
		if (!success) {
			System.out.println("An error has occurred while removing a booking");
		}
	}
	return true;
}

@Override
public boolean equals(Object obj) {
	if (this == obj) return true;
	if (obj == null || getClass() != obj.getClass()) return false;
	Client client = (Client) obj;
	return getUserName().equals(client.getUserName());
}

	public Booking bookLessonDependent(String depUserName, long lessonId){
		ArrayList<MinorClient> dependents = this.getDependents();
		for (MinorClient minorClient : dependents) {
			if (minorClient.getUserName().equals(depUserName)) {
				return minorClient.bookLesson(lessonId);
			}
		}
		System.out.println("System was unable to find the dependent");
		return null;
	}

	public boolean unBookLessonDependent(String depUserName, long lessonId){
		ArrayList<MinorClient> dependents = this.getDependents();
		for (MinorClient minorClient : dependents) {
			if (minorClient.getUserName().equals(depUserName)) {
				return minorClient.unBookLesson(lessonId);
			}
		}
		System.out.println("System was unable to unbook the lesson.");
		return false;
	}


@Override
public String toString() {
	return super.toString() + " age= " + this.age;
}
}


