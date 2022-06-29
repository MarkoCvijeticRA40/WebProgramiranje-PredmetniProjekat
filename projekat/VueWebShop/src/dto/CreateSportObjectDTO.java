package dto;

public class CreateSportObjectDTO {

	private String name;
	private String type;
	private String startTime;
	private String endTime;
	private String streetAndNumber;
	private String city;
	private String postalCode;
	private double longitude;
	private double latitude;
	private String image;
	
	public CreateSportObjectDTO() {}

	public CreateSportObjectDTO(String name, String type, String startTime, String endTime, String streetAndNumber,
			String city, String postalCode, double longitude, double latitude, String image) {
		super();
		this.name = name;
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.streetAndNumber = streetAndNumber;
		this.city = city;
		this.postalCode = postalCode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStreetAndNumber() {
		return streetAndNumber;
	}

	public void setStreetAndNumber(String streetAndNumber) {
		this.streetAndNumber = streetAndNumber;
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

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
