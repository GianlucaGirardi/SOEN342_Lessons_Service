package lessonServices;

import java.util.ArrayList;
import java.util.Objects;

public class Offering {
	private static long offeringCounter = 10000000;
	private final long OFFERING_ID = offeringCounter;
	private boolean isAvailableToPublic;
	private ArrayList<Lesson> lessons;
	
	public Offering() {
		offeringCounter++;
		this.isAvailableToPublic = false;
		this.lessons = new ArrayList<Lesson>();
	}

	public boolean isAvailableToPublic() {
		return isAvailableToPublic;
	}

	public void setAvailableToPublic(boolean isAvailableToPublic) {
		this.isAvailableToPublic = isAvailableToPublic;
	}

	public long getOFFERING_ID() {
		return OFFERING_ID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Offering other = (Offering) obj;
		return OFFERING_ID == other.OFFERING_ID && isAvailableToPublic == other.isAvailableToPublic
				&& Objects.equals(lessons, other.lessons);
	}

	@Override
	public String toString() {
		return "Offering [OFFERING_ID=" + OFFERING_ID + ", isAvailableToPublic=" + isAvailableToPublic + ", lessons="
				+ lessons + ", isAvailableToPublic()=" + isAvailableToPublic() + ", getOFFERING_ID()="
				+ getOFFERING_ID() + "]";
	}
	
}
