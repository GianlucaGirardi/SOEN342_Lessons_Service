import AccountManager.ClientCatalog;
import AccountManager.InstructorCatalog;
import AccountManager.Instructor;
import AccountManager.Administrator;
import AccountManager.Client;
import lessonServices.LessonCatalog;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import mapper.HibernateTest;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        LessonCatalog newLessonCatalog = new LessonCatalog();
        InstructorCatalog newInstructorCatalog = new InstructorCatalog();
        ClientCatalog newClientCatalog = new ClientCatalog(newInstructorCatalog);
        newInstructorCatalog.setClientCatalog(newClientCatalog);
        Administrator admin = Administrator.getAdministrator("Luca","Rahman", "lr", "123", newLessonCatalog, newClientCatalog, newInstructorCatalog);






        while (!exit) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View all offerings");
            System.out.println("2. Register");
            System.out.println("3. Sign in");
            System.out.println("4. Exit");

            System.out.print("Enter your choice: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        viewOfferings(newLessonCatalog);
                        break;
                    case 2:
                        register(scanner, newLessonCatalog, newInstructorCatalog, newClientCatalog, admin);
                        break;
                    case 3:
                        signIn(scanner, newLessonCatalog, newInstructorCatalog, newClientCatalog, admin);
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

    public static void viewOfferings(LessonCatalog newLessonCatalog) {
        newLessonCatalog.displayLessons();
    }



    public static void register(Scanner scanner, LessonCatalog newLessonCatalog, InstructorCatalog newInstructorCatalog, ClientCatalog newClientCatalog, Administrator admin) {



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

            ArrayList<String> newAvailabilities = new ArrayList<>();

            System.out.println("Enter availabilities (type 'done' to finish):");

            while (true) {
                System.out.print("Enter availability: ");
                String availability = scanner.nextLine();

                if ("done".equalsIgnoreCase(availability)) {
                    break;
                }

                newAvailabilities.add(availability);
            }

            System.out.println("Availabilities entered: " + newAvailabilities);

            System.out.print("Enter password: ");
            String instructorPassword = scanner.nextLine();

            System.out.println("\nRegistration complete for Instructor.");
            System.out.println("Role: " + role);
            System.out.println("Name: " + instructorFirstName + " " + instructorLastName);
            System.out.println("Username: " + instructorUsername);
            System.out.println("Phone Number: " + instructorPhoneNumber);
            System.out.println("Specialization: " + instructorSpecialization);
            System.out.println("Availabilities: " + newAvailabilities);
            System.out.println("Password: [hidden for security]");

            System.out.println(newInstructorCatalog.register(instructorFirstName, instructorLastName, instructorUsername, instructorPassword, newLessonCatalog, instructorPhoneNumber, instructorSpecialization, newAvailabilities));

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
                String password = scanner.nextLine();
                String clientPassword = scanner.nextLine();

                System.out.println("\nRegistration complete for Client.");
                System.out.println("Role: " + role);
                System.out.println("Name: " + clientFirstName + " " + clientLastName);
                System.out.println("Username: " + clientUsername);
                System.out.println("Password: [hidden for security]");

                System.out.println(newClientCatalog.register(clientFirstName, clientLastName, clientUsername, clientPassword, newLessonCatalog, clientAge));

            }
            else {

                System.out.print("Enter password: ");
                String clientPassword = scanner.nextLine();

                System.out.println("\nRegistration complete for Client.");
                System.out.println("Role: " + role);
                System.out.println("Name: " + clientFirstName + " " + clientLastName);
                System.out.println("Username: " + clientUsername);
                System.out.println("Password: [hidden for security]");

                System.out.println(newClientCatalog.register(clientFirstName, clientLastName, clientUsername, clientPassword, newLessonCatalog, clientAge));
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

            admin = Administrator.getAdministrator(adminFirstName,adminLastName, adminUsername, adminPassword, newLessonCatalog, newClientCatalog, newInstructorCatalog);


        }


        System.out.println("\nReturning to main menu...");
    }

    public static void signIn(Scanner scanner, LessonCatalog newLessonCatalog, InstructorCatalog newInstructorCatalog, ClientCatalog newClientCatalog, Administrator admin) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if((newInstructorCatalog.login(username, password))!=null) {
            System.out.println("Login successful!.");
            instructorMain(scanner, newLessonCatalog, newInstructorCatalog, newInstructorCatalog.login(username, password));

        }
        else if(newClientCatalog.login(username, password)!=null) {
            System.out.println("Login successful!.");
            clientMain(scanner, newLessonCatalog, newClientCatalog, newClientCatalog.login(username, password));

        }

        else if(admin.login(username, password)!=null){
            System.out.println("Administrator logged in successfully.");
            administratorMain(scanner, newLessonCatalog, newInstructorCatalog, newClientCatalog, admin.login(username, password));
        }
        else{
            System.out.println("Invalid username or password.");
        }



    }
    Instructor newInstructor;
    public static void instructorMain(Scanner scanner, LessonCatalog newLessonCatalog, InstructorCatalog newInstructorCatalog, Instructor newInstructor) {

        boolean leave = false;
        while (!leave) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View all offerings");
            System.out.println("2. take on offering");
            System.out.println("3. cancel offering");
            System.out.println("4. sign out");

            System.out.print("Enter your choice: ");


            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        newLessonCatalog.displayAllLessons();
                        break;
                    case 2:
                        System.out.println("Enter the lesson ID");
                        long lessonId = scanner.nextLong();
                        newInstructor.takeUpLesson(lessonId);
                        break;
                    case 3:
                        System.out.println("Enter the lesson ID");
                        long lessonId2 = scanner.nextLong();
                        newInstructor.removeTakenUpLesson(lessonId2);
                        break;
                    case 4:
                        System.out.println("signing out...");
                        leave = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }

    }

    Client newClient;
    public static void clientMain(Scanner scanner, LessonCatalog newLessonCatalog, ClientCatalog newClientCatalog, Client newClient) {

        boolean leave = false;
        while (!leave) {
            System.out.println("\nChoose an option:");
            System.out.println("1. View all offerings taken up by instructors");
            System.out.println("2. View my bookings");
            System.out.println("3. make booking");
            System.out.println("4. cancel booking");
            System.out.println("5. sign out");

            System.out.print("Enter your choice: ");


            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        newLessonCatalog.displayLessons();
                        break;
                    case 2:
                        newClient.displayAllAssociatedBookings();
                        break;
                    case 3:
                        System.out.println("Enter the lesson ID:");
                        long lessonId = scanner.nextLong();
                        newClient.bookLesson(lessonId);
                        break;
                    case 4:
                        System.out.println("Enter the lesson ID:");
                        long lessonId2 = scanner.nextLong();
                        newClient.unBookLesson(lessonId2);
                        break;
                    case 5:
                        System.out.println("signing out...");
                        leave = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }


    }

    public static void administratorMain(Scanner scanner, LessonCatalog newLessonCatalog, InstructorCatalog newInstructorCatalog, ClientCatalog newClientCatalog, Administrator admin) {

        boolean leave = false;
        while (!leave) {
            System.out.println("\nChoose an option:");
            System.out.println("1. create lesson");
            System.out.println("2. view instructor catalog");
            System.out.println("3. view client catalog");
            System.out.println("4. view lessons available to clients"); //view all lesson available to public
            System.out.println("5. view lesson that instructor can take on");//view all lesson that instrucor can take on
            System.out.println("6. delete account");
            System.out.println("7. cancel client booking");
            System.out.println("8. sign out");

            System.out.print("Enter your choice: ");


            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

                        System.out.print("Enter lesson name: ");
                        String lessonName = scanner.nextLine();

                        System.out.print("Enter lesson capacity: ");
                        int initialCapacity = scanner.nextInt();
                        scanner.nextLine(); // Consume the leftover newline

                        System.out.print("Enter city name: ");
                        String city = scanner.nextLine();

                        System.out.print("Enter location name: ");
                        String locationName = scanner.nextLine();

                        System.out.print("Enter space name: ");
                        String space = scanner.nextLine();

                        // Parse startDate
                        LocalDate startDate = null;
                        while (startDate == null) {
                            System.out.print("Enter start date (yyyy-MM-dd): ");
                            String startDateInput = scanner.nextLine();
                            try {
                                startDate = LocalDate.parse(startDateInput, dateFormatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                            }
                        }

                        // Parse endDate
                        LocalDate endDate = null;
                        while (endDate == null) {
                            System.out.print("Enter end date (yyyy-MM-dd): ");
                            String endDateInput = scanner.nextLine();
                            try {
                                endDate = LocalDate.parse(endDateInput, dateFormatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
                            }
                        }

                        System.out.print("Enter days of the week (day1#day2#day3...): ");
                        String daysOfWeek = scanner.nextLine();

                        // Parse startHour
                        LocalTime startHour = null;
                        while (startHour == null) {
                            System.out.print("Enter starting hour (HH:mm): ");
                            String startHourInput = scanner.nextLine();
                            try {
                                startHour = LocalTime.parse(startHourInput, timeFormatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid time format. Please use HH:mm.");
                            }
                        }

                        // Parse endHour
                        LocalTime endHour = null;
                        while (endHour == null) {
                            System.out.print("Enter ending hour (HH:mm): ");
                            String endHourInput = scanner.nextLine();
                            try {
                                endHour = LocalTime.parse(endHourInput, timeFormatter);
                            } catch (DateTimeParseException e) {
                                System.out.println("Invalid time format. Please use HH:mm.");
                            }
                        }

                        // Pass parsed LocalDate and LocalTime objects to the createLesson method
                        newLessonCatalog.createLesson(
                                lessonName, initialCapacity, city, locationName, space,
                                startDate, endDate, daysOfWeek, startHour, endHour
                        );
                        break;
                    case 2:
                        newInstructorCatalog.displayAllInstructors();
                        break;
                    case 3:
                        newClientCatalog.displayAllClients();
                        break;
                    case 4:
                        newLessonCatalog.displayLessons();
                        break;
                    case 5:
                        newLessonCatalog.displayAllLessons();
                        break;
                    case 6:
                        System.out.print("Enter username of account you wish to delete: ");
                        String name = scanner.nextLine();
                        admin.deleteAccount(name); //need to test
                        break;
                    case 7:
                        System.out.print("Enter  ID of booking you wish to remove: ");
                        long id = scanner.nextLong();
                        admin.removeBookingFromClient(id); //need to test
                        break;
                    case 8:
                        System.out.println("signing out...");
                        leave = true;
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }


    }
}