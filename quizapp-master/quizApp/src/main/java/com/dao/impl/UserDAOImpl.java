package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.UserDAO;
import com.enums.Role;
import com.model.ClassRoomModel;
import com.model.StudentModel;
import com.model.UserModel;

@Repository
public class UserDAOImpl implements UserDAO <UserModel>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	StudentDAOImpl studentDAOImpl;
	
	@Autowired
	TutorDAOImpl tutorDAOImpl;
	
	@Override
	public boolean save(UserModel model) {
		StringBuilder sql = new StringBuilder();
		int maxId = 0;
		sql.append("SELECT MAX(id) FROM user");
		Integer previousId = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		
		if(previousId==null) {
			previousId = 0;
		}
		maxId = previousId+1;
		
		sql.setLength(0);
		sql.append("INSERT INTO user(id,ueid,name,password,lastlogging,email,otp,role) values(?,?,?,?,?,?,?,?)");
		if(model.getRole().equalsIgnoreCase(Role.STUDENT.getRole()))
		{
			return jdbcTemplate.update(sql.toString(), maxId,model.getUeid(),model.getName(),model.getPassword(),model.getLastLogging(),model.getEmail(),model.getOtp(),Role.STUDENT.getRole())>0 && studentDAOImpl.save(maxId, model.getUeid(), model.getName());
		}
		else if(model.getRole().equalsIgnoreCase(Role.TUTOR.getRole())) {
			return jdbcTemplate.update(sql.toString(), maxId,model.getUeid(),model.getName(),model.getPassword(),model.getLastLogging(),model.getEmail(),model.getOtp(),Role.TUTOR.getRole())>0 && tutorDAOImpl.save(maxId, model.getUeid(), model.getName());
		}
		else if(model.getRole().equalsIgnoreCase(Role.ADMIN.getRole())){
			return jdbcTemplate.update(sql.toString(), maxId,model.getUeid(),model.getName(),model.getPassword(),model.getLastLogging(),model.getEmail(),model.getOtp(),Role.ADMIN.getRole())>0;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean update(UserModel model) {
		StringBuilder sql = new StringBuilder();
		StudentModel student = new StudentModel(model.getId(),model.getName(),model.getUeid(),null,null,0,0,null);
		
			sql.append("UPDATE user SET name=?,password=?,email=? where id = ?");
			
			return jdbcTemplate.update(sql.toString(), model.getName(),model.getPassword(),model.getEmail(),model.getId())>0 && studentDAOImpl.update(student);
		
		
	}

	@Override
	public UserModel getOneById(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user.id as id,user.ueid as ueid,user.name as name,user.password as password,user.email as email,user.lastlogging as lastLogging from user where user.id = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {id}
				,this::mapRowToModel);
	}

	@Override
	public UserModel getOneByUEId(String ueid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user.id as id,user.ueid as ueid,user.name as name,user.password as password,user.email as email,user.lastlogging as lastLogging from user where user.ueid = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {ueid}
				,this::mapRowToModel);
	}

	@Override
	public UserModel getOneByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user.id as id,user.ueid as ueid,user.name as name,user.password as password,user.email as email,user.lastlogging as lastLogging from user where user.name = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {name}
				,this::mapRowToModel);
	}
	
	@Override
	public List<UserModel> getAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT user.id as id,user.ueid as ueid,user.name as name,user.password as password,user.email as email,user.lastlogging as lastLogging from user");
		return jdbcTemplate.queryForObject(sql.toString()
				,this::mapRowsToModel);
	}
	
	@Override
	public boolean remove(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM user where id = ?");
		return jdbcTemplate.update(sql.toString(),id)>0;
	}
	
	
	private UserModel mapRowToModel(ResultSet rs,int numRows) throws SQLException {
		
		UserModel userModel = new UserModel();
		
		userModel.setId(rs.getLong("id"));
		userModel.setUeid(rs.getString("ueid"));
		userModel.setName(rs.getString("name"));
		userModel.setPassword(rs.getString("password"));
		userModel.setEmail(rs.getString("email"));
		userModel.setLastLogging(rs.getDate("lastLogging"));
		
		
		return userModel;
	}
	
	
	private List<UserModel> mapRowsToModel(ResultSet rs,int numRows) throws SQLException {
		
		UserModel userModel = new UserModel();
		List<UserModel> users = new ArrayList<UserModel>();
		
		int i = 0;
		
		for(i=0;i<numRows;i++) {
			
		}
		while(rs.next()) {
			userModel.setId(rs.getLong("id"));
			userModel.setUeid(rs.getString("ueid"));
			userModel.setName(rs.getString("name"));
			userModel.setPassword(rs.getString("password"));
			userModel.setEmail(rs.getString("email"));
			userModel.setLastLogging(rs.getDate("lastLogging"));
			users.add(userModel);
		}
		
		
		
		
		return users;
	}

	@Override
	public boolean updateOTP(String otp, String email) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user SET otp=? where email = ?");

		return jdbcTemplate.update(sql.toString(), otp,email)>0;
	}

	@Override
	public UserModel login(String email, String password) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT user.id as id,user.ueid as ueid,user.name as name,user.password as password,user.email as email,user.lastlogging as lastLogging from user where user.email = ? and user.password = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {email,password}
				,this::mapRowToModel);
	}

	@Override
	public UserModel loginWithOTP(String email, String otp) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT user.id as id,user.ueid as ueid,user.name as name,user.password as password,user.email as email,user.lastlogging as lastLogging from user where user.email = ? and user.otp = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {email,otp}
				,this::mapRowToModel);
	}

	@Override
	public boolean updateLastLogging(long id,String lastLogged) {
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user SET lastlogging=? where id = ?");

		return jdbcTemplate.update(sql.toString(), lastLogged,id)>0;
	}

}
