package useCases.checkers;

import Db.DB;
import account.AccountOwner;

public class CheckUniquePhoneNumber {
	public static boolean isPhoneUnique(String phoneNumber) {
        for (AccountOwner owner: DB.accountOwners) {
            if (owner != null) {
                if (owner.getPhoneNumber().equals(phoneNumber))  return false;
            }
        }
        return true;
    }
	

}
