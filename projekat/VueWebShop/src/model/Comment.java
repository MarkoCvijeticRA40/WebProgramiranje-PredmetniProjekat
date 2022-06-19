package model;



public class Comment {
	private String id;
	private CustomerType customer;
	private SportObject sportObject;
	private String text;
	private int grade;
	
	public Comment(String id, CustomerType customer, SportObject sportObject, String text, int grade) {
		super();
		this.id = id;
		this.customer = customer;
		this.sportObject = sportObject;
		this.text = text;
		this.grade = grade;
	}
	
	

	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public CustomerType getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerType customer) {
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
	
	
	
}
