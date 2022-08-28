package com.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class Student {

	//id, profile_pic, name
	
	@Id
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="profile_pic")
	private String profile_pic;

	public Student() {
		super();
		
	}

	public Student(long id, String name, String profile_pic) {
		super();
		this.id = id;
		this.name = name;
		this.profile_pic = profile_pic;
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

	public String getProfile_pic() {
		return profile_pic;
	}

	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", profile_pic=" + profile_pic + "]";
	}
	
	
	
	

}
