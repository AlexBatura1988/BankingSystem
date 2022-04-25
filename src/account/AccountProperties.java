package account;

public enum AccountProperties {
	BRONZE(4.5,6,5,7.5,10000,2500),
	SILVER(3,4.5,3.8,5,20000, 4000),
	GOLD(1.5,3,1.75,3.8,50000,6000),
	TITANIUM(0,0,0,0,0,0);
	double minRate, maxRate,minFee, maxFee;
	int loanAmount, withdrawalAmount;

	AccountProperties(double minRate, double maxRate, double minFee, double maxFee, int loanAmount, int withdrawalAmount) {
		// TODO Auto-generated constructor stub
	}
	

	

	

	

}
