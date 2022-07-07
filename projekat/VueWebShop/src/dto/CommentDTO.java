package dto;

public class CommentDTO {

	private String comment;
	private int grade;
	private String sportObjectId;
	private String customerId;
	
	public CommentDTO() {}

	public CommentDTO(String comment, int grade, String sportObjectId, String customerId) {
		super();
		this.comment = comment;
		this.grade = grade;
		this.sportObjectId = sportObjectId;
		this.customerId = customerId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getSportObjectId() {
		return sportObjectId;
	}

	public void setSportObjectId(String sportObjectId) {
		this.sportObjectId = sportObjectId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	
	
}
