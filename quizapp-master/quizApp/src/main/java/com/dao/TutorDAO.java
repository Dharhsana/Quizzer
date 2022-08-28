package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.TutorModel;

public interface TutorDAO <T>{

	public boolean save(long id, String ueid, String name);
	public boolean update(T model);
	public T getOneById(long id);
	public T getOneByUEId(String ueid);
	public T getOneByName(String name);
	public boolean remove(long id);
	public List<T> getAll();
}
