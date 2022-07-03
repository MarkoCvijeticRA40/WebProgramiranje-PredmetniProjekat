package model;

public class Address {

	public String addressName;	// street and number
	private String city;
	private String postalCode;
	
	public Address(String addressName, String city, String postalCodel) {
		super();
		this.addressName = addressName;
		this.city = city;
		this.postalCode = postalCodel;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return addressName + ", " + city;
	}
	
	
	
	
}
