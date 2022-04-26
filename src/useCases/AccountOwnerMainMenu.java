package useCases;

import java.time.LocalDateTime;
import java.util.Scanner;

import Db.Constants;
import Db.DB;
import account.ActivityData;
import account.ActivityName;
import runner.AppManager;
import useCases.checkers.CheckWithdrawalDailyLimit;

import static Db.Constants.LOGOUT;
import static account.ActivityName.*;
/**
 * 
 * @author alexb
 * main menu for account owner
 *
 */

public class AccountOwnerMainMenu {
	public static Constants show() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			showMenu();
			int input = scanner.nextInt();
			switch (input) {
			case 1: {
				AppManager.currUser.checkBalance();
				break;
			}
			case 2: {
				AppManager.currUser.deposit();
				break;
			}
			case 3: {
				AppManager.currUser.withdrawal();
				break;
			}
			case 4: {
				AppManager.currUser.getLoan();
				break;
			}
			case 5: {
				AppManager.currUser.transfer();
				break;
			}
			case 6: {
				AppManager.currUser.produceReport();
				break;
			}
			case 7: {
                AppManager.currUser.payBill();
                break;
            }

			case 0: {
				return LOGOUT;
			}
			}
		}

	}

	private static void showMenu() {
		System.out.println("1. Check account balance");
		System.out.println("2. Make a deposit");
		System.out.println("3. Withdrawal");
		System.out.println("4. Request loan");
		System.out.println("5. Transfer");
		System.out.println("6. Report");
		System.out.println("7. Pay bill");
		System.out.println("0. logout");
	}

}
