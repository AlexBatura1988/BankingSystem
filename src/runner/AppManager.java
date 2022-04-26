package runner;

import account.Account;
import Db.DB;
import account.AccountOwner;
import account.AccountProperties;
import account.BankManager;
import useCases.AccountOwnerMainMenu;
import useCases.BankManagerMainMenu;
import useCases.BankManagerStart;
import useCases.CreateAccount;
import useCases.Login;
import useCases.StartScreen;
import static Db.Constants.*;

public class AppManager {

	public static AccountOwner currUser;

	public void run() {
		init();
		int screenResponse = StartScreen.show();

		while (true) {
			switch (screenResponse) {
			case START_SCREEN: {
				screenResponse = StartScreen.show();
				break;
			}
			case LOGIN_NAME: {
				screenResponse = Login.showByName();
				break;
			}
			case LOGIN_PHONE: {
				screenResponse = Login.showByPhone();
				break;
			}
			case CREATE_ACC: {
				screenResponse = CreateAccount.show();
				break;
			}
			case LOGGED_IN: {
				if (currUser instanceof BankManager) {
					System.out.println("LoggedBankManager");
					screenResponse = BankManagerMainMenu.show();
				} else {
					System.out.println("LoggedUser");
					screenResponse = AccountOwnerMainMenu.show();

				}
				break;
			}
			case LOGOUT:{
				currUser = null;
				screenResponse = StartScreen.show();
				break;
			}
			}

		}
	}

	private void init() {
		BankManager bankManager = new BankManager();
        bankManager.phoneNumber = "123";
        bankManager.monthlyIncome = 12;
        bankManager.setUsername("m");
        bankManager.setPassword("1");
        Account account1 = new Account();
        account1.balance += 100000;
        bankManager.account = account1;
        DB.accountOwners[0] = bankManager;

        AccountOwner accountOwner = new AccountOwner();
        Account account = new Account();
        account.accountProperties = AccountProperties.BRONZE;
        accountOwner.account = account;
        accountOwner.phoneNumber = "1111";
        accountOwner.setUsername("a");
        DB.accountOwners[1] = accountOwner;
        
        
	}

}
