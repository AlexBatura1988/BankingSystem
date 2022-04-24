package users;

import java.time.LocalDate;

public abstract class Person {
	protected String firstName;
	protected String lastName;
	protected String phoneNumber;
	protected LocalDate birthDate;
	
	public Person(String firstName, String lastName, String phoneNumber, LocalDate birthDate) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", birthDate=" + birthDate + "]";
	}
	
	
	
  

}
