package lessonServices;

import java.util.Objects;

public class PrivateLesson extends Lesson {
	private Booking associatedBooking;
	private Offering associatedOffering;
	private String mode;
	
	public PrivateLesson() {
		this.associatedBooking = new Booking();
		this.associatedOffering = new Offering();
		this.mode = "private";
	}
	
	public PrivateLesson( Offering offering, Booking booking) {
		this.associatedBooking = booking;
		this.associatedOffering = offering;
		this.mode = "private";
	}
	
	public String getMode() {
		return this.mode;
	}
	
	public Offering getAssociatedOffering() {
		return this.associatedOffering;
	}
	
	public void setAssociatedOffering(Offering offering) {
		this.associatedOffering = offering;
	}
	
	public void displayAssociatedOfferingDetails() {
		System.out.println(associatedOffering);
	}
	
	public Booking getAssociatedBooking() {
		return this.associatedBooking;
	}
	
	public void setAssociatedOffering(Booking booking) {
		this.associatedBooking = booking;
	}
	
	public void displayAssociatedBooking() {
		System.out.println(associatedBooking);
	}

	@Override
	public String toString() {
		return "PrivateLesson [associatedBooking=" + associatedBooking + ", associatedOffering=" + associatedOffering
				+ ", mode=" + mode + ", getMode()=" + getMode() + ", getAssociatedOffering()=" + getAssociatedOffering()
				+ ", getAssociatedBooking()=" + getAssociatedBooking() + ", getName()=" + getName() + ", toString()="
				+ super.toString();
	}
	
}
