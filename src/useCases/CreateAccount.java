package useCases;

import java.time.LocalDate;
import java.util.Scanner;

import Db.Constants;
import Db.DB;
import account.AccountOwner;
import account.BankManager;
import useCases.checkers.CheckUniquePhoneNumber;
import useCases.checkers.CheckUniqueUserName;

import static Db.Constants.START_SCREEN;

public class CreateAccount {
	public static Constants show() {
		System.out.println("Enter phone number");
		Scanner scanString = new Scanner(System.in);
		Scanner scanIntFloat = new Scanner(System.in);
		AccountOwner accountOwner = new AccountOwner();
		while (true) {
			accountOwner.setPhoneNumber(scanString.nextLine());
			if (accountOwner.getPhoneNumber().length() < 4) {
				System.out.println("Too short number, enter again");
				continue;
			}
			if (accountOwner.getPhoneNumber().matches(".*[a-zA-Z].*")) {
				System.out.println("Phone number contains letters, enter again");
				continue;
			}
			if (CheckUniquePhoneNumber.isPhoneUnique(accountOwner.getPhoneNumber()))
				break;
			System.out.println("Phone is used, enter again");
		}
		while (true) {
			System.out.println("Enter name (no digits)");
			accountOwner.setFirstName(scanString.nextLine());
			if (accountOwner.getFirstName().matches(".*\\d+.*")) {
				System.out.println("Wrong input, name contains digits");
			} else
				break;
		}

		while (true) {
			System.out.println("Enter last name (no digits)");
			accountOwner.setLastName(scanString.nextLine());
			if (accountOwner.getLastName().matches(".*\\d+.*")) {
				System.out.println("Wrong input, last name contains digits");
			} else
				break;
		}

		System.out.println("Enter date of birth (dd mm yyyy)");
		int day = scanIntFloat.nextInt();
		int month = scanIntFloat.nextInt();
		int year = scanIntFloat.nextInt();
		accountOwner.setBirthDate(LocalDate.of(year, month, day));

		System.out.println("Enter userName");
		String username;
		while (true) {
			username = scanString.nextLine();
			if (CheckUniqueUserName.isUserNameUnique(username))
				break;
			System.out.println("Username is used, enter again");
		}
		accountOwner.setUsername(username);

		while (true) {
			System.out.println("Enter password (4-8 digits and letters)");
			String password = scanString.nextLine();
			if (password.length() < 4 || password.length() > 8) {
				System.out.println("wrong length");
				continue;
			}
			if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d+.*")) {
				System.out.println("Wrong input");
			} else {
				accountOwner.setPassword(password);

				break;
			}
		}

		System.out.println("Enter monthly income");
		accountOwner.setMonthlyIncome(scanIntFloat.nextDouble());

		System.out.println("waiting for a manager review, approval, and account type setting");
		BankManager.addUserToApprove(accountOwner);

		return START_SCREEN;
	}

}
