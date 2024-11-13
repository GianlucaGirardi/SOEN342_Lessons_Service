import mapper.HibernateTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View all offerings");
            System.out.println("2. Register");
            System.out.println("3. Sign in");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        viewOfferings();
                        break;
                    case 2:
                        register(scanner);
                        break;
                    case 3:
                        signIn();
                        break;
                    case 4:
                        System.out.println("Exiting program...");
                        exit = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

        scanner.close();
    }

    public static void viewOfferings() {
        System.out.println("Viewing all offerings...");
        // Add code to display offerings
    }

    public static void register(Scanner scanner) {
        System.out.println("\nRegister as:");
        System.out.println("1. Admin");
        System.out.println("2. Instructor");
        System.out.println("3. Client");
        System.out.print("Choose your role (1-3): ");

        int roleChoice;
        String role = "";

        // Validate role input
        while (true) {
            try {
                roleChoice = scanner.nextInt();
                if (roleChoice < 1 || roleChoice > 3) {
                    System.out.print("Invalid choice. Please choose 1 for Admin, 2 for Instructor, or 3 for Client: ");
                    continue;
                }
                switch (roleChoice) {
                    case 1:
                        role = "Admin";
                        break;
                    case 2:
                        role = "Instructor";
                        break;
                    case 3:
                        role = "Client";
                        break;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number between 1 and 3: ");
                scanner.next(); // Clear invalid input
            }
        }

        scanner.nextLine(); // Consume the newline character

        if ("Instructor".equals(role)) {
            // Fields specific to Instructor
            System.out.print("Enter first name: ");
            String instructorFirstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String instructorLastName = scanner.nextLine();

            System.out.print("Enter username: ");
            String instructorUsername = scanner.nextLine();

            System.out.print("Enter phone number: ");
            String instructorPhoneNumber = scanner.nextLine();

            System.out.print("Enter specialization: ");
            String instructorSpecialization = scanner.nextLine();

            System.out.print("Enter availabilities: ");
            String instructorAvailabilities = scanner.nextLine();

            System.out.print("Enter password: ");
            String instructorPassword = scanner.nextLine();

            System.out.println("\nRegistration complete for Instructor.");
            System.out.println("Role: " + role);
            System.out.println("Name: " + instructorFirstName + " " + instructorLastName);
            System.out.println("Username: " + instructorUsername);
            System.out.println("Phone Number: " + instructorPhoneNumber);
            System.out.println("Specialization: " + instructorSpecialization);
            System.out.println("Availabilities: " + instructorAvailabilities);
            System.out.println("Password: [hidden for security]");

        } else if ("Client".equals(role)) {
            // Fields specific to Client
            System.out.print("Enter first name: ");
            String clientFirstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String clientLastName = scanner.nextLine();

            System.out.print("Enter username: ");
            String clientUsername = scanner.nextLine();

            System.out.print("Enter age: ");
            int clientAge = Integer.parseInt(scanner.nextLine());

            if (clientAge<18) {
                System.out.print("Enter legal guardian username: ");
                String legalGuardianUsername = scanner.nextLine();
                System.out.print("Enter password: ");
                String clientPassword = scanner.nextLine();

                System.out.println("\nRegistration complete for Client.");
                System.out.println("Role: " + role);
                System.out.println("Name: " + clientFirstName + " " + clientLastName);
                System.out.println("Username: " + clientUsername);
                System.out.println("Password: [hidden for security]");
            }
            else {

                System.out.print("Enter password: ");
                String clientPassword = scanner.nextLine();

                System.out.println("\nRegistration complete for Client.");
                System.out.println("Role: " + role);
                System.out.println("Name: " + clientFirstName + " " + clientLastName);
                System.out.println("Username: " + clientUsername);
                System.out.println("Password: [hidden for security]");
            }

        } else {
            // Fields specific to Admin
            System.out.print("Enter first name: ");
            String adminFirstName = scanner.nextLine();

            System.out.print("Enter last name: ");
            String adminLastName = scanner.nextLine();

            System.out.print("Enter username: ");
            String adminUsername = scanner.nextLine();

            System.out.print("Enter password: ");
            String adminPassword = scanner.nextLine();

            System.out.println("\nRegistration complete for Admin.");
            System.out.println("Role: " + role);
            System.out.println("Name: " + adminFirstName + " " + adminLastName);
            System.out.println("Username: " + adminUsername);
            System.out.println("Password: [hidden for security]");
        }

        // Message before returning to the main menu
        System.out.println("\nReturning to main menu...");
    }

    public static void signIn() {
        System.out.println("Signing in...");
        // Add sign-in logic here
    }
}