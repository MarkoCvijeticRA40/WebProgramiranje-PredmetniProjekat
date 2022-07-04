package model;

import java.util.ArrayList;
import java.util.Date;

public class Trainer extends User{

	private ArrayList<TrainingHistory> trainingHistory;
	
	public Trainer() {
		
	}
	
	public Trainer(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			ArrayList<TrainingHistory> trainingHistory) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.trainingHistory = trainingHistory;
	}
	
	public Trainer(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.trainingHistory = null;
	}

	public ArrayList<TrainingHistory> getTrainingHistory() {
		return trainingHistory;
	}

	public void setTrainingHistory(ArrayList<TrainingHistory> trainingHistory) {
		this.trainingHistory = trainingHistory;
	}
	
	
}
