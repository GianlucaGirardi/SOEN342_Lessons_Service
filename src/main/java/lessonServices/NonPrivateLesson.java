package lessonServices;

import java.util.ArrayList;

public class NonPrivateLesson extends Lesson{
	private ArrayList<Booking> associatedBookings;
	private ArrayList<Offering> associatedOfferings;
	private String mode;
	
	public NonPrivateLesson() {
		this.associatedBookings = new ArrayList<Booking>();
		this.associatedOfferings = new ArrayList<Offering>();
		this.mode = "group";
	}
	
	public NonPrivateLesson(ArrayList<Offering> associatedOfferings, ArrayList<Booking> associatedBookings)  {
		this.associatedBookings = associatedBookings;
		this.associatedOfferings = associatedOfferings;
		this.mode = "group";
	}
	
	public String getMode() {
		return this.mode;
	}
	
	public ArrayList<Offering> getAssociatedOfferings(){
		return this.associatedOfferings;
	}
	
	public void setAssociatedOfferings(ArrayList<Offering> offerings) {
		this.associatedOfferings = offerings;
	}
	
	public void displayAllAssociatedOfferingDeatils() {
		System.out.println("Associated Offerings");
		System.out.println("********************");
		this.associatedOfferings.forEach(el -> System.out.println(el));
	}
	
	public void displayAllAssociatedBookingDetails() {
		System.out.println("Associated Bookings");
		System.out.println("********************");
		this.associatedBookings.forEach(el -> System.out.println(el));
	}
	
	public ArrayList<Booking> getAssociatedBookings(){
		return this.associatedBookings;
	}
	
	public void setAssociatedBookings(ArrayList<Booking> bookings) {
		this.associatedBookings = bookings;
	}
	
	public void getAllLessonDetails() {
		this.displayAllAssociatedOfferingDeatils();
		this.displayAllAssociatedBookingDetails();
		System.out.println("Mode: " + this.mode);
	}
	
}
