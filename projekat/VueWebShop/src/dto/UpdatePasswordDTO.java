package dto;

public class UpdatePasswordDTO {

	private String id;
	private String password;
	
	public UpdatePasswordDTO() {}

	public UpdatePasswordDTO(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String customerId) {
		this.id = customerId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
