package account;

public class Account {
	private static long accountNumber = 0;
	public double balance;
	public AccountProperties accountProperties;
	private double intrestRate;
	private double fee;
	public ActivityData[] history = new ActivityData[100];

	public void addHistoryData(ActivityData data) {
		// save History
		int historyIndex = -1;
		for (int i = 0; i < history.length - 1; i++) {
			if (history[i] == null) {
				historyIndex = i;
				break;
			}
		}
		history[historyIndex] = data;
	}

}
