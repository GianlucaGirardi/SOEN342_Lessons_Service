package AccountManager;

import java.util.ArrayList;

import lessonServices.Booking;
import lessonServices.LessonCatalog;

public class MinorClient extends Client{
	private String guardianFirstName;
	private String guardianLastName;
	private int guardianAge;
	private ArrayList<Booking> minorBookings;
	private String userName;

	public MinorClient(String firstName, String lastName, String userName, String password, LessonCatalog lessonCatalog, int age) {
		super(firstName, lastName, userName, password, lessonCatalog, age);
		this.minorBookings = super.getBookingCatalog().getBookings();
		this.userName=userName;
	}

	public String getUserName(){
		return this.userName;
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
		return this.minorBookings;
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
//		return "MinorClient " + super.toString();
		return "";
	}
}
