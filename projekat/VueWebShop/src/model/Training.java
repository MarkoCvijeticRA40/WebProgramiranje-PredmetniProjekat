package model;

public class Training {
	private String id;
	private String name;
	private String type;
	private SportObject sportObject;
	private int durationInMinutes;
	private Trainer trainer;
	private String description;
	private String image;
	private boolean isDeleted;
	
	public Training(String id, String name, String type, SportObject sportObject, int durationInMinutes, Trainer trainer,
			String description, String image) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.durationInMinutes = durationInMinutes;
		this.trainer = trainer;
		this.description = description;
		this.image = image;
		this.isDeleted = false;
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

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}

	public int getDurationInMinutes() {
		return durationInMinutes;
	}

	public void setDurationInMinutes(int durationInMinutes) {
		this.durationInMinutes = durationInMinutes;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	
	
}
