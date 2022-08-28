package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.QuizDAO;
import com.model.QuizModel;
import com.service.QuizService;
import com.utility.Utility;

@Service
public class QuizServiceImpl implements QuizService<QuizModel>{

	@Autowired
	private QuizDAO<QuizModel> quizDAO;
	
	@Override
	public boolean save(QuizModel model) {
		model.setUeid(Utility.getUEID("QUIZ"));
		return quizDAO.save(model);
	}

	@Override
	public boolean update(QuizModel model) {
		return quizDAO.update(model);

	}

	@Override
	public QuizModel getOneById(long id) {
		return quizDAO.getOneById(id);

	}

	@Override
	public QuizModel getOneByUEId(String ueid) {
		return quizDAO.getOneByUEId(ueid);

	}

	@Override
	public QuizModel getOneByName(String name) {
		return quizDAO.getOneByName(name);

	}

	@Override
	public boolean remove(long id) {
		return quizDAO.remove(id);

	}

	@Override
	public List<QuizModel> getAll() {
		return quizDAO.getAll();

	}

}
