package model;

import java.util.Date;

public class TrainingHistory {

	private Date applicationDate;
	private Training training;
	private Customer customer;
	private Trainer trainer;
	
	public TrainingHistory(Date applicationDate, Training training, Customer customer, Trainer trainer) {
		super();
		this.applicationDate = applicationDate;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
	}

	public Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(Date applicationDate) {
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
}