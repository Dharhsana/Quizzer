package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.UserModel;

public interface UserDAO <T>{

	public boolean save(T model);
	public boolean update(T model);
	public T getOneById(long id);
	public T getOneByUEId(String ueid);
	public T getOneByName(String name);
	public boolean remove(long id);
	public boolean updateOTP(String otp,String email);
	public T login(String email,String password);
	public T loginWithOTP(String email,String otp);
	public boolean updateLastLogging(long id,String lastLogged);
	List<T> getAll();

 
}
