package lessonServices;

import java.util.Objects;

public class Booking {
	private static long bookingCounter = 1000000;
	private final long  BOOKING_ID = bookingCounter;
	
	public Booking() {
		bookingCounter++;
	}
	
	public long getBookingId() {
		return this.BOOKING_ID;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		return BOOKING_ID == other.BOOKING_ID;
	}

	@Override
	public String toString() {
		return "Booking [BOOKING_ID=" + BOOKING_ID + ", getBookingId()=" + getBookingId() + "]";
	}
	
}
