package lessonServices;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

// An offer will init a Lesson
public class LessonCatalog {
	private final ArrayList<Lesson> lessons;
	public LessonCatalog(){
		this.lessons = new ArrayList<>();
	}

	public boolean checkLessonOverlap(String city, String locationName, LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour){
		for(Lesson lesson : lessons){
			if(lesson.checkForScheduleOverlap(city, locationName, startDate, endDate, daysOfWeek, startHour, endHour)){
				return true;
			}
		}
		return false;
	}

	// Verifies if a Lesson can be created
	public Lesson createLesson(String lessonName, String lessonType, String city, String locationName, String space, LocalDate startDate, LocalDate endDate, String daysOfWeek, LocalTime startHour, LocalTime endHour){
		if(checkLessonOverlap(city, locationName, startDate, endDate, daysOfWeek, startHour, endHour)) return null;
		Lesson l = new Lesson(lessonName, lessonType, city, locationName, space, startDate, endDate, daysOfWeek, startHour, endHour);
		lessons.add(l);
		return l;
	}
}
