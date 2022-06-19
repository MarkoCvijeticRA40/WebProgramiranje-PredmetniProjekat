package model;

import java.util.Date;

public class Manager extends User {
	
	private SportObject sportObject;

	public Manager() {
		
	}
	
	public Manager(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			SportObject sportObject) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.sportObject = sportObject;
	}
	
	public Manager(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.sportObject = null;
	}

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}
	
	

}
