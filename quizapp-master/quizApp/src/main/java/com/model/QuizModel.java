package com.model;

import java.util.List;

public class QuizModel extends BaseModel{

	private long id;
	private String name;
	private String time;
	private int number_of_question;
	private String description;
	private double result;
	private String grade;
	private int attempt;
	private List<ClassRoomModel> classRooms;
	private List<QuestionModel> questionList;
	private List<StudentModel> studentList;
	
	public QuizModel() {
		super();
	}

	public QuizModel(long id, String name, String time, int number_of_question, String description, double result,
			String grade,int attempt, List<ClassRoomModel> classRooms, List<QuestionModel> questionList,
			List<StudentModel> studentList) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.number_of_question = number_of_question;
		this.description = description;
		this.result = result;
		this.grade = grade;
		this.classRooms = classRooms;
		this.questionList = questionList;
		this.studentList = studentList;
		this.attempt = attempt;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getNumberOfQuestion() {
		return number_of_question;
	}

	public void setNumberOfQuestion(int number_of_question) {
		this.number_of_question = number_of_question;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public int getAttempt() {
		return attempt;
	}

	public void setAttempt(int attempt) {
		this.attempt = attempt;
	}
	
	public List<ClassRoomModel> getClassRooms() {
		return classRooms;
	}

	public void setClassRooms(List<ClassRoomModel> classRooms) {
		this.classRooms = classRooms;
	}

	public List<QuestionModel> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<QuestionModel> questionList) {
		this.questionList = questionList;
	}

	public List<StudentModel> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentModel> studentList) {
		this.studentList = studentList;
	}

	@Override
	public String toString() {
		return "QuizModel [id=" + id + ", name=" + name + ", time=" + time + ", number_of_question="
				+ number_of_question + ", description=" + description + ", result=" + result + ", grade=" + grade
				+ ", classRooms=" + classRooms + ", questionList=" + questionList + ", studentList=" + studentList
				+ "]";
	}

	
	
	
	
}
