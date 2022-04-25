package account;

import java.time.LocalDate;

public abstract class Person {
	public String firstName;
	public String lastName;
	public int phoneNumber;
	public LocalDate birthDate;

//	public Person(String firstName, String lastName, String phoneNumber, LocalDate birthDate) {
//		this.firstName = firstName;
//		this.lastName = lastName;
//		this.phoneNumber = phoneNumber;
//		this.birthDate = birthDate;
//	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
	}

}
