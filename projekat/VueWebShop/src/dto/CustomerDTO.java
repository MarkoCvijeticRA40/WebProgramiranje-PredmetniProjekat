package dto;

import java.util.Date;

import model.Gender;

public class CustomerDTO {
	
	private String username;
	private String name;
	private String lastName;
	private Gender gender;
	private Date dateOfBirth;
	
	public CustomerDTO() {}

	public CustomerDTO(String username, String name, String lastName, Gender gender, Date dateOfBirth) {
		super();
		this.username = username;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
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