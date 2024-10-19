package AccountManager;

import java.util.ArrayList;

import lessonServices.Booking;
import lessonServices.OfferingCatalog;

public class LegalGuardianClient extends Client{
	private String dependentFirstName;
	private String dependentLastName;
	private int dependentAge;
	private ArrayList<Booking> dependentBookings;
	
	public LegalGuardianClient(String firstName, String lastName, OfferingCatalog offeringCatalog, int age, String dependentFirstName, String dependentLastName, int dependentAge) {
		super(firstName, lastName, offeringCatalog, age);
		this.dependentAge = dependentAge;
		this.dependentLastName = dependentLastName;
		this.dependentFirstName = dependentFirstName;
		this.dependentBookings = new ArrayList<Booking>();
	}

	public String getDependentFirstName() {
		return dependentFirstName;
	}

	public void setDependentFirstName(String dependentFirstName) {
		this.dependentFirstName = dependentFirstName;
	}

	public String getDependentLastName() {
		return dependentLastName;
	}

	public void setDependentLastName(String dependentLastName) {
		this.dependentLastName = dependentLastName;
	}

	public int getDependentAge() {
		return dependentAge;
	}

	public void setDependentAge(int dependentAge) {
		this.dependentAge = dependentAge;
	}

	public ArrayList<Booking> getDependentBookings() {
		return dependentBookings;
	}

	public void setDependentBookings(ArrayList<Booking> dependentBookings) {
		this.dependentBookings = dependentBookings;
	}

	@Override
	public String toString() {
		return "LegalGuardianClient" + super.toString() + " dependentFirstName=" + dependentFirstName + ", dependentLastName="
				+ dependentLastName + ", dependentAge=" + dependentAge + ", dependentBookings=" + dependentBookings;
	}
	
	
	// addBooking
	
	// findBooking
	
	
}
