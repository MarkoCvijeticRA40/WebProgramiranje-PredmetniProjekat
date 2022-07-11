package dto;

import java.time.LocalDateTime;

import model.Customer;
import model.Trainer;
import model.Training;

public class PersonalTrainingDTO {

	private String id;
	private LocalDateTime applicationDate;
	private Training training;
	private Customer customer;
	private Trainer trainer;
	private String canCancel;
	private String name;
	
	
	public PersonalTrainingDTO() {}
	
	public PersonalTrainingDTO(String id, LocalDateTime applicationDate, Training training, Customer customer,
			Trainer trainer, String canCancel, String name) {
		super();
		this.id = id;
		this.applicationDate = applicationDate;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
		this.canCancel = canCancel;
		this.name = name;
	}

	public PersonalTrainingDTO(String id, LocalDateTime applicationDate, Training training, Customer customer,
			Trainer trainer, String canCancel) {
		super();
		this.id = id;
		this.applicationDate = applicationDate;
		this.training = training;
		this.customer = customer;
		this.trainer = trainer;
		this.canCancel = canCancel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getCanCancel() {
		return canCancel;
	}

	public void setCanCancel(String canCancel) {
		this.canCancel = canCancel;
	}
	
	
	
}
