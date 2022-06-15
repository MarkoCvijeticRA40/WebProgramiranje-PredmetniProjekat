package model;

public class SportObject {

	private String name;
	private String type;
	private String content;
	private Location location;
	private Double averageGrade;
	private String image;
	private WorkTime workTime;
	
	public SportObject(String name, String type, String content, Location location, Double averageGrade, String image,
			WorkTime workTime) {
		super();
		this.name = name;
		this.type = type;
		this.content = content;
		this.location = location;
		this.averageGrade = averageGrade;
		this.image = image;
		this.workTime = workTime;
	}

	public String getName() {
		return name;
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

	
	
	
	
	
}
