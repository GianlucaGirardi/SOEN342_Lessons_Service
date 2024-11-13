package AccountManager;

import lessonServices.Lesson;
import lessonServices.LessonCatalog;

import java.util.ArrayList;

public class Instructor extends Account {
	private String phoneNumber;
	private String specialization;
	private ArrayList<String> availabilities;
	private ArrayList<Lesson> takenUpLessons;
	private LessonCatalog lessonCatalog;

	public Instructor(String firstName, String lastName, String userName, String password, LessonCatalog lessonCatalog,
					  String phoneNumber, String specialization, ArrayList<String> availabilities) {
		super(firstName, lastName, userName, password, lessonCatalog);
		this.phoneNumber = phoneNumber;
		this.specialization = specialization;
		this.availabilities = availabilities;
		this.takenUpLessons = new ArrayList<Lesson>();
		this.lessonCatalog = lessonCatalog;
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

	public ArrayList<String> getAvailabilities() {
		return availabilities;
	}

	public void setAvailabilities(ArrayList<String> availabilities) {
		this.availabilities = availabilities;
	}

	public ArrayList<Lesson> getTakenUpLessons() {
		return takenUpLessons;
	}


//	public void addTakenUpLesson(Lesson lesson) {
//		if (lessonCatalog.isAvailable(lesson)) {
//			this.takenUpLessons.add(lesson);
//		} else {
//			System.out.println("Lesson is not available in the catalog.");
//		}
//	}
//
//	public boolean removeTakenUpLesson(String lessonId) {
//		for (Lesson lesson : takenUpLessons) {
//			if (lesson.getLESSON_ID().equals(lessonId)) {
//				takenUpLessons.remove(lesson);
//				return true;
//			}
//		}
//		return false;
//	}
//
//	public boolean isAvailableInCityForLesson(Lesson lesson) {
//		return availabilities.contains(lesson.getCity());
//	}

	@Override
	public String toString() {
		return "Instructor " + super.toString() + " [phone number=" + phoneNumber + ", specialization=" + specialization +
				", availability cities=" + availabilities + ", takenUpLessons=" + takenUpLessons + ", offeringCatalog=" + lessonCatalog + "]";
	}
}
