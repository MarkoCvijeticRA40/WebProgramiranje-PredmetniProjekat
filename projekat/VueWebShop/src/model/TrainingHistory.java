package model;

import java.time.LocalDateTime;

public class TrainingHistory {

	private LocalDateTime applicationDate;
	private Training training;
	private Customer customer;
	
	public TrainingHistory(LocalDateTime applicationDate, Training training, Customer customer) {
		super();
		this.applicationDate = applicationDate;
		this.training = training;
		this.customer = customer;
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

}