package model;

public class Content {

	private String name;
	private String type;
	private String image;
	private String description;
	private int durationInMinutes;
	
	public Content() {}

	public Content(String name, String type, String image, String description, int durationInMinutes) {
		super();
		this.name = name;
		this.type = type;
		this.image = image;
		this.description = description;
		this.durationInMinutes = durationInMinutes;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}
	
}
