package useCases;

import java.time.LocalDateTime;
import java.util.Scanner;

import Db.Constants;
import account.ActivityData;
import account.ActivityName;
import runner.AppManager;

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
			}

			}
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

		AppManager.currUser.account.balance += amount;

		// save History
		int historyIndex = -1;
		 for (int i = 0; i < AppManager.currUser.account.history.length -1; i++) {
	            if (AppManager.currUser.account.history[i] == null) {
	                historyIndex = i;
	                break;
	            }
	        }

		ActivityData activityData = new ActivityData();
		activityData.activityName = ActivityName.DEPOSIT;
		activityData.balanceChange = amount;
		activityData.timeStamp = LocalDateTime.now();
		activityData.info = message;
		AppManager.currUser.account.history[historyIndex] = activityData;

		System.out.println("Ok");
	}

	private static void showMenu() {
		System.out.println("1. Check account balance");
		System.out.println("2. Make a deposit");
	}

}
