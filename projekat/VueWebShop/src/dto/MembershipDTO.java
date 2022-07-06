package dto;

import java.time.LocalDate;
import java.util.Date;

import model.Customer;
import model.MembershipStatus;


public class MembershipDTO {

	public String id;
	private String typeMembership;
	private LocalDate dayPaying;
	private double value;
	private LocalDate startValidation;
	private LocalDate endValidation;
	private MembershipStatus membershipStatus;
	private int numberOfTerms;
	
	public MembershipDTO(String id, String typeMembership, LocalDate dayPaying, double value, LocalDate startValidation,
			LocalDate endValidation, MembershipStatus membershipStatus, int numberOfTerms) {
		super();
		this.id = id;
		this.typeMembership = typeMembership;
		this.dayPaying = dayPaying;
		this.value = value;
		this.startValidation = startValidation;
		this.endValidation = endValidation;
		this.membershipStatus = membershipStatus;
		this.numberOfTerms = numberOfTerms;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeMembership() {
		return typeMembership;
	}

	public void setTypeMembership(String typeMembership) {
		this.typeMembership = typeMembership;
	}

	public LocalDate getDayPaying() {
		return dayPaying;
	}

	public void setDayPaying(LocalDate dayPaying) {
		this.dayPaying = dayPaying;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public LocalDate getStartValidation() {
		return startValidation;
	}

	public void setStartValidation(LocalDate startValidation) {
		this.startValidation = startValidation;
	}

	public LocalDate getEndValidation() {
		return endValidation;
	}

	public void setEndValidation(LocalDate endValidation) {
		this.endValidation = endValidation;
	}

	public MembershipStatus getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(MembershipStatus membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public int getNumberOfTerms() {
		return numberOfTerms;
	}

	public void setNumberOfTerms(int numberOfTerms) {
		this.numberOfTerms = numberOfTerms;
	}		
}