package AccountManager;

import lessonServices.OfferingCatalog;
import scheduleManager.Schedule;

public class Instructor extends Account{
	private String phoneNumber;
	private String specialization;
	private Schedule availability;
	private OfferingCatalog offeringCatalog;
	
	public Instructor() {
		
	}
	
	public Instructor(String firstName, String lastName, String phoneNumber, String specialization, OfferingCatalog offeringCatalog) {
		super(firstName, lastName, offeringCatalog);
		this.phoneNumber = phoneNumber;
		this.specialization = specialization;
		//this.availability = new Schedule();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Schedule getAvailability() {
		return availability;
	}

	public void setAvailability(Schedule availability) {
		this.availability = availability;
	}

	@Override
	public String toString() {
		return "Instructor" + super.toString() + "phone number " + phoneNumber + ", specialization=" + specialization + ", availability="
				+ availability + ", offeringCatalog=" + offeringCatalog + "]";
	}
	
	
}
