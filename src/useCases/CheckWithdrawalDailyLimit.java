package useCases;

import java.time.LocalDate;
import static account.ActivityName.WITHDRAWAL;

import account.ActivityData;
import runner.AppManager;

public class CheckWithdrawalDailyLimit {

	public static boolean isLimited(double amount) {
		if (AppManager.currUser != null) {
			double todayWithdrawalSum = 0.0;
			for (ActivityData data : AppManager.currUser.account.history) {
				if (data != null) {
					if (data.timeStamp.toLocalDate().isEqual(LocalDate.now()) && data.activityName == WITHDRAWAL) {
						todayWithdrawalSum += data.balanceChange;
					}
				}
			}
			if (todayWithdrawalSum + amount > AppManager.currUser.account.accountProperties.getWithdrawalAmount()) {
				return true;
			}
		}
		return false;
	}

}
