package model;

public class Comment {
	private String id;
	private Customer customer;
	private SportObject sportObject;
	private String text;
	private int grade;
	private boolean isAproved;
	
	public Comment(String id, Customer customer, SportObject sportObject, String text, int grade, boolean isAproved) {
		super();
		this.id = id;
		this.customer = customer;
		this.sportObject = sportObject;
		this.text = text;
		this.grade = grade;
		this.isAproved = isAproved;
	}
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public SportObject getSportObject() {
		return sportObject;
	}

	public void setSportObject(SportObject sportObject) {
		this.sportObject = sportObject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}



	public boolean isAproved() {
		return isAproved;
	}


	public void setAproved(boolean isAproved) {
		this.isAproved = isAproved;
	}
	
	
	
}
