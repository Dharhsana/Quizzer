package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.model.QuestionModel;

public interface QuestionDAO <T>{

	public boolean save(T model);
	public boolean update(T model);
	public T getOneById(long id);
	public T getOneByUEId(String ueid);
	public T getOneByName(String name);
	public boolean remove(long id);
	QuestionModel mapRowToModel(ResultSet rs, int numRows) throws SQLException;
	List<QuestionModel> mapRowsToModel(ResultSet rs, int numRows) throws SQLException;
	List<QuestionModel> getAll();
	boolean addQuestionToQuiz(QuestionModel model);
	List<QuestionModel> getQuestionsOfQuiz(long quizId);
}
