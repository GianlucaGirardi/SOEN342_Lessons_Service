package AccountManager;

import lessonServices.LessonCatalog;

import java.util.ArrayList;

public class InstructorCatalog {
	private ArrayList<Instructor> instructors;
	private ClientCatalog clientCatalog;

	public InstructorCatalog() {
		this.instructors = new ArrayList<Instructor>();
		this.clientCatalog = null;
	}

	public ArrayList<Instructor> getInstructors(){
		return this.instructors;
	}

	public void setClientCatalog(ClientCatalog clientCatalog) {
		this.clientCatalog = clientCatalog;
	}

	public String register(String firstName, String lastName, String username, String password, LessonCatalog lessonCatalog, String phoneNumber, String specialization, ArrayList<String> availabilities) {
		if (checkUsernameExists(username)) {
			return "Username already exists. Please choose another username.";
		}
		Instructor newInstructor = new Instructor(firstName, lastName, username, password, lessonCatalog, phoneNumber, specialization, availabilities);
		this.instructors.add(newInstructor);
		return "Instructor registered successfully: " + newInstructor.toString();
	}

	private boolean checkUsernameExists(String username) {
		for (Instructor instructor : instructors) {
			if (instructor.getUserName().equals(username)) {
				return true;
			}
		}
		for (Client client : clientCatalog.getClients()) {
			if (client.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public Instructor login(String username, String password) {
		for (Instructor instructor : instructors) {
			if (instructor.getUserName().equals(username)) {
				if (instructor.getPassword().equals(password)) {
					//System.out.println("Login successful!");
					return instructor;
				} else {
					//System.out.println("Invalid password.");
					return null;
				}
			}
		}
		//System.out.println("Username not found.");
		return null;
	}

	public Instructor findInstructorByUserName(String userName) {
		return instructors.stream()
				.filter(instructor -> instructor.getUserName().equals(userName))
				.findFirst()
				.orElse(null);
	}

	public ArrayList<Instructor> getInstructorCatalog() {
		return new ArrayList<>(this.instructors);
	}

	public void displayAllInstructors() {
		if (this.instructors.isEmpty()) {
			System.out.println("No instructors available.");
		} else {
			this.instructors.forEach(instructor -> System.out.println(instructor));
		}
	}

	public boolean removeInstructor(String userName) {
		return instructors.removeIf(instructor -> instructor.getUserName().equals(userName));
	}

	public void displayInstructorCount() {
		System.out.println("Total instructors: " + instructors.size());
	}
}



