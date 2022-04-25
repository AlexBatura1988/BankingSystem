package useCases;

import java.util.Scanner;

import account.AccountOwner;
import runner.AppManager;
import Db.Constants;
import Db.DB;
import static Db.Constants.LOGGED_IN;

public class Login {
	public static  int showByName() {
		System.out.println("Enter username and pass");
		Scanner scanner = new Scanner(System.in);
		String scan;
		while (true) {
			scan = scanner.nextLine();
			for (AccountOwner accountOwner : DB.accountOwners) {
				if (accountOwner != null) {
					if (accountOwner.getUsername().equals(scan.split(" ")[0])) {
						if (accountOwner.checkPassword(scan.split(" ")[1])) {
							AppManager.currUser = accountOwner;
							return    LOGGED_IN;
						}
						System.out.println("Wrong password");
					}
				}
			}
			System.out.println("Wrong username");
		}
	}

	public static int showByPhone() {
		System.out.println("Enter phone");
		Scanner scanner = new Scanner(System.in);
		int scan;
		while (true) {
			scan = scanner.nextInt();
			for (AccountOwner accountOwner : DB.accountOwners) {
				if (accountOwner != null) {
					if (accountOwner.phoneNumber == scan) {
						AppManager.currUser = accountOwner;
						return LOGGED_IN;
					}
				}
			}
			System.out.println("Wrong phone");
		}
	}

}
