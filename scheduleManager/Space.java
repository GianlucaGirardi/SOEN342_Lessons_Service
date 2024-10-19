package scheduleManager;

import java.util.Objects;

public class Space {
	private String type;

	public Space() {
		this.type = null;
	}
	
	public Space(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Space other = (Space) obj;
		return Objects.equals(type, other.type);
	}

	@Override
	public String toString() {
		return "Space [type=" + type + ", getType()=" + getType() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
}
