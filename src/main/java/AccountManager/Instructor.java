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


	public boolean takeUpLesson(long lessonId) {
		Lesson lesson = this.lessonCatalog.searchLessonById(lessonId);
		if (lesson != null) {
			if (lesson.getInstructor() != null) {
				System.out.println("This lesson already has an instructor.");
				return false;
			}

			String location = lesson.getSchedule().getCity();
			for (String availability : availabilities) {
				if (availability.equals(location)) {
					lesson.setInstructor(this);
					return this.takenUpLessons.add(lesson);
				}
			}
		}
		System.out.println("Instructor cannot take up the lesson due to location mismatch or other reasons.");
		return false;
	}

	public boolean removeTakenUpLesson(long lessonId) {
		Lesson lessonToRemove = null;
		for (Lesson lesson : takenUpLessons) {
			if (lesson.getLESSON_ID() == lessonId) {
				lessonToRemove = lesson;
				break;
			}
		}

		if (lessonToRemove == null) {
			throw new IllegalArgumentException("No taken-up lesson found with ID " + lessonId);
		}

		lessonToRemove.setInstructor(null);
		takenUpLessons.remove(lessonToRemove);
		System.out.println("Lesson successfully removed.");
		return true;
	}


	@Override
	public String toString() {
//		return String.format("Instructor [userName=%s, firstName=%s, lastName=%s, phone number=%s, specialization=%s, availability cities=%s, takenUpLessons=%s]",
//				getUserName(), getFirstName(), getLastName(), phoneNumber, specialization, availabilities.toString(), takenUpLessons.toString());
//
		return "";
	}
}
