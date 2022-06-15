package model;



public class Comment {
	
	private CustomerType customer;
	private SportObject sportObject;
	private String text;
	private int grade;
	
	public Comment(CustomerType customer, SportObject sportObject, String text, int grade) {
		super();
		this.customer = customer;
		this.sportObject = sportObject;
		this.text = text;
		this.grade = grade;
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
