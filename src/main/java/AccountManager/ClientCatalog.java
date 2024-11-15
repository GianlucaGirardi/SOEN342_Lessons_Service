package AccountManager;

import lessonServices.LessonCatalog;
import java.util.*;

public class ClientCatalog {
	private ArrayList<Client> clients;
	private InstructorCatalog instructorCatalog;

	public ClientCatalog(InstructorCatalog instructorCatalog) {
		this.clients = new ArrayList<Client>();
		this.instructorCatalog = instructorCatalog;
	}

	public String register(String firstName, String lastName, String username, String password, LessonCatalog lessonCatalog, int age) {
		if (checkUsernameExists(username)) {
			return "Username already exists. Please choose another username.";
		}
		Client registeredClient = new Client(firstName, lastName, username, password, lessonCatalog, age);
		addClient(registeredClient);
		return "Registration successful: " + registeredClient.toString();
	}

	public String registerMinorAccount(String firstName, String lastName, String username, String password, LessonCatalog lessonCatalog, int age, String guardianFName, String guardianLName, int guardianAge) {
		if (checkUsernameExists(username)) {
			return "Username already exists. Please choose another username.";
		}
		MinorClient registeredMinClient = new MinorClient(firstName, lastName, username, password, lessonCatalog, age, guardianFName, guardianLName, guardianAge);
		addClient(registeredMinClient);  // Add MinorClient to the list
		return "Registration successful: " + registeredMinClient.toString();
	}

	private boolean checkUsernameExists(String username) {
		for (Client client : clients) {
			if (client.getUserName().equals(username)) {
				return true;
			}
		}
		for (Instructor instructor : instructorCatalog.getInstructorCatalog()) {
			if (instructor.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}

	public Client login(String username, String password) {
		for (Client client : clients) {
			if (client.getUserName().equals(username)) {
				if (client.getPassword().equals(password)) {
					System.out.println("Login successful!");
					return client;
				} else {
					System.out.println("Invalid password.");
					return null;
				}
			}
		}
		System.out.println("Username not found.");
		return null;
	}

	public void addClient(Client client) {
		clients.add(client);
	}

	public boolean removeClient(String userName) {
		return clients.removeIf(client -> client.getUserName().equals(userName));
	}

	public void displayAllClients() {
		if (clients.isEmpty()) {
			System.out.println("No clients available.");
		} else {
			this.clients.forEach(client -> System.out.println(client));
		}
	}

	public Client getClientByUserName(String userName) {
		return clients.stream()
				.filter(client -> client.getUserName().equals(userName))
				.findFirst()
				.orElse(null);
	}

	public boolean deleteClient(Client client) {
		boolean safeToDelete = client.emptyBookings();
		if(client.getAge() < 18){
			for(Client guardian : this.getClients()){
				ArrayList<MinorClient> depList = new ArrayList<>(guardian.getDependents());
				for(MinorClient min : depList){
					guardian.getDependents().remove(min);
				}
			}
		}
		if (safeToDelete) {
			return this.clients.remove(client);
		}
		return false;
	}

	public ArrayList<Client> getClients() {
		return new ArrayList<>(clients);
	}

	public boolean registerDependentToClient(Client guardianClient, String dependentUserName) {
		Client depClient = this.getClientByUserName(dependentUserName);
		if (depClient instanceof MinorClient) {
			boolean success = guardianClient.getDependents().add((MinorClient) depClient);
			if (success) {
				System.out.println("Successfully added a dependent " + depClient.getUserName());
				return true;
			} else {
				System.out.println("Could not add dependent.");
			}
		}
		return false;
	}
}

