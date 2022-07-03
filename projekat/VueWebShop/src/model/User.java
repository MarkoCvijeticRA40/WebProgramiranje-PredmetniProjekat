package model;

import java.time.LocalDateTime;
import java.util.Date;

public class User {
	
	private String id;
	public String username;
	private String password;
	public String name;
	public String lastName;
	private Gender gender;
	private Date dateOfBirth;
	
	public User(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth) {
		super();
		this.id = LocalDateTime.now().toString();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	
	public User(String id, String username, String password, String name, String lastName, Gender gender, Date dateOfBirth) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	
	public User(String username, String password, String name, String lastName, Gender gender) {
		super();
		this.id = LocalDateTime.now().toString();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = null;
	}
	
	
	public User(String username, String password, String name, String lastName) {
		super();
		this.id = LocalDateTime.now().toString();
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.gender = gender.Male;
		this.dateOfBirth = null;
	}
	
	public User() {
		super();
		this.id = LocalDateTime.now().toString();
		this.username = null;
		this.password = null;
		this.name = null;
		this.lastName = null;
		this.gender = null;
		this.dateOfBirth = null;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public String getId() {
		return id;
	}
	
	
	

}
