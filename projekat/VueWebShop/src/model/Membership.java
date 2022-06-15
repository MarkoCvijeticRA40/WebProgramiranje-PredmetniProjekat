package model;

import java.text.DateFormat;
import java.util.Date;

public class Membership {
	
	//private String id;
	private TypeMembership typeMembership;
	private Date dayPaying;
	private double value;
	private MembershipValidation membershipValidation;
	private MembershipStatus membershipStatus;
	private int numberOfTerms;
	//kupac
	
	public Membership(TypeMembership typeMembership, Date dayPaying, double value,
			MembershipValidation membershipValidation, MembershipStatus membershipStatus, int numberOfTerms) {
		super();
		this.typeMembership = typeMembership;
		this.dayPaying = dayPaying;
		this.value = value;
		this.membershipValidation = membershipValidation;
		this.membershipStatus = membershipStatus;
		this.numberOfTerms = numberOfTerms;
	}
	
	public TypeMembership getTypeMembership() {
		return typeMembership;
	}
	public void setTypeMembership(TypeMembership typeMembership) {
		this.typeMembership = typeMembership;
	}
	public Date getDayPaying() {
		return dayPaying;
	}
	public void setDayPaying(Date dayPaying) {
		this.dayPaying = dayPaying;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public MembershipValidation getMembershipValidation() {
		return membershipValidation;
	}
	public void setMembershipValidation(MembershipValidation membershipValidation) {
		this.membershipValidation = membershipValidation;
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