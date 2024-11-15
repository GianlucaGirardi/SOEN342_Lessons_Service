package AccountManager;

import lessonServices.Booking;
import lessonServices.Lesson;
import lessonServices.LessonCatalog;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Administrator extends Account {
	private static volatile Administrator admin;  // Singleton instance
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

	public Administrator login(String userName, String password) {
		if (admin == null) {
			return null;
		}
		// Admin login check
		if (admin.getUserName().equals(userName) && admin.getPassword().equals(password)) {
			//System.out.println("Administrator logged in successfully.");
			return admin;
		} else {
			//System.out.println("Invalid username or password.");
			return null;
		}
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
		this.getLessonCatalog().displayAllLessons();
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

	private int checkUsernameExists(String username) {
		for (Instructor instructor : instructorCatalog.getInstructorCatalog()) {
			if (instructor.getUserName().equals(username)) {
				return 2;
			}
		}
		for (Client client : clientCatalog.getClients()) {
			if (client.getUserName().equals(username)) {
				return 1;
			}
		}
		return 0;
	}

	public boolean deleteAccount(String userName) {
		int type = checkUsernameExists(userName);
		if(type == 1){
			return this.getClientCatalog().deleteClient(this.getClientCatalog().getClientByUserName(userName));
		}
		else if(type == 2){
			Instructor instructor = this.getInstructorCatalog().findInstructorByUserName(userName);
			this.deleteInstructorAccount(instructor);
			return this.getInstructorCatalog().getInstructors().remove(instructor);
		}
		else{
			throw new IllegalArgumentException("Account with username " + userName + " does not exist.");
		}
	}

	private boolean deleteInstructorAccount(Instructor instructor) {
		ArrayList<Lesson> takenUpLessons = new ArrayList<>(instructor.getTakenUpLessons());

		if (takenUpLessons.isEmpty()) {
			System.out.println("Instructor has no lessons to remove.");
			return false;
		}

		for (Lesson lesson : takenUpLessons) {
			long lessonId = lesson.getLESSON_ID();

			ArrayList<Client> clientsCopy = new ArrayList<>(this.getClientCatalog().getClients());

			// UnBook each lesson for all clients
			for (Client client : clientsCopy) {
				try {
					boolean success = client.unBookLesson(lessonId);
					if (!success) {
						throw new IllegalStateException("Failed to un-book lesson for client " + client.getUserName());
					}
				} catch (Exception e) {
					throw new IllegalStateException("Error while un-booking lesson: " + e.getMessage(), e);
				}
			}

			// At this point instructor can be removed from the lesson
			if (lesson.getCurrentCapacity() == 0) {
				boolean removed = instructor.removeTakenUpLesson(lessonId); // Remove the lesson from instructor
				if (!removed) {
					throw new IllegalStateException("Failed to remove taken-up lesson with ID " + lessonId);
				}
			}
		}
		return true;
	}

	public boolean removeBookingFromClient(long bookingId){
		for(Client client: this.getClientCatalog().getClients()){
			ArrayList<Booking> bookings = new ArrayList<>(client.getBookingCatalog().getBookings());
			for(Booking booking : bookings){
				if(bookingId == booking.getBookingId()){
					return client.unBookLesson(booking.getLesson().getLESSON_ID());
				}
			}
		}
		return false;
	}

	public void viewClientBookings(String userName) {
		for (Client client : clientCatalog.getClients()) {
			if (client.getUserName().equals(userName)) {
				client.displayAllAssociatedBookings();
			}
		}
	}

	public static void resetInstance() {
		admin = null;
	}

	@Override
	public String toString() {
		return "Administrator [userName=" + getUserName() + ", firstName=" + getFirstName() +
				", lastName=" + getLastName() + ", lessonCatalog=" + (getLessonCatalog() != null ? getLessonCatalog().toString() : "N/A") + "]";
	}
}
