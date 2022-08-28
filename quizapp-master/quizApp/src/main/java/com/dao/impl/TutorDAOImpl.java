package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.TutorDAO;
import com.model.StudentModel;
import com.model.TutorModel;

@Repository
public class TutorDAOImpl implements TutorDAO<TutorModel>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean save(long id,String ueid,String name) {
		StringBuilder sql = new StringBuilder();		
		sql.append("INSERT INTO tutor(id,ueid,name,profile_pic) values(?,?,?,?)");
		return jdbcTemplate.update(sql.toString(), id,ueid,name,null)>0;
	}

	@Override
	public boolean update(TutorModel model) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE tutor set name = ? where id =?");
		return jdbcTemplate.update(sql.toString(),model.getName(),model.getId())>0;
	}

	@Override
	public TutorModel getOneById(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tutor.id as id,tutor.ueid as ueid,tutor.name as name,tutor.profile_pic as profile_pic from tutor where tutor.id = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {id}
				,this::mapRowToModel);
	}

	@Override
	public TutorModel getOneByUEId(String ueid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tutor.id as id,tutor.ueid as ueid,tutor.name as name,tutor.profile_pic as profile_pic from tutor where tutor.ueid = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {ueid}
				,this::mapRowToModel);
	}

	@Override
	public TutorModel getOneByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tutor.id as id,tutor.ueid as ueid,tutor.name as name,tutor.profile_pic as profile_pic from tutor where tutor.name = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {name}
				,this::mapRowToModel);
	}

	@Override
	public List<TutorModel> getAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tutor.id as id,tutor.ueid as ueid,tutor.name as name,tutor.profile_pic as profile_pic from tutor");
		return jdbcTemplate.queryForObject(sql.toString()
				,this::mapRowsToModel);
	}
	
	@Override
	public boolean remove(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM tutor where id = ?");
		return jdbcTemplate.update(sql.toString(),id)>0;
	}

	
	private TutorModel mapRowToModel(ResultSet rs,int numRows) throws SQLException {
		
		TutorModel tutorModel = new TutorModel();
		
		tutorModel.setId(rs.getLong("id"));
		tutorModel.setUeid(rs.getString("ueid"));
		tutorModel.setName(rs.getString("name"));
		tutorModel.setProPic(rs.getString("profile_pic"));
		
		
		
		return tutorModel;
	}
	
	
	private List<TutorModel> mapRowsToModel(ResultSet rs,int numRows) throws SQLException {
		
		TutorModel tutorModel = new TutorModel();
		List<TutorModel> tutors = new ArrayList<TutorModel>();
		
		int i = 0;
		for(i=0;i<numRows;i++) {
			
			tutorModel.setId(rs.getLong("id"));
			tutorModel.setUeid(rs.getString("ueid"));
			tutorModel.setName(rs.getString("name"));
			tutorModel.setProPic(rs.getString("profile_pic"));
			
			tutors.add(tutorModel);
		}

		return tutors;
	}
}
