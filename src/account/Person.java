package account;

import java.time.LocalDate;

public abstract class Person {
	public String firstName;
	public String lastName;
	public String phoneNumber;
	public LocalDate birthDate;



	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate + "]";
	}

}
