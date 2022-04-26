package account;

public enum AccountProperties {
	BRONZE(4.5, 6, 5, 7.5, 10000, 2500), SILVER(3, 4.5, 3.8, 5, 20000, 4000), GOLD(1.5, 3, 1.75, 3.8, 50000, 6000),
	TITANIUM(0, 0, 0, 0, 0, 0);

	double minRate, maxRate, minFee, maxFee;
	int loanAmount, withdrawalAmount;

	public int getWithdrawalAmount() {
		return withdrawalAmount;
	}
	
	public int getLoanAmount() {
        return loanAmount;
    }

    public double getRate() {
        return maxRate;
    }

	AccountProperties(double minRate, double maxRate, double minFee, double maxFee, int loanAmount,
			int withdrawalAmount) {
		this.minRate = minRate;
		this.maxRate = maxRate;
		this.minFee = minFee;
		this.maxFee = maxFee;
		this.loanAmount = loanAmount;
		this.withdrawalAmount = withdrawalAmount;
	}

}
