package com.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.dao.AnswerDAO;
import com.model.AnswerModel;
import com.model.QuestionModel;

@Repository
public class AnswerDAOImpl implements AnswerDAO<AnswerModel> {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public boolean save(AnswerModel model) {
		StringBuilder sql = new StringBuilder();
		int maxId = 0;
		sql.append("SELECT MAX(id) FROM answer");
		
		Integer previousId = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
		
		if(previousId==null) {
			previousId = 0;
		}
		maxId = previousId+1;
		sql.setLength(0);
		sql.append("INSERT INTO answer(id,ueid,label,text,question_id) values(?,?,?,?,?)");
		return jdbcTemplate.update(sql.toString(), maxId, model.getUeid(), model.getLabel(), model.getText(),
				model.getQuestion().getId()) > 0;

	}

	@Override
	public int[] save(List<AnswerModel> models) {
		StringBuilder sql = new StringBuilder();
		int maxId = 0;
		sql.append("SELECT MAX(id) FROM answer");
		maxId = jdbcTemplate.queryForObject(sql.toString(), Integer.class)+1;
		sql.setLength(0);
		sql.append("INSERT INTO answer(id,ueid,label,text,question_id) values(?,?,?,?,?)");
		
		for(AnswerModel m:models) {
			m.setId(maxId++);
		}
		
		return jdbcTemplate.batchUpdate(sql.toString(),
				new BatchPreparedStatementSetter() {
					
					@Override
					public void setValues(PreparedStatement ps, int i) throws SQLException {
						ps.setLong(1, models.get(i).getId());
						ps.setString(2, models.get(i).getUeid());
						ps.setString(3, models.get(i).getLabel());
						ps.setString(4, models.get(i).getText());
						ps.setLong(5, models.get(i).getQuestion().getId());
						
					}
					
					@Override
					public int getBatchSize() {
						
						return models.size();
					}
				});

	}

	@Override
	public boolean update(AnswerModel model) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE answer SET label=?,text=?,question=? where id = ?");

		return jdbcTemplate.update(sql.toString(), model.getLabel(), model.getText(), model.getQuestion().getId(),
				model.getId()) > 0;
	}
	
	@Override
	public int[] update(List<AnswerModel> models) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE answer SET label=?,text=?,question=? where id = ?");

		return jdbcTemplate.batchUpdate(sql.toString(),new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, models.get(i).getLabel());
				ps.setString(2, models.get(i).getText());
				ps.setLong(3, models.get(i).getQuestion().getId());
				ps.setLong(3, models.get(i).getId());

			}
			
			@Override
			public int getBatchSize() {
				
				return models.size();
			}
		});
	}

	@Override
	public AnswerModel getOneById(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT answer.id as id,answer.ueid as ueid,answer.label as label,answer.text as text,question.id as qid from answer,question where answer.id = ? and question.id = answer.id");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { id }, this::mapRowToAnswer);
	}

	@Override
	public AnswerModel getOneByUEId(String ueid) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT answer.id as id,answer.ueid as ueid,answer.label as label,answer.text as text,question.id as qid from answer,question where answer.ueid = ? and question.id = answer.id");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { ueid }, this::mapRowToAnswer);
	}

	@Override
	public AnswerModel getOneByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append(
				"SELECT answer.id as id,answer.ueid as ueid,answer.label as label,answer.text as text,question.id as qid from answer,question where answer.name = ? and question.id = answer.id");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] { name }, this::mapRowToAnswer);
	}

	@Override
	public List<AnswerModel> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AnswerModel> getAnswersByQuestionId(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("Select id,label,text,ueid,question_id from answer where question_id = ?");
		
		return jdbcTemplate.query(sql.toString(),new RowMapper<AnswerModel>() {

			@Override
			public AnswerModel mapRow(ResultSet rs, int rowNum) throws SQLException {
				AnswerModel model = new AnswerModel();
				model.setId(rs.getLong(1));
				model.setLabel(rs.getString(2));
				model.setText(rs.getString(3));
				model.setUeid(rs.getString(4));
				return model;
			}
			
		});
	}

	@Override
	public List<AnswerModel> getByQuizId(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean remove(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM answer where id = ?");
		return jdbcTemplate.update(sql.toString(), id) > 0;
	}

	private AnswerModel mapRowToAnswer(ResultSet rs, int numRows) throws SQLException {

		AnswerModel answerModel = new AnswerModel();
		QuestionModel questionModel = new QuestionModel();
		answerModel.setId(rs.getLong("id"));
		answerModel.setUeid(rs.getString("ueid"));
		answerModel.setLabel(rs.getString("label"));
		answerModel.setText(rs.getString("text"));
		questionModel.setId(rs.getLong("qid"));
		answerModel.setQuestion(questionModel);

		return answerModel;
	}

	

}
