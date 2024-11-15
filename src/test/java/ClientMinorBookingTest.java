import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import lessonServices.Lesson;
import lessonServices.LessonCatalog;
import lessonServices.Booking;
import AccountManager.Client;
import AccountManager.MinorClient;
import AccountManager.ClientCatalog;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientMinorBookingTest {

    private ClientCatalog clientCatalog;
    private LessonCatalog lessonCatalog;
    private Client client;
    private MinorClient minorClient;
    private Lesson lesson;

    @BeforeEach
    public void setUp() {
        lessonCatalog = new LessonCatalog();
        clientCatalog = new ClientCatalog(null);

        // Create a client
        client = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 30);
        clientCatalog.addClient(client);

        // Create a minor client
        minorClient = new MinorClient("Jane", "Doe", "jane_doe", "password123", lessonCatalog, 16, "Emily", "Doe", 40);
        clientCatalog.addClient(minorClient);

        // Create a lesson
        lesson = lessonCatalog.createLesson("Math 101", 3, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));
    }

    @Test
    public void testRegisterDependentToClient() {
        // Client registers minor client as a dependent
        boolean isRegistered = clientCatalog.registerDependentToClient(client, "jane_doe");

        // Assert that the dependent was successfully registered
        assertTrue(isRegistered, "The minor client should be successfully registered as a dependent.");
        assertTrue(client.getDependents().contains(minorClient), "Client should have the minor client in their dependents list.");
    }

    @Test
    public void testClientBookLessonForDependent() {
        // Register minor client as a dependent
        clientCatalog.registerDependentToClient(client, "jane_doe");

        // Client books a lesson for the minor client
        Booking booking = client.bookLessonDependent("jane_doe", lesson.getLESSON_ID());

        // Assert that the booking was successful
        assertNotNull(booking, "The client should be able to book the lesson for their dependent.");
        assertEquals(1, minorClient.getBookings().size(), "The minor client should have 1 booking.");
    }

    @Test
    public void testClientUnbookLessonForDependent() {
        // Register minor client as a dependent and book a lesson for them
        clientCatalog.registerDependentToClient(client, "jane_doe");
        client.bookLessonDependent("jane_doe", lesson.getLESSON_ID());

        // Unbook the lesson for the minor client
        boolean unbookingResult = client.unBookLessonDependent("jane_doe", lesson.getLESSON_ID());

        // Assert that the unbooking was successful
        assertTrue(unbookingResult, "The client should be able to unbook the lesson for their dependent.");
        assertEquals(0, minorClient.getBookings().size(), "The minor client should have 0 bookings after unbooking.");
    }

    @Test
    public void testClientCannotBookLessonForNonDependent() {
        // Try to book a lesson for a non-dependent minor client
        Client anotherClient = new Client("Alice", "Smith", "alice_smith", "password123", lessonCatalog, 25);
        clientCatalog.addClient(anotherClient);

        Booking booking = client.bookLessonDependent("alice_smith", lesson.getLESSON_ID());

        // Assert that the lesson cannot be booked for a non-dependent minor client
        assertNull(booking, "The client should not be able to book a lesson for a non-dependent minor client.");
    }
}
