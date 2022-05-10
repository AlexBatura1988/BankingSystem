package useCases;

import java.util.Scanner;
import static Db.Constants.LOGOUT;

import Db.Constants;
import Db.DB;
import account.Account;
import account.AccountOwner;
import account.AccountProperties;
import account.BankManager;
import runner.AppManager;

/**
 * 
 * @author alexb menu for bank manager
 *
 */

public class BankManagerMainMenu {

	public static Constants show() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			showMenu();
			int input = scanner.nextInt();
			switch (input) {
			case 1: {
				showUsersToApprove();
				break;
			}
			case 2: {
				AppManager.currUser.checkBalance();
				break;
			}
			case 3: {
				AppManager.currUser.produceReport();
				break;
			}
			case 0: {
				return LOGOUT;
			}

			}
		}
	}

	private static void showUsersToApprove() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			for (int i = 0; i < BankManager.getUsersToApprove().length; i++) {
				if (BankManager.getUsersToApprove()[i] != null) {
					System.out.println((i + 1) + " " + BankManager.getUsersToApprove()[i]);
				}
			}
			System.out.println();
			showUsersMenu();
			int input = scanner.nextInt();
			if (input == 0) {
				break;
			} else if (BankManager.getUsersToApprove()[input - 1] != null) {
				showUserInfo(input - 1);
			}
		}
	}

	private static void showUserInfo(int index) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			AccountOwner owner = BankManager.getUsersToApprove()[index];
			System.out.println("First name: " + owner.getFirstName());
			System.out.println("Last name: " + owner.getLastName());
			System.out.println("Phone number: " + owner.getPhoneNumber());
			System.out.println("Birthday: " + owner.getBirthDate());
			System.out.println("Monthly income: " + owner.getMonthlyIncome());
			if (owner.getAccount() != null) {
				System.out.println("Property: " + owner.getAccount().accountProperties);
			}
			System.out.println();

			showUserInfoMenu();
			int input = scanner.nextInt();
			if (input == 0) {
				break;
			} else if (input == 1) {
				setPropertyForUsersAccount(index);
			} else if (input == 2) {
				if (owner.getAccount() == null) {
					System.out.println("NOT APPROVED");
					System.out.println("Set property for users account");
				} else {

//					for (int i = 0; i < DB.accountOwners.length - 1; i++) {
//						if (DB.accountOwners[i] == null) {
//							DB.accountOwners[i] = BankManager.getUsersToApprove()[index];
//							BankManager.getUsersToApprove()[index] = null;
//							break;
//						}
//					}
//
//					System.out.println("Account Approved");
//					break;
					
					BankManager.saveAccountToDB(index);
					break;
				}
			}
		}
	}

	private static void setPropertyForUsersAccount(int index) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			showUserPropertyMenu();
			int input = scanner.nextInt();
			Account account = new Account();
			if (input == 0) {
				break;
			} else if (input == 1) {
				account.accountProperties = AccountProperties.BRONZE;
				BankManager.getUsersToApprove()[index].account = account;
				break;
			} else if (input == 2) {
				account.accountProperties = AccountProperties.SILVER;
				BankManager.getUsersToApprove()[index].account = account;
				break;
			} else if (input == 3) {
				account.accountProperties = AccountProperties.GOLD;
				BankManager.getUsersToApprove()[index].account = account;
				break;
			} else if (input == 4) {
				account.accountProperties = AccountProperties.TITANIUM;
				BankManager.getUsersToApprove()[index].account = account;
				break;
			}
		}
	}

	private static void showUserPropertyMenu() {

		System.out.println("1. BRONZE");
		System.out.println("2. SILVER");
		System.out.println("3. GOLD");
		System.out.println("4. TITANIUM");
		System.out.println("0. back");
	}

	private static void showUserInfoMenu() {

		System.out.println("1. set Property");
		System.out.println("2. Approve");
		System.out.println("0. Back");
	}

	private static void showUsersMenu() {
		System.out.println("0. Back");
		System.out.println("1..100 select User");
	}

	private static void showMenu() {
		System.out.println("1. Show users to approve");
		System.out.println("2. Balance");
		System.out.println("3. report");
		System.out.println("0. logout");
	}

}
