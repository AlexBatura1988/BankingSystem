package Db;

import account.AccountOwner;
import runner.AppManager;

public class DB {
	public static AccountOwner[] accountOwners = new AccountOwner[100];

	public static void saveCurrentUser() {
		if (AppManager.currUser != null) {
			for (int i = 0; i < accountOwners.length - 1; i++) {
				if (accountOwners[i].getUsername().equals(AppManager.currUser.getUsername())) {
					accountOwners[i] = AppManager.currUser;
					break;
				}
			}
		}
	}

}
