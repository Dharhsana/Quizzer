package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.QuestionDAO;
import com.model.AnswerModel;
import com.model.QuestionModel;
import com.model.StudentModel;
import com.model.TutorModel;

@Repository
public class QuestionDAOImpl implements QuestionDAO<QuestionModel>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public boolean save(QuestionModel model) {
		
		int maxId = 0;
		StringBuilder sql = new StringBuilder("SELECT max(id) from question");
		
		Integer previousId = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		
		if(previousId==null) {
			previousId = 0;
		}
		maxId = previousId+1;
		sql.setLength(0);
		sql.append("INSERT INTO question(id,ueid,text,weight,answer_correct,tutor_id) values(?,?,?,?,?)");
		return jdbcTemplate.update(sql.toString(),model.getId(),model.getUeid(),model.getText(),model.getWeight(),model.getCorrectAnswer().getId(),model.getTutor().getId())>0;
		
	}

	@Override
	public boolean update(QuestionModel model) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE question set text = ? ,weight = ?,answer_correct = ? where id=?");
		return jdbcTemplate.update(sql.toString(),model.getText(),model.getWeight(),model.getCorrectAnswer().getId(),model.getId())>0;
	}

	@Override
	public QuestionModel getOneById(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT question.id as id,question.ueid as ueid,question.text as text,question.weight as weight,question.answer_correct as answer_correct,tutor_id as tid from question where question.id = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {id}
				,this::mapRowToModel);
	}

	@Override
	public QuestionModel getOneByUEId(String ueid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT question.id as id,question.ueid as ueid,question.text as text,question.weight as weight,question.answer_correct as answer_correct,tutor_id as tid from question where question.ueid = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {ueid}
				,this::mapRowToModel);
	}

	@Override
	public QuestionModel getOneByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT question.id as id,question.ueid as ueid,question.text as text,question.weight as weight,question.answer_correct as answer_correct,tutor_id as tid from question where question.name = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {name}
				,this::mapRowToModel);
	}

	@Override
	public List<QuestionModel> getAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT question.id as id,question.ueid as ueid,question.text as text,question.weight as weight,question.answer_correct as answer_correct,tutor_id as tid from question");
		return jdbcTemplate.queryForObject(sql.toString()
				,this::mapRowsToModel);
	}
	
	@Override
	public boolean remove(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM question where id = ?");
		return jdbcTemplate.update(sql.toString(),id)>0;
	}

	@Override
	public boolean addQuestionToQuiz(QuestionModel model) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO quiz_has_question(quiz_id,question_id,question_label,question_display_order) values(?,?,?,?)");
		return jdbcTemplate.update(sql.toString(),model.getQuizList().get(0).getId(),model.getLabel(),model.getDisplayOrder())>0;
	}
	
	@Override
	public List<QuestionModel> getQuestionsOfQuiz(long quizId){
		StringBuilder sql = new StringBuilder();
		String query = "SELECT qhq.quiz_id as quiz_id,q.id as id,q.text as text,q.weight as weight,q.answer_correct as answer_correct,q.tutor_id as tutor_id from question q , quiz_has_question qhq where q.id=qhq.quiz_id";
		sql.append(query);
		return jdbcTemplate.queryForObject(sql.toString()
				,this::mapRowsToModel);
	}
	
	@Override
	public QuestionModel mapRowToModel(ResultSet rs,int numRows) throws SQLException {
		
		QuestionModel questionModel = new QuestionModel();
		AnswerModel answer = new AnswerModel();
		TutorModel tutor = new TutorModel();
		questionModel.setId(rs.getLong("id"));
		questionModel.setUeid(rs.getString("ueid"));
		questionModel.setText(rs.getString("text"));
		questionModel.setWeight(rs.getInt("weight"));
		answer.setId(rs.getInt("answer_correct"));
		questionModel.setCorrectAnswer(answer);
		tutor.setId(rs.getInt("tutor_id"));
		questionModel.setTutor(tutor);

		return questionModel;
	}

	@Override
	public List<QuestionModel> mapRowsToModel(ResultSet rs,int numRows) throws SQLException {
		
		QuestionModel questionModel = new QuestionModel();
		AnswerModel answer = new AnswerModel();
		TutorModel tutor = new TutorModel();
		List<QuestionModel> questions = new ArrayList<QuestionModel>();
		
		int i = 0;
		for(i=0;i<numRows;i++) {
			
			
			
			questionModel.setId(rs.getLong("id"));
			questionModel.setUeid(rs.getString("ueid"));
			questionModel.setText(rs.getString("text"));
			questionModel.setWeight(rs.getInt("weight"));
			answer.setId(rs.getInt("answer_correct"));
			questionModel.setCorrectAnswer(answer);
			tutor.setId(rs.getInt("tutor_id"));
			questionModel.setTutor(tutor);
		}

		return questions;
	}
}
