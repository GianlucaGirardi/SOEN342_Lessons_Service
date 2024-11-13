package AccountManager;

import lessonServices.LessonCatalog;

public class Account {
	private final String userName;   // Primary key
	private String password;
	private LessonCatalog lessonCatalog;
	private String firstName;
	private String lastName;

	public Account(String firstName, String lastName, String userName, String password, LessonCatalog lessonCatalog) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;  // Primary key
		this.password = password;
		this.lessonCatalog = lessonCatalog;
	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public LessonCatalog getLessonCatalog() {
		return lessonCatalog;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "Account [userName=" + userName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", lessonCatalog=" + (lessonCatalog != null ? lessonCatalog.toString() : "N/A") + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		Account account = (Account) obj;
		return userName.equals(account.userName);
	}

	@Override
	public int hashCode() {
		return userName.hashCode();
	}
}

