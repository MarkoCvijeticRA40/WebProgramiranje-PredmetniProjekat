package dto;

import java.util.ArrayList;

import model.Content;
import model.Location;
import model.SportObject;
import model.SportObjectStatus;
import model.WorkTime;

public class SportObjectViewDTO {

	public String id;
	public String name;
	public String type;
	public ArrayList<Content> content;
	public Location location;
	public Double averageGrade;
	public String image;
	public WorkTime workTime;
	public SportObjectStatus status;
	
	public SportObjectViewDTO(SportObject s) {
		super();
		this.id = s.getId();
		this.name = s.getName();
		this.type = s.getType();
		this.content = s.getContent();
		this.location = s.getLocation();
		this.averageGrade = s.getAverageGrade();
		this.image = s.getImage();
		this.workTime = s.getWorkTime();
	}	
}