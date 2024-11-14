package AccountManager;

import lessonServices.Booking;
import lessonServices.BookingCatalog;
import lessonServices.Lesson;
import lessonServices.LessonCatalog;

public class Client extends Account {
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

	public void setBookingCatalog(BookingCatalog bookingCatalog) {
		this.bookingCatalog = bookingCatalog;
	}

	public void displayAllAssociatedBookings() {
		this.bookingCatalog.displayAllBookings();
	}

	public Booking bookLesson(long lessonId) {
		Lesson lesson = super.getLessonCatalog().searchLessonById(lessonId);
		if (lesson == null || !lesson.isAvailable()) {
			return null;
		}
		return bookingCatalog.bookLesson(lesson);
	}

	public boolean unBookLesson(long lessonId){
		return bookingCatalog.unBookLesson(lessonId);
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

	@Override
	public String toString() {
		return super.toString() + " age= " + this.age;
	}
}


