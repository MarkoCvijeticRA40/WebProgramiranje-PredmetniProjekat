package model;

import java.util.Date;

public class Trainer extends User{

	private TrainingHistory trainingHistory;

	public Trainer(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			TrainingHistory trainingHistory) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.trainingHistory = trainingHistory;
	}

	public TrainingHistory getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(TrainingHistory trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	
	
}
