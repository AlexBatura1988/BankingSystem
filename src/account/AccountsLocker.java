package account;

import java.time.LocalDateTime;

public class AccountsLocker {
	private static UserTimeoutData[] userTimeoutData = new UserTimeoutData[100];

	public static void addUserToLock(AccountOwner owner) {
		for (int i = 0; i < userTimeoutData.length - 1; i++) {
			if (userTimeoutData[i] == null) {
				userTimeoutData[i] = new UserTimeoutData(owner, LocalDateTime.now().plusMinutes(30));
				break;
			}
		}
	}

	public static LocalDateTime getUsersLockDate(AccountOwner owner) {
		for (int i = 0; i < userTimeoutData.length - 1; i++) {
			if (userTimeoutData[i] != null
					&& userTimeoutData[i].accountOwner.getUsername().equals(owner.getUsername())) {
				if (LocalDateTime.now().isAfter(userTimeoutData[i].localDateTime)) {
					userTimeoutData[i] = null;
					return null;
				} else {
					return userTimeoutData[i].localDateTime;
				}
			}
		}
		return null;
	}

	static class UserTimeoutData {
		AccountOwner accountOwner;
		LocalDateTime localDateTime;

		public UserTimeoutData(AccountOwner accountOwner, LocalDateTime localDateTime) {
			this.accountOwner = accountOwner;
			this.localDateTime = localDateTime;
		}
	}

}
