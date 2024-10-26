package AccountManager;

import lessonServices.OfferingCatalog;

public class Account {
	private static long accountCounter = 1000000;
	private long ACCOUNT_ID = accountCounter;
	private OfferingCatalog offeringCatalog;
	private String firstName;
	private String lastName;
	
	public Account() {
		accountCounter++;
		this.offeringCatalog = null;
		this.firstName = null;
		this.lastName = null;
	}
	
	public Account(String firstName, String lastName, OfferingCatalog offeringCatalog) {
		accountCounter++;
		this.offeringCatalog = offeringCatalog;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public long getACCOUNT_ID() {
		return ACCOUNT_ID;
	}

	public OfferingCatalog getOfferingCatalog() {
		return offeringCatalog;
	}

	public void setOfferingCatalog(OfferingCatalog offeringCatalog) {
		this.offeringCatalog = offeringCatalog;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Account id " + ACCOUNT_ID +", firstName=" + firstName
				+ ", lastName=" + lastName;
	}
}
