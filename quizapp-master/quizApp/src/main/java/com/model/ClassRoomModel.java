package com.model;

import java.util.Date;
import java.util.List;

public class ClassRoomModel extends BaseModel{
	
	private long id;
	private String name;
	private Date create_date;
	private List<QuizModel> quizList;
	private List<StudentModel> studentList;
	private TutorModel tutor;
	
	public ClassRoomModel() {
		super();
	}
	
	public ClassRoomModel(long id, String name, Date create_date, List<QuizModel> quizList, List<StudentModel> studentList,
			TutorModel tutor) {
		super();
		this.id = id;
		this.name = name;
		this.create_date = create_date;
		this.quizList = quizList;
		this.studentList = studentList;
		this.tutor = tutor;
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

	public Date getCreateDate() {
		return create_date;
	}

	public void setCreateDate(Date create_date) {
		this.create_date = create_date;
	}

	public List<QuizModel> getQuizList() {
		return quizList;
	}

	public void setQuizList(List<QuizModel> quizList) {
		this.quizList = quizList;
	}

	public List<StudentModel> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentModel> studentList) {
		this.studentList = studentList;
	}

	public TutorModel getTutor() {
		return tutor;
	}

	public void setTutor(TutorModel tutor) {
		this.tutor = tutor;
	}

	@Override
	public String toString() {
		return "ClassRoom [id=" + id + ", name=" + name + ", create_date=" + create_date + ", quizList=" + quizList
				+ ", studentList=" + studentList + ", tutor=" + tutor + "]";
	}

	
	
	
	

}
