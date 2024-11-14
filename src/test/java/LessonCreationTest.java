import lessonServices.Lesson;
import lessonServices.LessonCatalog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

public class LessonCreationTest {

    private LessonCatalog lessonCatalog;

    @BeforeEach
    public void setUp() {
        lessonCatalog = new LessonCatalog();
    }

    @Test
    public void testCreateLessonSuccess() {

        // Create lesson
        Lesson lesson = lessonCatalog.createLesson("Math 101", 30, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        assertNotNull(lesson, "The lesson should be created successfully.");
        assertEquals("Math 101", lesson.getName(), "The lesson name should be Math 101.");
        assertTrue(lesson.isAvailable(), "The lesson should be available.");
    }

    @Test
    public void testOverlappingTimeslotsNotAllowed() {

        // Creating a lessons where schedules overlap is not allowed
        lessonCatalog.createLesson("Math 101", 30, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        Lesson conflictingLesson = lessonCatalog.createLesson("Math 102", 20, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        assertNull(conflictingLesson, "The lesson should not be created due to overlapping timeslots.");
    }

    @Test
    public void testOverlappingTimeslotsInDifferentCitiesAllowed() {

        // Create a lesson in New York
        lessonCatalog.createLesson("Math 101", 30, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // Create an overlapping lesson in a different city
        Lesson nonConflictingLesson = lessonCatalog.createLesson("Math 102", 20, "Los Angeles", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // Creation is allowed
        assertNotNull(nonConflictingLesson, "The lesson should be created successfully even though the timeslot overlaps in a different city.");
    }

    @Test
    public void testFindLessonById() {
        Lesson lesson = lessonCatalog.createLesson("Math 101", 30, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));

        // Find lesson by lesson id
        Lesson foundLesson = lessonCatalog.searchLessonById(lesson.getLESSON_ID());

        assertNotNull(foundLesson, "The lesson should be found by its ID.");
        assertEquals(lesson.getLESSON_ID(), foundLesson.getLESSON_ID(), "The lesson IDs should match.");
    }

    @Test
    public void testDisplayAllLessons() {
        lessonCatalog.createLesson("Math 101", 30, "New York", "Room 101", "Space A",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Mon#Wed#Fri", LocalTime.of(10, 0), LocalTime.of(12, 0));
        lessonCatalog.createLesson("Physics 101", 20, "New York", "Room 102", "Space B",
                LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 20), "Tue#Thu", LocalTime.of(12, 0), LocalTime.of(14, 0));

        // Display all lessons to console
        lessonCatalog.displayAllLessons();
    }
}
