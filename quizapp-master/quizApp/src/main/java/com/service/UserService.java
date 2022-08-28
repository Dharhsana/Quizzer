package com.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.Token;
import com.model.UserModel;

public interface UserService<T,K> {

	public boolean save(T model);
	public boolean update(T model);
	public T getOneById(long id);
	public T getOneByUEId(String ueid);
	public T getOneByName(String name);
	public boolean remove(long id);	
	public boolean updateOTP(String email);
	public K login(String email,String password);
	public K loginWithOTP(String email,String otp);
	public boolean updateLastLogging(long id);
	public String logout(Token token);
	public List<T> getAll();
}
