package model;

import java.util.Date;

public class MembershipValidation {
	
	private Date startTimeDate;
	private Date endTimeDate;
	
	public Date getStartTimeDate() {
		return startTimeDate;
	}
	public void setStartTimeDate(Date startTimeDate) {
		this.startTimeDate = startTimeDate;
	}
	public Date getEndTimeDate() {
		return endTimeDate;
	}
	public void setEndTimeDate(Date endTimeDate) {
		this.endTimeDate = endTimeDate;
	}
	public MembershipValidation(Date startTimeDate, Date endTimeDate) {
		super();
		this.startTimeDate = startTimeDate;
		this.endTimeDate = endTimeDate;
	}
	
}