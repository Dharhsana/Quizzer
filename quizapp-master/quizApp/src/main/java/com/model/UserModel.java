package com.model;

import java.util.Date;

public class UserModel extends BaseModel{

	private long id;
	private String name;
	private String password;
	private String otp;
	private String email;
	private String role;
	private Date lastLogging;
	private TutorModel tutor;
	private StudentModel student;
	
	public UserModel() {
		super();
	}

	public UserModel(long id, String name, String password, String otp, String email, String role, Date lastLogging,
			TutorModel tutor, StudentModel student) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.otp = otp;
		this.email = email;
		this.role = role;
		this.lastLogging = lastLogging;
		this.tutor = tutor;
		this.student = student;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Date getLastLogging() {
		return lastLogging;
	}

	public void setLastLogging(Date lastLogging) {
		this.lastLogging = lastLogging;
	}

	public TutorModel getTutor() {
		return tutor;
	}

	public void setTutor(TutorModel tutor) {
		this.tutor = tutor;
	}

	public StudentModel getStudent() {
		return student;
	}

	public void setStudent(StudentModel student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", password=" + password + ", otp=" + otp + ", email=" + email
				+ ", role=" + role + ", lastLogging=" + lastLogging + ", tutor=" + tutor + ", student=" + student + "]";
	}
	
	
	
	
	
	

}
