package useCases;

import java.time.LocalDateTime;
import java.util.Scanner;

import Db.DB;
import account.ActivityData;
import account.ActivityName;
import runner.AppManager;
import static Db.Constants.LOGOUT;
import static account.ActivityName.*;

public class AccountOwnerMainMenu {
	public static int show() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			showMenu();
			int input = scanner.nextInt();
			switch (input) {
			case 1: {
				System.out.println("Balance:" + AppManager.currUser.account.balance);
				break;
			}
			case 2: {
				makeDeposit(scanner);
				break;
			}
			case 3: {
				withdrawal(scanner);
				break;
			}

			case 0: {
				return LOGOUT;
			}

			}
		}

	}

	private static void withdrawal(Scanner scanner) {
		System.out.println("Enter amount: ");
		double amount = Math.abs(scanner.nextDouble());
		if (CheckWithdrawalDailyLimit.isLimited(amount)) {
			System.out.println("the amount exceeds the daily max :" + AppManager.currUser.account.accountProperties.getWithdrawalAmount() );
		} else {
			System.out.println("Successfully");
			AppManager.currUser.withdrawal(amount);
			ActivityData data = new ActivityData(WITHDRAWAL, amount, LocalDateTime.now(), null);
			AppManager.currUser.account.addHistoryData(data);
			DB.saveCurrentUser();
		}
	}

	private static void makeDeposit(Scanner scanner) {
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

		AppManager.currUser.deposit(amount);

		ActivityData activityData = new ActivityData(ActivityName.DEPOSIT, amount, LocalDateTime.now(), message);
		AppManager.currUser.account.addHistoryData(activityData);
		DB.saveCurrentUser();

		System.out.println("Ok");

	}

	private static void showMenu() {
		System.out.println("1. Check account balance");
		System.out.println("2. Make a deposit");
		System.out.println("3. Withdrawal");
		System.out.println("0. logout");
	}

}
