package account;

import java.time.LocalDateTime;

public class ActivityData {
	public ActivityName activityName;
	public Double balanceChange;
	public LocalDateTime timeStamp;
	public String info;
	
	public ActivityData(ActivityName activityName, Double balanceChange, LocalDateTime timeStamp, String info) {
        this.activityName = activityName;
        this.balanceChange = balanceChange;
        this.timeStamp = timeStamp;
        this.info = info;
    }

}
