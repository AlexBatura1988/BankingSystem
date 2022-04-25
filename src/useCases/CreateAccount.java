package useCases;

import java.time.LocalDate;
import java.util.Scanner;

import Db.Constants;
import account.AccountOwner;
import account.BankManager;
import static Db.Constants.START_SCREEN;

public class CreateAccount {
	public static int show() {
		System.out.println("Enter phone number");
		Scanner scanString = new Scanner(System.in);
		Scanner scanIntFloat = new Scanner(System.in);
		AccountOwner accountOwner = new AccountOwner();
		while (true) {
			accountOwner.phoneNumber = scanIntFloat.nextInt();
			if (CheckUniquePhoneNumber.isPhoneUnique(accountOwner.phoneNumber))
				break;
			System.out.println("Phone is used, enter again");
		}
		System.out.println("Enter name (no digits)");
		accountOwner.firstName = scanString.nextLine();
		System.out.println("Enter last name (no digits)");
		accountOwner.lastName = scanString.nextLine();

		System.out.println("Enter date of birth (dd mm yyyy)");
		int day = scanIntFloat.nextInt();
		int month = scanIntFloat.nextInt();
		int year = scanIntFloat.nextInt();
		accountOwner.birthDate = LocalDate.of(year, month, day);

		System.out.println("Enter userName");
		String username;
		while (true) {
			username = scanString.nextLine();
			if (CheckUniqueUserName.isUserNameUnique(username))
				break;
			System.out.println("Username is used, enter again"); // todo check at least and provide to login?
		}
		accountOwner.setUsername(username);

		System.out.println("Enter password"); // todo 4-8 chars, must contain digit and letter
		String password = scanString.nextLine();
		accountOwner.setPassword(password);

		System.out.println("Enter monthly income");
		accountOwner.monthlyIncome = scanIntFloat.nextDouble();

		System.out.println("waiting for a manager review, approval, and account type setting");
		for (int i = 0; i < BankManager.usersToAprove.length; i++) {
			if (BankManager.usersToAprove[i] == null)
				BankManager.usersToAprove[i] = accountOwner;
		}

		// save to db
		return START_SCREEN;
	}

}
