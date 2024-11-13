package AccountManager;

import lessonServices.Lesson;
import lessonServices.LessonCatalog;

import java.time.LocalDate;
import java.time.LocalTime;

public class Administrator extends Account {
	private static volatile Administrator admin;
	private ClientCatalog clientCatalog;
	private InstructorCatalog instructorCatalog;

	private Administrator(String firstName, String lastName, String userName, String password,
						  LessonCatalog lessonCatalog, ClientCatalog clientCatalog, InstructorCatalog instructorCatalog) {
		super(firstName, lastName, userName, password, lessonCatalog);
		this.clientCatalog = clientCatalog;
		this.instructorCatalog = instructorCatalog;
	}

	public static Administrator getAdministrator(String firstName, String lastName, String userName, String password,
												 LessonCatalog lessonCatalog, ClientCatalog clientCatalog, InstructorCatalog instructorCatalog) {
		if (admin == null) {
			synchronized (Administrator.class) {
				if (admin == null) {
					admin = new Administrator(firstName, lastName, userName, password,
							lessonCatalog, clientCatalog, instructorCatalog);
				}
			}
		} else {
			System.out.println("An admin already exists.");
		}
		return admin;
	}

	public ClientCatalog getClientCatalog() {
		return clientCatalog;
	}

	public InstructorCatalog getInstructorCatalog() {
		return instructorCatalog;
	}

	public LessonCatalog getLessonCatalog(){
		return super.getLessonCatalog();
	}

	public void displayLessons(){
		this.getLessonCatalog().displayLessons();
	}

	public void displayClients() {
		if (clientCatalog != null) {
			System.out.println("Displaying all clients:");
			clientCatalog.displayAllClients();
		} else {
			System.out.println("No client catalog available.");
		}
	}

	public void displayInstructors() {
		if (instructorCatalog != null) {
			System.out.println("Displaying all instructors:");
			instructorCatalog.displayAllInstructors();
		} else {
			System.out.println("No instructor catalog available.");
		}
	}

	public void createLesson(String lessonName, int initialCapacity, String city, String locationName, String space,
							   LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour, LessonCatalog lessonCatalog) {
		Lesson newLesson = lessonCatalog.createLesson(lessonName, initialCapacity, city, locationName, space,
				startDate, endDate, daysOfWeek, startHour, endHour);
		if (newLesson != null) {
			System.out.println("Lesson created successfully: " + newLesson);
		} else {
			System.out.println("Could not create the lesson due to overlap with existing lessons.");
		}
	}

	@Override
	public String toString() {
		return "Administrator [userName=" + getUserName() + ", firstName=" + getFirstName() +
				", lastName=" + getLastName() + ", lessonCatalog=" + (getLessonCatalog() != null ? getLessonCatalog().toString() : "N/A") + "]";
	}
}
