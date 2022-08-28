package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {

	//id, name, password, lastlogging, otp, email,  role
	@Id
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="lastlogging")
	private Date lastLogging;
	
	@Column(name="otp")
	private String otp;
	
	@Column(name="email")
	private String email;
	
	@Column(name="role")
	private String role;

	public User() {
		super();
		
	}

	public User(long id, String name, String password, Date lastLogging, String otp, String email, String role) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.lastLogging = lastLogging;
		this.otp = otp;
		this.email = email;
		this.role = role;
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

	public Date getLastLogging() {
		return lastLogging;
	}

	public void setLastLogging(Date lastLogging) {
		this.lastLogging = lastLogging;
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

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", lastLogging=" + lastLogging
				+ ", otp=" + otp + ", email=" + email + ", role=" + role + "]";
	}
	
	
}
