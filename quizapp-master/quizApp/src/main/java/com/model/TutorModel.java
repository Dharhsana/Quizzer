package com.model;

import java.util.List;

public class TutorModel extends BaseModel{

	private long id;
	private String name;
	private String pro_pic;
	private List<ClassRoomModel> classRooms;
	private List<QuestionModel> questions;
	
	
	public TutorModel() {
		super();
	}


	public TutorModel(long id, String name, String pro_pic, List<ClassRoomModel> classRooms,
			List<QuestionModel> questions) {
		super();
		this.id = id;
		this.name = name;
		this.pro_pic = pro_pic;
		this.classRooms = classRooms;
		this.questions = questions;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getProPic() {
		return pro_pic;
	}


	public void setProPic(String pro_pic) {
		this.pro_pic = pro_pic;
	}


	public List<ClassRoomModel> getClassRooms() {
		return classRooms;
	}


	public void setClassRooms(List<ClassRoomModel> classRooms) {
		this.classRooms = classRooms;
	}


	public List<QuestionModel> getQuestions() {
		return questions;
	}


	public void setQuestions(List<QuestionModel> questions) {
		this.questions = questions;
	}


	@Override
	public String toString() {
		return "TutorModel [id=" + id + ", name=" + name + ", pro_pic=" + pro_pic + ", classRooms=" + classRooms
				+ ", questions=" + questions + "]";
	}
	
	
	
}
