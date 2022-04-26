package account;

import java.time.LocalDate;

public class BankManager extends AccountOwner {

	private static AccountOwner[] usersToApprove = new AccountOwner[100];

//
//
//	public void setAndApproveAcc() {
//		
//	}
	public static void addUserToApprove(AccountOwner owner) {
		for (int i = 0; i < usersToApprove.length - 1; i++) {
			if (usersToApprove[i] == null) {
				usersToApprove[i] = owner;
				break;
			}
		}
	}
//	
//	public void produceReport(LocalDate start) {
//		
//	}

	public static AccountOwner[] getUsersToApprove() {
		return usersToApprove;
	}

}
