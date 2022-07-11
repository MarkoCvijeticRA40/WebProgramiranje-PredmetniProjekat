package model;

public class CustomerType {
	
	public String name;
	private double discount;
	private int requiredPoints;
	
	public CustomerType(String name, double discount, int requiredPoints) {
		super();
		this.name = name;
		this.discount = discount;
		this.requiredPoints = requiredPoints;
	}

	public CustomerType(String name) {
		super();
		this.name = name;
	}
	
	public CustomerType() {}
	
	public String getName() {
		return name;
	}

	public void setName(Customer customer) {
		
		double points = customer.getPoints();
		
		if(points < 3000) {
			customer.customerType.seterPomocna("Bronze");
		}
		else if(points > 3000.00 && points < 10000.00 ) {
			customer.customerType.seterPomocna("Silver");
		}
		else 
		{
			customer.customerType.seterPomocna("Gold");
		}
	}
	
	public void seterPomocna(String name) {
		this.name = name;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public int getRequiredPoints() {
		return requiredPoints;
	}

	public void setRequiredPoints(int requiredPoints) {
		this.requiredPoints = requiredPoints;
	}
	
	
	
	

}
