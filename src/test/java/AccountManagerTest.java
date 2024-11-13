import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import lessonServices.LessonCatalog;
import AccountManager.*;

import java.util.ArrayList;

public class AccountManagerTest {

    private ClientCatalog clientCatalog;
    private InstructorCatalog instructorCatalog;
    private LessonCatalog lessonCatalog;

    @BeforeEach
    public void setUp() {
        lessonCatalog = new LessonCatalog();
        instructorCatalog = new InstructorCatalog();
        clientCatalog = new ClientCatalog(instructorCatalog);
        instructorCatalog.setClientCatalog(clientCatalog);
    }

    // Test for successful Client registration
    @Test
    public void testClientRegisterSuccess() {
        String result = clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        Client registeredClient = clientCatalog.getClientByUserName("john_doe");

        assertNotNull(registeredClient);
        assertEquals("John", registeredClient.getFirstName());
        assertEquals("Doe", registeredClient.getLastName());
        assertEquals("john_doe", registeredClient.getUserName());
    }

    // Test for successful Instructor registration
    @Test
    public void testInstructorRegisterSuccess() {
        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("Monday");
        String result = instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities);
        Instructor registeredInstructor = instructorCatalog.getInstructorCatalog().get(0);

        assertNotNull(registeredInstructor);
        assertEquals("Jane", registeredInstructor.getFirstName());
        assertEquals("Doe", registeredInstructor.getLastName());
        assertEquals("jane_doe", registeredInstructor.getUserName());
        assertEquals("Math", registeredInstructor.getSpecialization());
    }

    // Test for Client registration with existing username
    @Test
    public void testClientRegisterUsernameTaken() {
        clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        String result = clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        assertEquals("Username already exists. Please choose another username.", result);
    }

    // Test for Instructor registration with existing username
    @Test
    public void testInstructorRegisterUsernameTaken() {
        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("Monday");
        instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities);
        String result = instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-5678", "Physics", availabilities);
        assertEquals("Username already exists. Please choose another username.", result);
    }

    // Test for successful Client login
    @Test
    public void testClientLoginSuccess() {
        clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        boolean result = clientCatalog.login("john_doe", "password123");
        assertTrue(result);
    }

    // Test for successful Instructor login
    @Test
    public void testInstructorLoginSuccess() {
        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("Monday");
        instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities);
        boolean result = instructorCatalog.login("jane_doe", "password123");
        assertTrue(result);
    }

    // Test for Client login failure with incorrect password
    @Test
    public void testClientLoginFailureIncorrectPassword() {
        clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        boolean result = clientCatalog.login("john_doe", "wrongpassword");
        assertFalse(result);
    }

    // Test for Instructor login failure with incorrect password
    @Test
    public void testInstructorLoginFailureIncorrectPassword() {
        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("Monday");
        instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities);
        boolean result = instructorCatalog.login("jane_doe", "wrongpassword");
        assertFalse(result);
    }

    // Test for login failure with non-existing username
    @Test
    public void testLoginFailureNonExistingUsername() {
        boolean result = clientCatalog.login("non_existing_user", "password123");
        assertFalse(result);
    }

    // Test for Instructor login failure with non-existing username
    @Test
    public void testInstructorLoginFailureNonExistingUsername() {
        boolean result = instructorCatalog.login("non_existing_instructor", "password123");
        assertFalse(result);
    }

    // Test for displaying all clients
    @Test
    public void testDisplayAllClients() {
        clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        clientCatalog.displayAllClients();
    }

    // Test for displaying all instructors
    @Test
    public void testDisplayAllInstructors() {
        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("Monday");
        instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities);
        instructorCatalog.displayAllInstructors();
    }
}
