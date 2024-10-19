package scheduleManager;

import java.util.Objects;

public class Location {
	private String name;
	private String city;
	
	public Location() {
		this.name = null;
		this.city = null;
	}
	
	public Location(String name, String city) {
	
		this.name = name;
		this.city = city;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Location other = (Location) obj;
		return Objects.equals(city, other.city) && Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", city=" + city + ", getName()=" + getName() + ", getCity()=" + getCity()
				+ "]";
	}
	
}
