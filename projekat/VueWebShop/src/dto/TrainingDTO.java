package dto;

public class TrainingDTO {

	private String id;
	private String name;
	private String type;
	private String image;
	private String description;
	private int durationInMinutes;
	private String trainerUsername;
	public String sportObjectId;
	
	public TrainingDTO() {}

	public TrainingDTO(String id, String name, String type, String image, String description,
			int durationInMinutes, String trainerUsername, String sportObjectId) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.image = image;
		this.description = description;
		this.durationInMinutes = durationInMinutes;
		this.trainerUsername = trainerUsername;
		this.sportObjectId = sportObjectId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getTrainerUsername() {
		return trainerUsername;
	}

	public void setTrainerUsername(String trainerUsername) {
		this.trainerUsername = trainerUsername;
	}

	public String getSportObjectId() {
		return sportObjectId;
	}

	public void setSportObjectId(String sportObjectId) {
		this.sportObjectId = sportObjectId;
	}
	
	
	
}
