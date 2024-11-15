package AccountManager;

import java.util.ArrayList;

import lessonServices.Booking;
import lessonServices.LessonCatalog;

public class MinorClient extends Client {
	private String guardianFirstName;
	private String guardianLastName;
	private int guardianAge;

	public MinorClient(String firstName, String lastName, String userName, String password, LessonCatalog lessonCatalog, int age, String guardianFName, String guardianLName, int guardianAge) {
		super(firstName, lastName, userName, password, lessonCatalog, age);
		this.guardianFirstName = guardianFName;
		this.guardianLastName = guardianLName;
		this.guardianAge = guardianAge;
	}

	public String getGuardianFirstName() {
		return guardianFirstName;
	}

	public void setGuardianFirstName(String guardianFirstName){
		this.guardianFirstName = guardianFirstName;
	}

	public String getGuardianLastName() {
		return guardianLastName;
	}

	public void setGuardianLastName(String guardianLastName){
		this.guardianLastName = guardianLastName;
	}

	public int getGuardianAge(){
		return this.guardianAge;
	}


	public ArrayList<Booking> getBookings() {
		return super.getBookingCatalog().getBookings();
	}

	@Override
	public Booking bookLesson(long lessonId) {
		return super.bookLesson(lessonId);
	}

	@Override
	public boolean unBookLesson(long lessonId){
		return super.unBookLesson(lessonId);
	}

	@Override
	public String toString() {
		return "MinorClient " + super.toString();
	}
}
