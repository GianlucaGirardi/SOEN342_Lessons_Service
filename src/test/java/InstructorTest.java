package lessonServices;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import AccountManager.Instructor;
import AccountManager.InstructorCatalog;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InstructorTest {

    @Test
    public void testInstructorTakeUpLessonSuccess() {
        // Reset lesson counter to ensure lesson ID starts at 1 for each test
        Lesson.setLessonsCounter(1);

        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();

        ArrayList<String> availabilities1 = new ArrayList<>();
        availabilities1.add("New York");
        Instructor instructor1 = new Instructor("John", "Doe", "john_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities1);

        ArrayList<String> availabilities2 = new ArrayList<>();
        availabilities2.add("Los Angeles");
        Instructor instructor2 = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-5678", "Physics", availabilities2);

        instructorCatalog.getInstructors().add(instructor1);
        instructorCatalog.getInstructors().add(instructor2);

        // Create a lesson in New York (for instructor1)
        Lesson lesson = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        assertNotNull(lesson, "Lesson should be created and found by ID.");
        assertEquals(1, lesson.getLESSON_ID(), "The lesson ID should be 1.");

        // Instructor1 successfully takes up the lesson
        boolean result = instructor1.takeUpLesson(lesson.getLESSON_ID());
        assertTrue(result, "Instructor1 should be able to take up the lesson.");
        assertEquals(1, instructor1.getTakenUpLessons().size(), "Instructor1 should have 1 lesson assigned.");
    }

    @Test
    public void testInstructorTakeUpLessonFailureLocationMismatch() {
        // Reset lesson counter to ensure lesson ID starts at 1 for each test
        Lesson.setLessonsCounter(1);

        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();

        ArrayList<String> availabilities1 = new ArrayList<>();
        availabilities1.add("New York");
        Instructor instructor1 = new Instructor("John", "Doe", "john_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities1);

        ArrayList<String> availabilities2 = new ArrayList<>();
        availabilities2.add("Los Angeles");
        Instructor instructor2 = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-5678", "Physics", availabilities2);

        instructorCatalog.getInstructors().add(instructor1);
        instructorCatalog.getInstructors().add(instructor2);

        Lesson lesson = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        assertNotNull(lesson, "Lesson should be created and found by ID.");

        // Instructor2 attempts to take up a lesson that is in a different location
        boolean result = instructor2.takeUpLesson(lesson.getLESSON_ID());
        assertFalse(result, "Instructor2 should not be able to take up the lesson due to location mismatch.");
        assertEquals(0, instructor2.getTakenUpLessons().size(), "Instructor2 should not have any lessons assigned.");
    }

    @Test
    public void testInstructorCannotTakeUpLessonAlreadyTaken() {

        Lesson.setLessonsCounter(1);

        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();

        ArrayList<String> availabilities1 = new ArrayList<>();
        availabilities1.add("New York");
        Instructor instructor1 = new Instructor("John", "Doe", "john_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities1);

        ArrayList<String> availabilities2 = new ArrayList<>();
        availabilities2.add("Los Angeles");
        Instructor instructor2 = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-5678", "Physics", availabilities2);

        instructorCatalog.getInstructors().add(instructor1);
        instructorCatalog.getInstructors().add(instructor2);

        Lesson lesson = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        assertNotNull(lesson, "Lesson should be created and found by ID.");

        // Instructor1 takes up the lesson
        instructor1.takeUpLesson(lesson.getLESSON_ID());

        // Instructor2 attempts to take the same lesson
        boolean result = instructor2.takeUpLesson(lesson.getLESSON_ID());
        assertFalse(result, "Instructor2 should not be able to take up the lesson because it is already taken by Instructor1.");
    }

    @Test
    public void testInstructorTakeUpLessonSuccessAndFailureFlow() {
        // Reset lesson counter to ensure lesson ID starts at 1 for each test
        Lesson.setLessonsCounter(1);

        LessonCatalog lessonCatalog = new LessonCatalog();
        InstructorCatalog instructorCatalog = new InstructorCatalog();

        ArrayList<String> availabilities1 = new ArrayList<>();
        availabilities1.add("New York");
        Instructor instructor1 = new Instructor("John", "Doe", "john_doe", "password123", lessonCatalog, "555-1234", "Math", availabilities1);

        ArrayList<String> availabilities2 = new ArrayList<>();
        availabilities2.add("Los Angeles");
        availabilities2.add("New York");
        Instructor instructor2 = new Instructor("Jane", "Smith", "jane_smith", "password456", lessonCatalog, "555-5678", "Physics", availabilities2);

        instructorCatalog.getInstructors().add(instructor1);
        instructorCatalog.getInstructors().add(instructor2);

        Lesson lesson = lessonCatalog.createLesson("Math 101", 2, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        assertNotNull(lesson, "Lesson should be created and found by ID.");

        boolean result = instructor1.takeUpLesson(lesson.getLESSON_ID());
        assertTrue(result, "Instructor1 should be able to take up the lesson.");
        assertEquals(1, instructor1.getTakenUpLessons().size(), "Instructor1 should have 1 lesson assigned.");

        // Instructor2 attempts to take up the lesson but should fail because it's already taken
        boolean result2 = instructor2.takeUpLesson(lesson.getLESSON_ID());
        assertFalse(result2, "Instructor2 should not be able to take up the lesson because it's already taken by Instructor1.");

        // Instructor1 removes the lesson, freeing up the spot
        boolean removeResult = instructor1.removeTakenUpLesson(lesson.getLESSON_ID());
        assertTrue(removeResult, "Instructor1 should successfully remove the lesson.");

        // Now Instructor2 should be able to take the lesson
        boolean result3 = instructor2.takeUpLesson(lesson.getLESSON_ID());
        assertTrue(result3, "Instructor2 should be able to take up the lesson after Instructor1 removed it.");
        assertEquals(1, instructor2.getTakenUpLessons().size(), "Instructor2 should now have 1 lesson assigned.");
    }
}




