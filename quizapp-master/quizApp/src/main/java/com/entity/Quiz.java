package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="quiz")
public class Quiz {

	//id, name, time, description, number_of_questions, classroom_id
	
	@Id
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="time")
	private String time;
	
	@Column(name="description")
	private String lastLogging;
	
	@Column(name="number_of_questions")
	private int number_of_questions;
	
	@ManyToOne
	@JoinColumn(name="classroom_id",nullable = false)
	private ClassRoom classroom;

	public Quiz() {
		super();
	}

	public Quiz(long id, String name, String time, String lastLogging, int number_of_questions, 
			ClassRoom classroom) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
		this.lastLogging = lastLogging;
		this.number_of_questions = number_of_questions;
	
		this.classroom = classroom;
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

	public String getLastLogging() {
		return lastLogging;
	}

	public void setLastLogging(String lastLogging) {
		this.lastLogging = lastLogging;
	}

	public int getNumber_of_questions() {
		return number_of_questions;
	}

	public void setNumber_of_questions(int number_of_questions) {
		this.number_of_questions = number_of_questions;
	}

	

	public ClassRoom getClassroom_id() {
		return classroom;
	}

	public void setClassroom_id(ClassRoom classroom) {
		this.classroom = classroom;
	}

	@Override
	public String toString() {
		return "Quiz [id=" + id + ", name=" + name + ", time=" + time + ", lastLogging=" + lastLogging
				+ ", number_of_questions=" + number_of_questions + ", classroom=" + classroom + "]";
	}

	
	

}
