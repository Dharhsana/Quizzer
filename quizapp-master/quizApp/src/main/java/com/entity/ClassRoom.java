package com.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "classroom")
public class ClassRoom {

	@Id
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="created_date")
	private Date created_date;

	@OneToMany(mappedBy="classroom")
	private Collection<Quiz> quizzList = new ArrayList<Quiz>();
	
	

	public ClassRoom() {
		super();
	}

	public ClassRoom(long id, String name, Date created_date) {
		super();
		this.id = id;
		this.name = name;
		this.created_date = created_date;
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

	public Date getCreated_date() {
		return created_date;
	}

	public void setCreated_date(Date created_date) {
		this.created_date = created_date;
	}
	
	
	public Collection<Quiz> getQuizzList() {
		return quizzList;
	}

	public void setQuizzList(Collection<Quiz> quizzList) {
		this.quizzList = quizzList;
	}

	@Override
	public String toString() {
		return "ClassRoom [id=" + id + ", name=" + name + ", created_date=" + created_date + "]";
	}
	
	
}
