package model;

import org.eclipse.jdt.internal.compiler.codegen.DoubleCache;

public class Location {

	private Double longitude;
	private Double latitude;
	public Address address;
	
	public Location(Double longitude, Double latitude, Address address) {
		super();
		this.longitude = longitude;
		this.latitude = latitude;
		this.address = address;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return address.toString();
	}
}