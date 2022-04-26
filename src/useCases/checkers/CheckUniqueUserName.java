package useCases.checkers;

import Db.DB;
import account.AccountOwner;

public class CheckUniqueUserName {
	public static boolean isUserNameUnique(String username) {
        for (AccountOwner owner: DB.accountOwners) {
            if (owner != null) {
                if (owner.getUsername().equals(username)) return false;
            }
        }
        return true;
    }

}
