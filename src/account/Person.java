package account;

import java.time.LocalDate;
/**
 * 
 * @author alexb
 * abstract class Person with fields first name, last name, phone number, birthday
 *
 */


public abstract class Person {
	protected String firstName;
	protected String lastName;
	protected String phoneNumber;
	protected LocalDate birthDate;

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber
				+ ", birthDate=" + birthDate + "]";
	}

}
