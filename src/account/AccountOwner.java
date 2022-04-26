package account;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import static account.ActivityName.*;

import Db.DB;
import useCases.checkers.CheckWithdrawalDailyLimit;

public class AccountOwner extends Person {

	public Account account;
	public double monthlyIncome;
	public Credentials credentials;

	public void setPassword(String password) {
		credentials.password = password;
	}

	public AccountOwner() {
		this.credentials = new Credentials();
	}

	public boolean checkPassword(String password) {
		return credentials.password.equals(password);
	}

	public String getUsername() {
		return credentials.username;
	}

	public void setUsername(String username) {
		credentials.username = username;
	}

	public void checkBalance() {
		System.out.println("Balance:" + account.balance);
	}

	public void produceReport() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter date from (dd mm yyyy): ");
		int day = scanner.nextInt();
		int month = scanner.nextInt();
		int year = scanner.nextInt();
		LocalDate dateFrom = LocalDate.of(year, month, day);
		for (ActivityData data : account.history) {
			if (data != null) {
				if (data.timeStamp.isAfter(dateFrom.atTime(0, 0))) {
					System.out.println(data);
				}
			}
		}
	}

	public void deposit() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Make a deposit");
		int code = (int) (1000 + Math.random() * 900);
		System.out.println("Your code: " + code);
		System.out.print("Enter code: ");
		while (true) {
			if (code == scanner.nextInt()) {
				break;
			} else {
				System.out.println("Try Again");
				code = (int) (1000 + (Math.random() * 900));
				System.out.println("Your code: " + code);
				System.out.print("Enter code: ");
			}
		}
		System.out.println("Enter the amount of funds to deposit: ");
		double amount = scanner.nextDouble();

		System.out.println("Enter message: ");
		String message = new Scanner(System.in).nextLine();

		if (account != null) {
			account.balance += amount;
		}

		ActivityData activityData = new ActivityData(ActivityName.DEPOSIT, amount, LocalDateTime.now(), message);
		account.addHistoryData(activityData);
		DB.saveCurrentUser();
		System.out.println("Ok");

	}

	public void transfer() {
		Scanner scannerDigits = new Scanner(System.in);
		Scanner scannerStrings = new Scanner(System.in);
		System.out.println("Transfer");
		double amount;
		while (true) {
			System.out.println("Enter amount (2000 max):");
			amount = scannerDigits.nextDouble();
			if (amount > 2000) {
				System.out.println("the amount exceeds the max amount");
			} else
				break;
		}

		int recipientIndex = -1;
		String phone;
		while (true) {
			System.out.println("Enter recipient phone number:");
			phone = scannerStrings.nextLine();
			for (int i = 0; i < DB.accountOwners.length - 1; i++) {
				if (DB.accountOwners[i] != null) {
					if (DB.accountOwners[i].phoneNumber.equals(phone)) {
						recipientIndex = i;
						break;
					}
				}
			}
			if (recipientIndex == -1) {
				System.out.println("Wrong phone number");
			} else
				break;
		}

		account.balance -= amount;
		account.balance -= account.accountProperties.getFee();
		DB.accountOwners[recipientIndex].account.balance += amount;
		ActivityData data1 = new ActivityData(WITHDRAWAL, amount, LocalDateTime.now(),
				"Transfer to " + DB.accountOwners[recipientIndex].firstName + " "
						+ DB.accountOwners[recipientIndex].lastName + " amount: " + amount + " fee: "
						+ account.accountProperties.getFee());
		account.addHistoryData(data1);

		ActivityData data2 = new ActivityData(TRANSFER, amount, LocalDateTime.now(),
				"From " + firstName + " " + lastName);
		DB.accountOwners[recipientIndex].account.addHistoryData(data2);
		System.out.println("Transferred  to " + DB.accountOwners[recipientIndex].firstName + " "
				+ DB.accountOwners[recipientIndex].lastName + " amount: " + amount + " fee: "
				+ account.accountProperties.getFee());
	}

	public void getLoan() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Request loan");
		double amount;
		while (true) {
			System.out.println("Enter loan amount: ");
			amount = scanner.nextDouble();
			if (amount > account.accountProperties.getLoanAmount()) {
				System.out.println("the amount exceeds the max loan amount");
			} else
				break;
		}

		int months;
		while (true) {
			System.out.println("Enter number of monthly payments: ");
			months = scanner.nextInt();
			if (months > 60) {
				System.out.println("number of payments exceeds sixty");
			} else
				break;
		}

		System.out.println("Loan amount: " + amount);
		System.out.println("Months number: " + months);
		System.out.println(
				"Monthly payment: " + (amount + (amount * (account.accountProperties.getRate() / 100))) / months);
		account.balance += amount;
		ActivityData activityData = new ActivityData(ActivityName.DEPOSIT, amount, LocalDateTime.now(), "get loan");
		account.addHistoryData(activityData);

		DB.accountOwners[0].account.addHistoryData(
				new ActivityData(WITHDRAWAL, amount, LocalDateTime.now(), "loan for " + firstName + " " + lastName));
		DB.accountOwners[0].account.balance -= amount;

		DB.saveCurrentUser();
		System.out.println("Ok");
	}

	public void withdrawal() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter amount: ");
		double amount = Math.abs(scanner.nextDouble());
		if (CheckWithdrawalDailyLimit.isLimited(amount)) {
			System.out.println("the amount exceeds the daily max");
		} else {
			System.out.println("Successfully");
			account.balance -= amount;

			ActivityData data = new ActivityData(WITHDRAWAL, amount, LocalDateTime.now(), null);
			account.addHistoryData(data);
			DB.saveCurrentUser();
		}
	}

	class Credentials {
		public String username;
		private String password;

	}

	@Override
	public String toString() {
		return super.toString() + ", monthlyIncome: " + monthlyIncome;
	}
}
