package account;

import java.time.LocalDateTime;

public class ActivityData {
	public ActivityName activityName;
	public Double balanceChange;
	public LocalDateTime timeStamp;
	public String info;
	/**
	 * 
	 * @param activityName enum
	 * @param balanceChange double
	 * @param timeStamp LocalDateTime
	 * @param info String
	 */
	public ActivityData(ActivityName activityName, Double balanceChange, LocalDateTime timeStamp, String info) {
        this.activityName = activityName;
        this.balanceChange = balanceChange;
        this.timeStamp = timeStamp;
        this.info = info;
    }

	@Override
	public String toString() {
		return "ActivityData [activityName=" + activityName + ", balanceChange=" + balanceChange + ", timeStamp="
				+ timeStamp + ", info=" + info + "]";
	}
	
	

}
