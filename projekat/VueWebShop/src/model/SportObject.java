package model;

import java.time.LocalTime;

public class SportObject {

	private String id;
	private String name;
	private String type;
	private String content;
	private Location location;
	private Double averageGrade;
	private String image;
	private WorkTime workTime;
	private SportObjectStatus status;
	
	public SportObject(String id,String name, String type, String content, Location location, Double averageGrade, String image,
			WorkTime workTime) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.content = content;
		this.location = location;
		this.averageGrade = averageGrade;
		this.image = image;
		this.workTime = workTime;
		setStatus();
	}

	public void setStatus() {
		if(this.workTime.getStartTime().compareTo(LocalTime.now())<=0 && this.workTime.getEndTime().compareTo(LocalTime.now())>=0)
		{
			this.status = status.Open;
		}
		else {
			this.status = status.Close;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
}
