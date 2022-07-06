package dto;

public class BuyMembership {

	public String customerId;
	public String membershipId;
	
	public BuyMembership() {}
	
	public BuyMembership(String customerId, String membershipId) {
		super();
		this.customerId = customerId;
		this.membershipId = membershipId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}
}