package account;

import java.time.LocalDate;

public class AccountOwner extends Person {

	public Account account;
    public double monthlyIncome;
    public Credentials credentials;
    
    public void setPassword(String password) {
        credentials.password = password;
    }
	
	public AccountOwner() {
        this.credentials = new Credentials();
    }
	
	public boolean checkPassword(String password) {
        return credentials.password.equals(password);
    }

    public String getUsername() {
        return credentials.username;
    }
    public void setUsername(String username) {
        credentials.username = username;
    }

	public double checkBalance() {
		if(account != null) {
			return account.balance;
		}else {
			return 0.0;
		}
		
	}
//	public void produceReport(LocalDate start) {
//		
//	}
	public void deposit(double amount) {
		if (account != null) {
            account.balance += amount;
        }
		
	}
	public void withdrawal(double amount) {
		if(account != null) {
			account.balance -= amount;
		}
	}
//	public void transferFunds()
//	{
//		
//	}

	class Credentials {
		public String username;
		private String password;

	}
	@Override
    public String toString() {
        return super.toString() + ", monthlyIncome: " + monthlyIncome;
    }
}
