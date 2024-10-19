package lessonServices;

import java.util.Objects;

public class Lesson {
	private static long bookingCounter = 1;
	private final long BOOKING_ID = bookingCounter;
	private String name;
	
	public Lesson() {
		bookingCounter++;
		this.name = null;
	}
	
	public Lesson(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lesson other = (Lesson) obj;
		return BOOKING_ID == other.BOOKING_ID && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Lesson [BOOKING_ID=" + BOOKING_ID + ", name=" + name + ", getName()=" + getName() + "]";
	}
	
}
