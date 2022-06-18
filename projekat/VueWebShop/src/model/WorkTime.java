package model;

import java.time.LocalTime;

public class WorkTime {

	private LocalTime startTime;
	private LocalTime endTime;
	
	
	public WorkTime(LocalTime startTime, LocalTime endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	@Override
	public String toString() {
		return this.startTime.toString() + "-" + this.endTime.toString();
	}
}
