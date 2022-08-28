package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.QuizDAO;
import com.model.ClassRoomModel;
import com.model.QuizModel;

@Repository
public class QuizDAOImpl implements QuizDAO<QuizModel>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean save(QuizModel model) {
		
		StringBuilder sql = new StringBuilder();
		int maxId = 0;
		sql.append("SELECT MAX(id) FROM quiz");
		
		Integer previousId = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		
		if(previousId==null) {
			previousId = 0;
		}
		maxId = previousId+1;
		sql.setLength(0);
		sql.append("INSERT INTO quiz(id,ueid,name,time,number_of_questions,classroom_id) values(?,?,?,?,?)");
		return jdbcTemplate.update(sql.toString(), maxId,model.getUeid(),model.getName(),model.getTime(),model.getNumberOfQuestion(),model.getClassRooms().get(0).getId())>0;
	}

	@Override
	public boolean update(QuizModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE quiz set name=? , time=?,number_of_questions=? where id =?");
		return jdbcTemplate.update(sql.toString(),model.getName(),model.getTime(),model.getNumberOfQuestion(),model.getId())>0;
		
	}

	@Override
	public QuizModel getOneById(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT quiz.id as id,quiz.ueid as ueid,quiz.name as name,quiz.time as time,quiz.number_of_questions as number_of_questions,classroom_id as cid from quiz where quiz.id = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {id}
				,this::mapRowToModel);
	}

	@Override
	public QuizModel getOneByUEId(String ueid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT quiz.id as id,quiz.ueid as ueid,quiz.name as name,quiz.time as time,quiz.number_of_questions as number_of_questions,classroom_id as cid from quiz where quiz.ueid = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {ueid}
				,this::mapRowToModel);
	}

	@Override
	public QuizModel getOneByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT quiz.id as id,quiz.ueid as ueid,quiz.name as name,quiz.time as time,quiz.number_of_questions as number_of_questions,classroom_id as cid from quiz where quiz.name = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {name}
				,this::mapRowToModel);
	}

	@Override
	public List<QuizModel> getAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT quiz.id as id,quiz.ueid as ueid,quiz.name as name,quiz.time as time,quiz.number_of_questions as number_of_questions,classroom_id as cid from quiz");
		return jdbcTemplate.queryForObject(sql.toString()
				,this::mapRowsToModel);
	}
	
	
	
	@Override
	public boolean remove(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM quiz where id = ?");
		return jdbcTemplate.update(sql.toString(),id)>0;
	}

	
	private QuizModel mapRowToModel(ResultSet rs,int numRows) throws SQLException {
		
		QuizModel quizModel = new QuizModel();
		List<ClassRoomModel> classRooms = new ArrayList<ClassRoomModel>();
		ClassRoomModel classRoomModel = new ClassRoomModel();
		
		quizModel.setId(rs.getLong("id"));
		quizModel.setUeid(rs.getString("ueid"));
		quizModel.setName(rs.getString("name"));
		quizModel.setTime(rs.getString("time"));
		quizModel.setNumberOfQuestion(rs.getInt("number_of_questions"));
		classRoomModel.setId(rs.getLong("id"));
		classRooms.set(0, classRoomModel);
		quizModel.setClassRooms(classRooms);

		
		return quizModel;
	}

	
	private List<QuizModel> mapRowsToModel(ResultSet rs,int numRows) throws SQLException {
		
		QuizModel quizModel = new QuizModel();
		List<QuizModel> quizzes = new ArrayList<QuizModel>();
		List<ClassRoomModel> classRooms = new ArrayList<ClassRoomModel>();
		ClassRoomModel classRoomModel = new ClassRoomModel();
		
		int i = 0;
		for(i=0;i<numRows;i++) {
			
			quizModel.setId(rs.getLong("id"));
			quizModel.setUeid(rs.getString("ueid"));
			quizModel.setName(rs.getString("name"));
			quizModel.setTime(rs.getString("time"));
			quizModel.setNumberOfQuestion(rs.getInt("number_of_questions"));
			classRoomModel.setId(rs.getLong("id"));
			classRooms.set(0, classRoomModel);
			quizModel.setClassRooms(classRooms);
			
			quizzes.add(quizModel);
		}

		return quizzes;
	}
}
