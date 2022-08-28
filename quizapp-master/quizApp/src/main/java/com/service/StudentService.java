package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.StudentModel;

public interface StudentService<T> {

	
	public boolean update(T model);
	public T getStudent(long id);
	public T getOneById(long id);
	public T getOneByUEId(String ueid);
	public T getOneByName(String name);
	public boolean remove(long id);
	public List<T> getAllStudents();


	 
}
