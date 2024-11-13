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
        assertEquals(lesson.getCurrentCapacity(), 0, "Lesson should be full after Client1 books it.");

        // Client2 attempts to book the same full lesson - return error message
        Client client2 = new Client("Jane", "Smith", "jane_smith", "password456", lessonCatalog, 30);
        Booking booking2 = client2.bookLesson(lesson.getLESSON_ID());
        assertNull(booking2, "Client2 should not be able to book the lesson because it is full.");

        // Client1 unbooks the lesson, space++
        boolean unbookingResult = client1.getBookingCatalog().unBookLesson(booking1.getBookingId());
        assertTrue(unbookingResult, "Client1 should be able to unbook the lesson.");
        assertEquals(lesson.getCurrentCapacity(), 1, "Lesson's capacity should increase after unbooking.");

        // Client2 books the lesson
        Booking booking3 = client2.bookLesson(lesson.getLESSON_ID());
        assertNotNull(booking3, "Client2 should now be able to book the lesson after it became available.");
        assertEquals(lesson.getCurrentCapacity(), 0, "Lesson should be full again after Client2 books it.");

        client2.displayAllAssociatedBookings();
        assertEquals(1, client2.getBookingCatalog().getBookings().size(), "Client2 should have 1 booking now.");
    }
}
