package useCases;

import Db.DB;
import account.AccountOwner;

public class CheckUniquePhoneNumber {
	public static boolean isPhoneUnique(int phoneNumber) {
        for (AccountOwner owner: DB.accountOwners) {
            if (owner != null) {
                if (owner.phoneNumber == phoneNumber) return false;
            }
        }
        return true;
    }
	

}
