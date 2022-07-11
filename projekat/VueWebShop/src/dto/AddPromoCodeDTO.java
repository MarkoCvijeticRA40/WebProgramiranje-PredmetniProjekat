package dto;

public class AddPromoCodeDTO {

	private String startDate;
	private String endDate;
	private int quantity;
	private double discountPercentage;
	
	public AddPromoCodeDTO() {}

	public AddPromoCodeDTO(String startDate, String endDate, int quantity, double discountPercentage) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.quantity = quantity;
		this.discountPercentage = discountPercentage;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscountPercentage() {
		return discountPercentage;
	}

	public void setDiscountPercentage(double discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	
	
}
