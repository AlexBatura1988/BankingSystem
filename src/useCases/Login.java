package useCases;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

import account.AccountOwner;
import account.AccountsLocker;
import runner.AppManager;
import Db.Constants;
import Db.DB;
import static Db.Constants.LOGGED_IN;

public class Login {
	public static int showByName() {
		System.out.println("Enter username and pass");
		Scanner scanner = new Scanner(System.in);
		String scan;
		int wrongPassCounter = 0;
		boolean foundUsername;
		while (true) {
			foundUsername = false;
			scan = scanner.nextLine();
			for (AccountOwner accountOwner : DB.accountOwners) {
				if (accountOwner != null) {
					if (accountOwner.getUsername().equals(scan.split(" ")[0])) {
						foundUsername = true;

						if (AccountsLocker.getUsersLockDate(accountOwner) != null) {
							Duration duration = Duration.between((LocalDateTime.now()),
									AccountsLocker.getUsersLockDate(accountOwner));
							System.out.println("Account is locked for " + duration.toMinutes() + " minutes");

						} else {
							if (accountOwner.checkPassword(scan.split(" ")[1])) {
								AppManager.currUser = accountOwner;
								return LOGGED_IN;
							}
							System.out.println("Wrong password");
							wrongPassCounter++;
							if (wrongPassCounter == 3) {
								AccountsLocker.addUserToLock(accountOwner);
								Duration duration = Duration.between((LocalDateTime.now()),
										AccountsLocker.getUsersLockDate(accountOwner));
								System.out.println("Account is locked for " + duration.toMinutes() + " minutes");
								wrongPassCounter = 0;
							}
						}
					}
				}
			}
			if (!foundUsername)
				System.out.println("Wrong username");
		}
	}

	public static int showByPhone() {
		System.out.println("Enter phone");
		Scanner scanner = new Scanner(System.in);
		String scan;
		while (true) {
			scan = scanner.nextLine();

			for (AccountOwner accountOwner : DB.accountOwners) {
				if (accountOwner != null) {

					if (accountOwner.phoneNumber.equals(scan)) {
						AppManager.currUser = accountOwner;
						return LOGGED_IN;
					}
				}
			}
			System.out.println("Wrong phone");
		}
	}

}
