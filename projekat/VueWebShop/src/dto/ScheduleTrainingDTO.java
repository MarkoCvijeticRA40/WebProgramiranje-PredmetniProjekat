package dto;

import java.util.Date;

public class ScheduleTrainingDTO {

	private String trainingName;
	private String date;
	private String time;
	private String customerId;
	
	public ScheduleTrainingDTO() {}

	public ScheduleTrainingDTO(String trainingName, String date, String time, String customerId) {
		super();
		this.trainingName = trainingName;
		this.date = date;
		this.time = time;
		this.customerId = customerId;
	}

	public String getTrainingName() {
		return trainingName;
	}

	public void setTrainingName(String trainingName) {
		this.trainingName = trainingName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
}
