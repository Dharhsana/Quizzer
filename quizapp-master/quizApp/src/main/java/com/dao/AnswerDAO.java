package com.dao;

import java.util.List;

import com.model.AnswerModel;

public interface AnswerDAO <T>{

	public boolean save(T model);
	public boolean update(T model);
	public int[] save(List<T> models);
	public int[] update(List<T> models);
	public T getOneById(long id);
	public T getOneByUEId(String ueid);
	public T getOneByName(String name);
	public List<T> getAll();
	public List<T> getAnswersByQuestionId(long id);
	public List<T> getByQuizId(long id);
	public boolean remove(long id);
	
}
