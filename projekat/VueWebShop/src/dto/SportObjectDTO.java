package dto;

public class SportObjectDTO {
	
	private String id;
	private String name;
	private String type;
	private String content;
	private String location;
	private Double averageGrade;
	private String image;
	private String workTime;
	private String status;
	
	public SportObjectDTO(String id, String name, String type, String content, String location, Double averageGrade,
			String image, String workTime,String status) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.content = content;
		this.location = location;
		this.averageGrade = averageGrade;
		this.image = image;
		this.workTime = workTime;
		this.status = status;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
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

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
}
