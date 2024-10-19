package lessonServices;

import java.util.ArrayList;

// An offer will init a Lesson
public class LessonCatalog {
	private ArrayList<Lesson> lessons;

	public LessonCatalog() {
		this.lessons = new ArrayList<Lesson>();
	}

	public void  displayAllBookings() {
		this.lessons.forEach(el -> System.out.println(el));
	}
}
