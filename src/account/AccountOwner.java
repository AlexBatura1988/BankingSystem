package account;

import java.time.LocalDate;

public class AccountOwner extends Person {

	private Account account;
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

//	public void checkBalance() {
//		
//	}
//	public void produceReport(LocalDate start) {
//		
//	}
//	public void deposit() {
//		
//	}
//	public void withdrawal() {
//		
//	}
//	public void transferFunds()
//	{
//		
//	}

	class Credentials {
		public String username;
		private String password;

	}
}
