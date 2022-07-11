package dto;

import java.util.Date;

import model.CustomerType;
import model.Gender;

public class CustomerDTO {
	
	public String id;
	public String username;
	public String name;
	public String lastName;
	private Gender gender;
	private Date dateOfBirth;
	public double points;
	public CustomerType customerType;
	
	public CustomerDTO() {}

	public CustomerType getCustomerType() {
		return customerType;
	}
	
	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
	
	public CustomerDTO(String id) {
		super();
		this.id = id;
	}
	
	public CustomerDTO(String username, String name, String lastName, Gender gender, Date dateOfBirth, double points,
			CustomerType customerType) {
		super();
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.points = points;
		this.customerType = customerType;
	}
	
	public CustomerDTO(String username, String name, String lastName, Gender gender, Date dateOfBirth,
			double points) {
		super();
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.points = points;
	}

	public CustomerDTO(String username, String name, String lastName, Gender gender, Date dateOfBirth) {
		super();
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	
	public CustomerDTO(String id, String username, String name, String lastName, Gender gender, Date dateOfBirth) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
}