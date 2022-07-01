package model;

import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {

	private Membership membership;
	private ArrayList<SportObject> visitedObjects;
	private int points;
	private CustomerType customerType;
	
	
	public Customer(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth,
			Membership membership, ArrayList<SportObject> visitedObjects, int points, CustomerType customerType) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.membership = membership;
		this.visitedObjects = visitedObjects;
		this.points = points;
		this.customerType = customerType;
	}
	
	
	public Customer(String username, String password, String name, String lastName, Gender gender) {
		super(username, password, name, lastName, gender);
		this.membership = null;
		this.visitedObjects = null;
		this.points = 0;
		this.customerType = null;
	}
	
	public Customer(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.membership = null;
		this.visitedObjects = null;
		this.points = 0;
		this.customerType = null;
	}
	
	public Customer(String username, String password, String name, String lastName, Gender gender, Date dateOfBirth, ArrayList<SportObject> visitedObjects) {
		super(username, password, name, lastName, gender, dateOfBirth);
		this.membership = null;
		this.visitedObjects = visitedObjects;
		this.points = 0;
		this.customerType = null;
	}
	
	
	public Customer(String username, String password, String name, String lastName) {
		super(username, password, name, lastName);
		this.membership = null;
		this.visitedObjects = null;
		this.points = 0;
		this.customerType = null;
	}

	public Customer() {
		super();
		//this.membership = null;
		//this.visitedObjects = null;
		this.points = 0;
		//this.customerType = null;
	}



	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public ArrayList<SportObject> getVisitedObjects() {
		return visitedObjects;
	}

	public void setVisitedObjects(ArrayList<SportObject> visitedObjects) {
		this.visitedObjects = visitedObjects;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	
	
	
	
}
