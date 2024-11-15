package AccountManager;

import lessonServices.Lesson;
import lessonServices.LessonCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


// Run the tests individually for this class before each = not set up properly
public class AdminDeleteAccountTest {

    @BeforeEach
    public void resetAdminSingleton() {
        // Reset the Administrator singleton instance before each test to ensure independence
        Administrator.resetInstance();
        //lesson id
        Lesson.setLessonsCounter(1);

    }

    @Test
    public void testDeleteClientAccountSuccess() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        Client client = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        clientCatalog.addClient(client);

        // Ensure the client exists
        Client registeredClient = clientCatalog.getClientByUserName("john_doe");
        assertNotNull(registeredClient, "Client should be registered successfully.");

        // Delete the client account
        boolean result = admin.deleteAccount("john_doe");

        // Verify that the client has been removed from the catalog
        assertTrue(result, "Client account should be deleted successfully.");
        assertNull(clientCatalog.getClientByUserName("john_doe"), "Client should not exist after deletion.");
    }

    @Test
    public void testDeleteClientAccountWithMultipleBookings() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        Client client = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        clientCatalog.addClient(client);

        Lesson lesson1 = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));
        Lesson lesson2 = lessonCatalog.createLesson("Science 101", 2, "New York", "Room 102", "Space B",
                LocalDate.of(2024, 11, 21), LocalDate.of(2024, 12, 21), "Tue#Thu", LocalTime.of(14, 0), LocalTime.of(16, 0));

        assertNotNull(lesson1, "Math lesson should be created successfully.");
        assertNotNull(lesson2, "Science lesson should be created successfully.");

        client.bookLesson(lesson1.getLESSON_ID());
        client.bookLesson(lesson2.getLESSON_ID());

        // Ensure client has booked both lessons
        assertEquals(2, client.getBookingCatalog().getBookings().size(), "Client should have 2 bookings.");

        // Delete the client account
        boolean result = admin.deleteAccount("john_doe");

        // Verify that the client account is deleted
        assertTrue(result, "Client account should be deleted successfully.");
        assertNull(clientCatalog.getClientByUserName("john_doe"), "Client should not exist after deletion.");

        // Verify that the client's bookings are also removed
        assertTrue(client.getBookingCatalog().getBookings().isEmpty(), "Client's bookings should be removed after account deletion.");
    }

    @Test
    public void testDeleteInstructorAccountSuccess() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("New York");
        Instructor instructor = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-1234", "Math", availabilities);
        instructorCatalog.getInstructors().add(instructor);

        lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // Ensure the instructor exists
        Instructor registeredInstructor = instructorCatalog.getInstructors().get(0);
        assertNotNull(registeredInstructor, "Instructor should be registered successfully.");

        // Instructor takes up a lesson
        Lesson lesson = lessonCatalog.searchLessonById(1);
        boolean lessonTaken = instructor.takeUpLesson(lesson.getLESSON_ID());
        assertTrue(lessonTaken, "Instructor should successfully take up the lesson.");

        // Delete the instructor account
        boolean result = admin.deleteAccount("jane_smith");

        // Verify the instructor has been removed
        assertTrue(result, "Instructor account should be deleted successfully.");
        assertTrue(instructorCatalog.getInstructors().isEmpty(), "Instructor should not exist after deletion.");


        // Verify that the lesson instructor is set to null
        assertNull(lesson.getInstructor(), "Lesson should have no instructor after deletion.");
    }

    @Test
    public void testDeleteNonExistentAccount() {
        // Initialize catalogs
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        // Create and register the admin
        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        // Try deleting a non-existent account
        assertThrows(IllegalArgumentException.class, () -> {
            admin.deleteAccount("non_existent_user");
        }, "Should throw exception when attempting to delete a non-existent account.");
    }

    @Test
    public void testDeleteInstructorTakenUpLessonWithAClient() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        Client client = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        clientCatalog.addClient(client);

        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("New York");
        Instructor instructor = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-1234", "Math", availabilities);
        instructorCatalog.getInstructors().add(instructor);

        lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        Lesson lesson = lessonCatalog.searchLessonById(1);
        client.bookLesson(lesson.getLESSON_ID());

        instructor.takeUpLesson(lesson.getLESSON_ID());

        // Try deleting the instructor account while lessons are booked
        boolean result = admin.deleteAccount("jane_smith");

        // Verify that the instructor is deleted
        assertTrue(result, "Instructor account should be deleted after lessons are unbooked.");

        // Verify that the booking has been removed (unbooked)
        assertTrue(client.getBookingCatalog().getBookings().isEmpty(), "Client should have successfully unbooked the lesson.");
    }

    @Test
    public void testDeleteInstructorWithNoBookedLessons() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("New York");
        Instructor instructor = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-1234", "Math", availabilities);
        instructorCatalog.getInstructors().add(instructor);

        lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        Lesson lesson = lessonCatalog.searchLessonById(1);
        instructor.takeUpLesson(lesson.getLESSON_ID());

        // Instructor removes the lesson
        boolean removed = instructor.removeTakenUpLesson(lesson.getLESSON_ID());
        assertTrue(removed, "Instructor should successfully remove the taken lesson.");

        // Attempt deleting the instructor account with no booked lessons
        boolean result = admin.deleteAccount("jane_smith");

        // Verify that the instructor is deleted
        assertTrue(result, "Instructor account should be deleted successfully.");
    }

    @Test
    public void testDeleteAccountWithErrorUnbookingLesson() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("New York");
        Instructor instructor = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-1234", "Math", availabilities);
        instructorCatalog.getInstructors().add(instructor);

        // Create and register a client
        Client client = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        clientCatalog.addClient(client);

        lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // failure in un-booking for the client
        Lesson lesson = lessonCatalog.searchLessonById(1);
        instructor.takeUpLesson(lesson.getLESSON_ID());

        // Simulate failure in un-booking
        client.unBookLesson(lesson.getLESSON_ID());  // This can be modified to throw an exception in your actual unBookLesson implementation

        // Verify the exception is thrown while attempting to delete
        assertThrows(IllegalStateException.class, () -> {
            admin.deleteAccount("jane_smith");
        }, "Exception should be thrown when error occurs while un-booking lessons for clients.");
    }
    @Test
    public void testRemoveBookingFromClient() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(instructorCatalog);

        Administrator admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        Client client = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        clientCatalog.addClient(client);

        Lesson lesson1 = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));
        Lesson lesson2 = lessonCatalog.createLesson("Science 101", 2, "New York", "Room 102", "Space B",
                LocalDate.of(2024, 11, 21), LocalDate.of(2024, 12, 21), "Tue#Thu", LocalTime.of(14, 0), LocalTime.of(16, 0));

        // Ensure lessons were created successfully
        assertNotNull(lesson1, "Math lesson should be created successfully.");
        assertNotNull(lesson2, "Science lesson should be created successfully.");

        client.bookLesson(lesson1.getLESSON_ID());
        client.bookLesson(lesson2.getLESSON_ID());

        assertEquals(2, client.getBookingCatalog().getBookings().size(), "Client should have 2 bookings.");

        // Get the booking ID for lesson1
        long bookingIdToRemove = client.getBookingCatalog().getBookings().get(0).getBookingId();

        boolean result = admin.removeBookingFromClient(bookingIdToRemove);

        // Verify that the booking was removed
        assertTrue(result, "Booking should be removed successfully.");

        assertEquals(1, client.getBookingCatalog().getBookings().size(), "Client should have 1 booking after removal.");

        assertEquals(lesson1.getCurrentCapacity(), 0, "Lesson's capacity should increase after the booking is removed.");
    }

}


