package dto;

public class WelcomeCustomerDTO {

	private String training;
	private String customer;
	
	public WelcomeCustomerDTO() {}

	public WelcomeCustomerDTO(String training, String customer) {
		super();
		this.training = training;
		this.customer = customer;
	}

	public String getTraining() {
		return training;
	}

	public void setTraining(String training) {
		this.training = training;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
}
