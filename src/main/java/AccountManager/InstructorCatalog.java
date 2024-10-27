package AccountManager;

import java.util.ArrayList;

import lessonServices.OfferingCatalog;

public class InstructorCatalog {
	private ArrayList<Instructor> instructorCatalog;
	
	public InstructorCatalog() {
		this.instructorCatalog = new ArrayList<Instructor>();
	}
	
	public void addInstructor() {
		this.instructorCatalog.add( new Instructor());
	}
	
	public void addInstructor(Instructor instructor) {
		this.instructorCatalog.add( instructor);
	}
	
	public void addInstructor(String firstName, String lastName, String phoneNumber, String specialization, OfferingCatalog offeringCatalog) {
		this.instructorCatalog.add( new Instructor(firstName, lastName, phoneNumber, specialization, offeringCatalog));
	}
	
//	public Instructor findInstructor() {}
	
	public ArrayList<Instructor> getInstructorCatalog(){
		return this.instructorCatalog;
	}
	
	public void displayAllInstructors() {
		this.instructorCatalog.forEach(el -> System.out.println(el));
	}
	
}


