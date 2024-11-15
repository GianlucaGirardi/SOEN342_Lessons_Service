import AccountManager.MinorClient;
import lessonServices.Lesson;
import lessonServices.LessonCatalog;
import lessonServices.Booking;
import AccountManager.Client;
import AccountManager.ClientCatalog;
import AccountManager.InstructorCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class ClientBookingTest {

    private LessonCatalog lessonCatalog;
    private InstructorCatalog instructorCatalog;
    private ClientCatalog clientCatalog;
    private Client client;

    @BeforeEach
    public void setUp() {
        lessonCatalog = new LessonCatalog();
        instructorCatalog = new InstructorCatalog();
        clientCatalog = new ClientCatalog(instructorCatalog);
        instructorCatalog.setClientCatalog(clientCatalog);
        client = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        clientCatalog.addClient(client);
    }

    @Test
    public void testClientBookLessonSuccess() {

        Lesson lesson = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        Booking booking = client.bookLesson(lesson.getLESSON_ID());

        assertNotNull(booking, "The lesson should be successfully booked.");
    }

    @Test
    public void testClientViewBookings() {

        Lesson lesson1 = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));
        client.bookLesson(lesson1.getLESSON_ID());

        Lesson lesson2 = lessonCatalog.createLesson("Physics 101", 2, "New York", "Room 102", "Space B",
                LocalDate.of(2024, 11, 21), LocalDate.of(2024, 12, 21), "Tue#Thu", LocalTime.of(12, 0), LocalTime.of(14, 0));
        client.bookLesson(lesson2.getLESSON_ID());

        client.displayAllAssociatedBookings();

        assertEquals(2, client.getBookingCatalog().getBookings().size(), "The client should have 2 bookings.");
    }

    @Test
    public void testClientCannotBookFullLesson() {

        Lesson lesson = lessonCatalog.createLesson("Math 101", 1, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // First client books the lesson
        client.bookLesson(lesson.getLESSON_ID());

        // Second client tries to book the same lesson which is now full
        Client client2 = new Client("Jane", "Smith", "jane_smith", "password123", lessonCatalog, 30);
        clientCatalog.addClient(client2); // Add client2 to the catalog
        Booking booking = client2.bookLesson(lesson.getLESSON_ID());

        assertNull(booking, "The second client should not be able to book the full lesson.");
    }

    @Test
    public void testClientUnbookAndRebookFullLesson() {

        Lesson lesson = lessonCatalog.createLesson("Math 101", 1, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        Client client1 = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        Booking booking1 = client1.bookLesson(lesson.getLESSON_ID());
        assertNotNull(booking1, "Client1 should be able to book the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 1, "Lesson should be full after Client1 books it.");

        // Client2 attempts to book the same full lesson - return error message
        Client client2 = new Client("Jane", "Smith", "jane_smith", "password456", lessonCatalog, 30);
        Booking booking2 = client2.bookLesson(lesson.getLESSON_ID());
        assertNull(booking2, "Client2 should not be able to book the lesson because it is full.");

        // Client1 unbooks the lesson, space++
        boolean unbookingResult = client1.unBookLesson(booking1.getBookingId());
        assertTrue(unbookingResult, "Client1 should be able to unbook the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 0, "Lesson's capacity should increase after unbooking.");

        // Client2 books the lesson
        Booking booking3 = client2.bookLesson(lesson.getLESSON_ID());
        assertNotNull(booking3, "Client2 should now be able to book the lesson after it became available.");
        assertEquals(lesson.getCurrentCapacity(), 1, "Lesson should be full again after Client2 books it.");

        client2.displayAllAssociatedBookings();
        assertEquals(1, client2.getBookingCatalog().getBookings().size(), "Client2 should have 1 booking now.");
    }

    @Test
    public void testBookingAndUnbookingMultipleSpots() {
        // Create a lesson with 3 available spots
        Lesson lesson = lessonCatalog.createLesson("Math 101", 3, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // Create and register 4 clients
        Client client1 = new Client("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        Client client2 = new Client("Jane", "Smith", "jane_smith", "password456", lessonCatalog, 30);
        Client client3 = new Client("Alice", "Johnson", "alice_johnson", "password789", lessonCatalog, 22);
        Client client4 = new Client("Bob", "Davis", "bob_davis", "password101", lessonCatalog, 40);

        // Client1 books the lesson
        Booking booking1 = client1.bookLesson(lesson.getLESSON_ID());
        assertNotNull(booking1, "Client1 should be able to book the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 1, "Lesson should have 2 spots left after Client1 books it.");

        // Client2 books the lesson
        Booking booking2 = client2.bookLesson(lesson.getLESSON_ID());
        assertNotNull(booking2, "Client2 should be able to book the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 2, "Lesson should have 1 spot left after Client2 books it.");

        // Client3 books the lesson
        Booking booking3 = client3.bookLesson(lesson.getLESSON_ID());
        assertNotNull(booking3, "Client3 should be able to book the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 3, "Lesson should be full after Client3 books it.");

        // Client4 tries to book the full lesson but should fail
        Booking booking4 = client4.bookLesson(lesson.getLESSON_ID());
        assertNull(booking4, "Client4 should not be able to book the lesson because it is full.");

        // Client1 unbooks the lesson, freeing up a spot
        boolean unbookingResult1 = client1.unBookLesson(booking1.getBookingId());
        assertTrue(unbookingResult1, "Client1 should be able to unbook the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 2, "Lesson's capacity should increase after Client1 unbooks.");

        // Client4 should now be able to book the lesson
        Booking booking5 = client4.bookLesson(lesson.getLESSON_ID());
        assertNotNull(booking5, "Client4 should now be able to book the lesson after Client1 unbooks.");
        assertEquals(lesson.getCurrentCapacity(), 3, "Lesson should be full again after Client4 books it.");

        // Client2 unbooks the lesson
        boolean unbookingResult2 = client2.unBookLesson(booking2.getBookingId());
        assertTrue(unbookingResult2, "Client2 should be able to unbook the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 2, "Lesson's capacity should increase after Client2 unbooks.");

        // Client3 rebooks the lesson (now that a spot is available)
        Booking booking6 = client3.bookLesson(lesson.getLESSON_ID());
        assertNull(booking6, "Client3 should should not be able to rebook lesson after Client2 unbooks since they have already booked this lesson.");
        assertEquals(lesson.getCurrentCapacity(), 2, "Lesson should not be full since Client3 was not able to rebook.");

        // Verify bookings for Client4 (who booked after Client1 unbooked)
        assertEquals(1, client4.getBookingCatalog().getBookings().size(), "Client4 should have 1 booking now.");

        // Verify bookings for Client3 (who attempted a rebook after Client2 unbooked)
        assertEquals(1, client3.getBookingCatalog().getBookings().size(), "Client3 should have 1 booking now.");
    }

    @Test
    public void testMinorClientBookLesson() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(null);

        MinorClient minorClient = new MinorClient("John", "Doe", "john_doe", "password123", lessonCatalog, 16, "Jane", "Doe", 40);
        clientCatalog.addClient(minorClient);

        Lesson lesson = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // MinorClient books the lesson
        Booking booking = minorClient.bookLesson(lesson.getLESSON_ID());

        // Verify the booking was successful
        assertNotNull(booking, "MinorClient should be able to book the lesson.");
        assertEquals(1, minorClient.getBookings().size(), "MinorClient should have 1 booking.");
    }

    @Test
    public void testMinorClientBookAndUnbookLesson() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(null);

        MinorClient minorClient = new MinorClient("John", "Doe", "john_doe", "password123", lessonCatalog, 16, "Jane", "Doe", 40);
        clientCatalog.addClient(minorClient);

        Lesson lesson = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // MinorClient books the lesson
        Booking booking = minorClient.bookLesson(lesson.getLESSON_ID());

        assertNotNull(booking, "MinorClient should be able to book the lesson.");
        assertEquals(1, minorClient.getBookings().size(), "MinorClient should have 1 booking.");

        // MinorClient unbooks the lesson
        boolean unbookingResult = minorClient.unBookLesson(lesson.getLESSON_ID());

        // Verify that the unbooking was successful
        assertTrue(unbookingResult, "MinorClient should be able to unbook the lesson.");
        assertEquals(0, minorClient.getBookings().size(), "MinorClient should have 0 bookings after unbooking.");
    }

}
