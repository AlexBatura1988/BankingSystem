package runner;

import account.AccountOwner;
import account.BankManager;
import useCases.BankManagerStart;
import useCases.CreateAccount;
import useCases.Login;
import useCases.StartScreen;
import static Db.Constants.*;

import Db.DB;

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
					screenResponse = BankManagerStart.show();
				} else {
					System.out.println("LoggedUser");
					return;

				}
				break;
			}
			}

		}
	}

	private void init() {
		AccountOwner accountOwner = new AccountOwner();
		accountOwner.phoneNumber = 1234;
		accountOwner.monthlyIncome = 12;
		accountOwner.setUsername("alex");
		accountOwner.setPassword("1234");
		DB.accountOwners[0] = accountOwner;

		accountOwner = new BankManager();
		accountOwner.phoneNumber = 123;
		accountOwner.monthlyIncome = 12;
		accountOwner.setUsername("Gay");
		accountOwner.setPassword("1234");
		DB.accountOwners[1] = accountOwner;
	}

}
