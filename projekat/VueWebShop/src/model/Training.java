package model;

public class Training {
	
	private String name;
	private String type;
	private SportObject sportObject;
	private int durationInMinutes;
	private Trainer trainer;
	private String description;
	// slika
	
	public Training(String name, String type, SportObject sportObject, int durationInMinutes, Trainer trainer,
			String description) {
		super();
		this.name = name;
		this.type = type;
		this.sportObject = sportObject;
		this.durationInMinutes = durationInMinutes;
		this.trainer = trainer;
		this.description = description;
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
	
	

}
