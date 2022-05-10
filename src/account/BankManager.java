package account;

import java.time.LocalDate;

import Db.DB;

public class BankManager extends AccountOwner {

	private static AccountOwner[] usersToApprove = new AccountOwner[100];

	/**
	 * 
	 * @param owner adding user to approve my manager
	 */
	public static void addUserToApprove(AccountOwner owner) {
		for (int i = 0; i < usersToApprove.length - 1; i++) {
			if (usersToApprove[i] == null) {
				usersToApprove[i] = owner;
				break;
			}
		}
	}

	public static AccountOwner[] getUsersToApprove() {
		return usersToApprove;
	}
	
	public static void saveAccountToDB(int index) {
		for (int i = 0; i < DB.accountOwners.length - 1; i++) {
			if (DB.accountOwners[i] == null) {
				DB.accountOwners[i] = BankManager.getUsersToApprove()[index];
				BankManager.getUsersToApprove()[index] = null;
				break;
			}
		}

		System.out.println("Account Approved");
		
	}

}
