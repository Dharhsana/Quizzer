package com.model;

import java.util.List;

public class StudentModel extends BaseModel{
	
	private long id;
	private String name;
	private String pro_pic;
	private List<ClassRoomModel> classRooms;
	private List<QuizModel> quizzes;
	private int attempt;
	private double result;
	private String grade;
	
	public StudentModel() {
		super();
	}

	public StudentModel(long id, String name, String pro_pic, List<ClassRoomModel> classRooms, List<QuizModel> quizzes,
			int attempt, double result, String grade) {
		super();
		this.id = id;
		this.name = name;
		this.pro_pic = pro_pic;
		this.classRooms = classRooms;
		this.quizzes = quizzes;
		this.attempt = attempt;
		this.result = result;
		this.grade = grade;
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

	public List<QuizModel> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<QuizModel> quizzes) {
		this.quizzes = quizzes;
	}

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "StudentModel [id=" + id + ", name=" + name + ", pro_pic=" + pro_pic + ", classRooms=" + classRooms
				+ ", quizzes=" + quizzes + ", attempt=" + attempt + ", result=" + result + ", grade=" + grade + "]";
	}

	
	
	

}
