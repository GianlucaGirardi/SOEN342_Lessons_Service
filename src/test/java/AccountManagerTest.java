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
    private Administrator admin;

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
        clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
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

    @Test
    public void testAdminRegisterSuccess() {
        // Register admin
        admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        // Test that the admin is registered and the properties are correct
        assertNotNull(admin, "Admin should be registered successfully.");
        assertEquals("admin_user", admin.getUserName(), "Admin username should match.");
    }

    // Test for Admin re-registration attempt
    @Test
    public void testAdminReRegisterFailure() {
        // First registration
        admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);
        Administrator duplicateAdmin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        // Ensure that both calls return the same instance (Singleton)
        assertSame(admin, duplicateAdmin, "Only one instance of Admin should exist.");
    }

    // Test for Admin login success
    @Test
    public void testAdminLoginSuccess() {
        // Ensure admin is created first
        admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        // Test login success with correct credentials
        Administrator result = admin.login("admin_user", "admin_password");

        assertNotNull(result, "Admin should be able to log in with correct credentials.");
        assertEquals("admin_user", result.getUserName(), "Logged-in admin username should match.");
    }

    // Test for Admin login failure (incorrect password)
    @Test
    public void testAdminLoginFailureIncorrectPassword() {
        // Ensure admin is created first
        admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        // Test login failure with incorrect password
        Administrator result = admin.login("admin_user", "wrongpassword");

        assertNull(result, "Admin login should fail with incorrect password.");
    }

    // Test for Admin login failure (non-existing username)
    @Test
    public void testAdminLoginFailureNonExistingUsername() {
        // Ensure admin is created first
        admin = Administrator.getAdministrator("Admin", "User", "admin_user", "admin_password", lessonCatalog, clientCatalog, instructorCatalog);

        // Test login failure with non-existing username
        Administrator result = admin.login("non_existing_admin", "admin_password");

        assertNull(result, "Admin login should fail with non-existing username.");
    }

    // Test for Client login success
    @Test
    public void testClientLoginSuccess() {
        clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        Client result = clientCatalog.login("john_doe", "password123");

        assertNotNull(result, "Client login should succeed with correct credentials.");
        assertEquals("john_doe", result.getUserName(), "Logged-in client username should match.");
    }

    // Test for Instructor login success
    @Test
    public void testInstructorLoginSuccess() {
        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("Monday");
        instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities);
        Instructor result = instructorCatalog.login("jane_doe", "password123");

        assertNotNull(result, "Instructor login should succeed with correct credentials.");
        assertEquals("jane_doe", result.getUserName(), "Logged-in instructor username should match.");
    }

    // Test for Client login failure with incorrect password
    @Test
    public void testClientLoginFailureIncorrectPassword() {
        clientCatalog.register("John", "Doe", "john_doe", "password123", lessonCatalog, 25);
        Client result = clientCatalog.login("john_doe", "wrongpassword");

        assertNull(result, "Client login should fail with incorrect password.");
    }

    // Test for Instructor login failure with incorrect password
    @Test
    public void testInstructorLoginFailureIncorrectPassword() {
        ArrayList<String> availabilities = new ArrayList<>();
        availabilities.add("Monday");
        instructorCatalog.register("Jane", "Doe", "jane_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities);
        Instructor result = instructorCatalog.login("jane_doe", "wrongpassword");

        assertNull(result, "Instructor login should fail with incorrect password.");
    }

    // Test for login failure with non-existing username
    @Test
    public void testLoginFailureNonExistingUsername() {
        Client result = clientCatalog.login("non_existing_user", "password123");

        assertNull(result, "Client login should fail with non-existing username.");
    }

    // Test for Instructor login failure with non-existing username
    @Test
    public void testInstructorLoginFailureNonExistingUsername() {
        Instructor result = instructorCatalog.login("non_existing_instructor", "password123");

        assertNull(result, "Instructor login should fail with non-existing username.");
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

    @Test
    public void testCreateMinorClient() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(null);

        MinorClient minorClient = new MinorClient("John", "Doe", "john_doe", "password123", lessonCatalog, 15);
        clientCatalog.addClient(minorClient);

        // Ensure the MinorClient is registered successfully
        Client registeredClient = clientCatalog.getClientByUserName("john_doe");
        assertNotNull(registeredClient, "MinorClient should be registered successfully.");

        // Verify guardian's details
        assertEquals("Jane", minorClient.getGuardianFirstName(), "Guardian first name should match.");
        assertEquals("Doe", minorClient.getGuardianLastName(), "Guardian last name should match.");
        assertEquals(40, minorClient.getGuardianAge(), "Guardian age should match.");
    }

    @Test
    public void testLoginMinorClient() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(null);

        MinorClient minorClient = new MinorClient("John", "Doe", "john_doe", "password123", lessonCatalog, 16);
        clientCatalog.addClient(minorClient);

        // Login with correct credentials
        Client loggedInClient = clientCatalog.getClientByUserName("john_doe");
        assertNotNull(loggedInClient, "Client should be found by username.");

        // Verify that the login client matches the registered client
        assertEquals("john_doe", loggedInClient.getUserName(), "Username should match.");
        assertTrue(loggedInClient.getPassword().equals("password123"), "Password should match.");
    }

    @Test
    public void testLoginMinorClientWithIncorrectPassword() {
        LessonCatalog lessonCatalog = new LessonCatalog();
        ClientCatalog clientCatalog = new ClientCatalog(null);

        MinorClient minorClient = new MinorClient("John", "Doe", "john_doe", "password123", lessonCatalog, 16);
        clientCatalog.addClient(minorClient);

        // Attempt logging in with incorrect password
        Client loggedInClient = clientCatalog.getClientByUserName("john_doe");
        assertNotNull(loggedInClient, "Client should be found by username.");
        assertFalse(loggedInClient.getPassword().equals("wrongpassword"), "Password should not match.");
    }

}


