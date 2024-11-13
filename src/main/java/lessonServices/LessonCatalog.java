package lessonServices;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import java.util.List;

public class LessonCatalog {
	private final ArrayList<Lesson> lessons;

	public LessonCatalog() {
		this.lessons = new ArrayList<>();
	}

	public boolean checkLessonOverlap(String city, String locationName, LocalDate startDate, LocalDate endDate,
									  String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		for (Lesson lesson : lessons) {
			if (lesson.checkForScheduleOverlap(city, locationName, startDate, endDate, daysOfWeek, startHour, endHour)) {
				return true;
			}
		}
		return false;
	}

	public Lesson createLesson(String lessonName, int initialCapacity, String city, String locationName, String space,
							   LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour) {
		if (checkLessonOverlap(city, locationName, startDate, endDate, daysOfWeek, startHour, endHour)) return null;
		Lesson lesson = new Lesson(lessonName, initialCapacity, city, locationName, space, startDate, endDate, daysOfWeek, startHour, endHour);
		lessons.add(lesson);
		return lesson;
	}

	public Lesson searchLessonById(long lessonId) {
		for (Lesson lesson : lessons) {
			if (lesson.getLESSON_ID() == lessonId) {
				return lesson;
			}
		}
		return null;
	}

	public List<Lesson> searchLessonsByName(String lessonName) {
		List<Lesson> result = new ArrayList<>();
		for (Lesson lesson : lessons) {
			if (lesson.getName().equalsIgnoreCase(lessonName)) {
				result.add(lesson);
			}
		}
		return result;
	}

	public void displayLessons() {
		if (lessons.isEmpty()) {
			System.out.println("No lessons available.");
		} else {
			for (Lesson lesson : lessons) {
				System.out.println(lesson);
			}
		}
	}
}
