package com.service;

import java.util.List;

public interface QuestionService<T> {

	public boolean save(T model);
	public boolean update(T model);
	public T getOneById(long id);
	public T getOneByUEId(String ueid);
	public T getOneByName(String name);
	public boolean remove(long id);
	public List<T> getAll();
}
