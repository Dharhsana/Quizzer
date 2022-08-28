package com.serviceImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDAO;
import com.model.Token;
import com.model.UserModel;
import com.service.UserService;
import com.utility.Authentication;
import com.utility.Utility;

@Service
public class UserServiceImpl implements UserService<UserModel,Token>{

	@Autowired
	private UserDAO<UserModel> userDAO;
	
	@Override
	public boolean save(UserModel model) {
		
		String password = Utility.getPassword(model.getPassword());
		model.setPassword(password);
		model.setUeid(Utility.getUEID("USER"));
		return userDAO.save(model);
	}

	@Override
	public boolean update(UserModel model) {
		String password = Utility.getPassword(model.getPassword());
		model.setPassword(password);
		return userDAO.update(model);
	}

	@Override
	public UserModel getOneById(long id) {
		
		return userDAO.getOneById(id);
	}

	@Override
	public UserModel getOneByUEId(String ueid) {
		
		return userDAO.getOneByUEId(ueid);
	}

	@Override
	public UserModel getOneByName(String name) {
		
		return userDAO.getOneByName(name);
	}

	@Override
	public boolean remove(long id) {
		
		return userDAO.remove(id);
	}

	@Override
	public boolean updateOTP(String email) {
		
		return userDAO.updateOTP(Utility.getOtp(), email);
	}

	@Override
	public Token login(String email, String password) {
		String password_hash = Utility.getPassword(password);
		
		UserModel user =  userDAO.login(email, password_hash);
		Token token = null;
		if(user!=null) {
			token = Authentication.createToken(user.getName(), user.getRole());
		}
		
		
		return token;
	}

	@Override
	public Token loginWithOTP(String email, String otp) {
		
		
		
		UserModel user =  userDAO.loginWithOTP(email, otp);
		Token token = null;
		if(user!=null) {
			token = Authentication.createToken(user.getName(), user.getRole());
		}
		
		
		return token;
		
	}

	@Override
	public boolean updateLastLogging(long id) {
		
		Date d = new Date();
		String lastLogged = Utility.getDateTime(d);
		return userDAO.updateLastLogging(id,lastLogged);
	}

	@Override
	public List<UserModel> getAll() {
		
		return userDAO.getAll();
	}

	@Override
	public String logout(Token token) {
		
		return Authentication.logout(token)==true?"Logged Out":"Not logged in";
	}

	
}
