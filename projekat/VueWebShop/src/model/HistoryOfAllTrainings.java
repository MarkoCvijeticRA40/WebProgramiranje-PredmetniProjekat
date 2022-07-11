package model;

import java.time.LocalDateTime;

public class HistoryOfAllTrainings {
	
	private String id;
	private LocalDateTime applicationDate;
	private Training training;
	private Customer customer;
	private Trainer trainer;
	public SportObject sportObject;
	
	public HistoryOfAllTrainings(String id, LocalDateTime applicationDate, Training training, Customer customer, Trainer trainer) {
		super();
		this.id = id;
		this.applicationDate = applicationDate;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}
	
	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime applicationDate) {
		this.applicationDate = applicationDate;
	}

	public Training getTraining() {
		return training;
	}

	public void setTraining(Training training) {
		this.training = training;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Trainer getTrainer() {
		return trainer;
	}

	public void setTrainer(Trainer trainer) {
		this.trainer = trainer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
