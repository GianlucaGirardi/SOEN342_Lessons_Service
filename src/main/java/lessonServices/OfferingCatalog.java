package lessonServices;

import java.util.ArrayList;

// Will be created once in the driver
public class OfferingCatalog {
	private ArrayList<Offering> offerings;

	public OfferingCatalog() {
		this.offerings = new ArrayList<Offering>();
	}
	
	public ArrayList<Offering> getOfferings(){
		return this.offerings;
	}

	public void  displayOfferings() {
		this.offerings.forEach(el -> System.out.println(el));
	}
}
