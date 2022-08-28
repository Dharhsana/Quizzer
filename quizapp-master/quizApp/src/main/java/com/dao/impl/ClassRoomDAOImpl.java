package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.ClassRoomDAO;
import com.model.AnswerModel;
import com.model.ClassRoomModel;
import com.model.QuestionModel;
import com.model.TutorModel;

@Repository
public class ClassRoomDAOImpl implements ClassRoomDAO <ClassRoomModel>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean save(ClassRoomModel model) {
		StringBuilder sql = new StringBuilder();
		int maxId = 0;
		sql.append("SELECT MAX(id) FROM classroom");
		
		Integer previousId = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		
		if(previousId==null) {
			previousId = 0;
		}
		maxId = previousId+1;
		
		sql.setLength(0);
		sql.append("INSERT INTO classroom(id,ueid,name,created_date,tutor_id) values(?,?,?,?)");
		return jdbcTemplate.update(sql.toString(), maxId,model.getUeid(),model.getName(),model.getCreateDate(),model.getTutor().getId())>0;
	}

	@Override
	public boolean update(ClassRoomModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE classroom SET name=?,created_date=? where id = ?");

		return jdbcTemplate.update(sql.toString(), model.getName(),model.getCreateDate(),model.getId())>0;
	}
	
	@Override
	public ClassRoomModel getOneById(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classroom.id as id,classroom.ueid as ueid,classroom.name as name,classroom.created_date as created_date,classroom.tutor_id as tutor_id from classroom where classroom.id = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {id}
				,this::mapRowToModel);
	}

	@Override
	public ClassRoomModel getOneByUEId(String ueid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classroom.id as id,classroom.ueid as ueid,classroom.name as name,classroom.created_date as created_date,classroom.tutor_id as tutor_id from classroom where classroom.ueid = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {ueid}
		,this::mapRowToModel);
	}

	@Override
	public ClassRoomModel getOneByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classroom.id as id,classroom.ueid as ueid,classroom.name as name,classroom.created_date as created_date,classroom.tutor_id as tutor_id from classroom where classroom.name = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {name}
		,this::mapRowToModel);
	}

	@Override
	public List<ClassRoomModel> getAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT classroom.id as id,classroom.ueid as ueid,classroom.name as name,classroom.created_date as created_date,classroom.tutor_id as tutor_id from classroom");
		return jdbcTemplate.queryForObject(sql.toString()
				,this::mapRowsToModel);
	}
	
	@Override
	public boolean remove(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM classroom where id = ?");
		return jdbcTemplate.update(sql.toString(),id)>0;
	}

	
	private ClassRoomModel mapRowToModel(ResultSet rs,int numRows) throws SQLException {
		
		ClassRoomModel classRoomModel = new ClassRoomModel();
		TutorModel tutor = new TutorModel();
		
		classRoomModel.setId(rs.getLong("id"));
		classRoomModel.setUeid(rs.getString("ueid"));
		classRoomModel.setName(rs.getString("name"));
		classRoomModel.setCreateDate((rs.getDate("created_date")));
		tutor.setId(rs.getLong("tutor_id"));
		classRoomModel.setTutor(tutor);
		
		return classRoomModel;
	}

	
	private List<ClassRoomModel> mapRowsToModel(ResultSet rs,int numRows) throws SQLException {
		
		ClassRoomModel classRoomModel = new ClassRoomModel();
		List<ClassRoomModel> classRooms = new ArrayList<ClassRoomModel>();
		TutorModel tutor = new TutorModel();

		int i = 0;
		for(i=0;i<numRows;i++) {
			
			classRoomModel.setId(rs.getLong("id"));
			classRoomModel.setUeid(rs.getString("ueid"));
			classRoomModel.setName(rs.getString("name"));
			classRoomModel.setCreateDate((rs.getDate("created_date")));
			tutor.setId(rs.getLong("tutor_id"));
			classRoomModel.setTutor(tutor);
			classRooms.add(classRoomModel);
		}

		return classRooms;
	}
	

}
