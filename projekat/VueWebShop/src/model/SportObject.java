package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class SportObject {

	private String id;
	private String name;
	private String type;
	private ArrayList<Content> contents;
	private Location location;
	private Double averageGrade;
	private String image;
	private WorkTime workTime;
	private SportObjectStatus status;
	private boolean isDeleted;
	
	public SportObject() {}
	
	public SportObject(String id, String name, String type, ArrayList<Content> contents, Location location, Double averageGrade, String image,
			WorkTime workTime) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.contents = contents;
		this.location = location;
		this.averageGrade = averageGrade;
		this.image = image;
		this.workTime = workTime;
		setStatus();
		this.isDeleted = false;
	}
	
	public SportObject(String id,String name, String type, Location location, WorkTime workTime, String image) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.location = location;
		this.averageGrade = 0.0;
		this.workTime = workTime;
		this.image = image;
		setStatus();
		this.isDeleted = false;
	}

	public void setStatus() {
		if (this.workTime.getEndTime().getHour() == 0 && this.workTime.getEndTime().getMinute() == 0) {
			if (this.workTime.getStartTime().compareTo(LocalTime.now()) <= 0 && LocalTime.MAX.compareTo(LocalTime.now()) >= 0) {
				this.status = status.Open;
			}
			else {
				this.status = status.Close;
			}
			
		}
		else if (this.workTime.getStartTime().compareTo(this.workTime.getEndTime()) >= 0) {
			if (this.workTime.getEndTime().compareTo(LocalTime.now()) <= 0 && this.workTime.getStartTime().compareTo(LocalTime.now()) >= 0) {
				this.status = status.Close;
			}
			else {
				this.status = status.Open;
			}
		}
		else {
			if (this.workTime.getStartTime().compareTo(LocalTime.now()) <= 0 && this.workTime.getEndTime().compareTo(LocalTime.now()) >= 0) {
				this.status = status.Open;
			}
			else {
				this.status = status.Close;
			}
		}
	}
	
	public SportObjectStatus getStatus() {
		return status;
	}

	public String getName() {
		return name;
	}
	
	public String getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<Content> getContent() {
		return contents;
	}

	public void setContent(ArrayList<Content> contents) {
		this.contents = contents;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Double getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(Double averageGrade) {
		this.averageGrade = averageGrade;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public WorkTime getWorkTime() {
		return workTime;
	}

	public void setWorkTime(WorkTime workTime) {
		this.workTime = workTime;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String toString() {
		return averageGrade.toString();
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}