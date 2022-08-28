package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.QuestionDAO;
import com.model.QuestionModel;
import com.service.QuestionService;
import com.utility.Utility;

@Service
public class QuestionServiceImpl implements QuestionService<QuestionModel>{

	@Autowired 
	QuestionDAO<QuestionModel> questionDAO;
	
	@Override
	public boolean save(QuestionModel model) {
		model.setUeid(Utility.getUEID("QUESTION"));
		return questionDAO.save(model);
	}

	@Override
	public boolean update(QuestionModel model) {
		return questionDAO.update(model);
	}

	@Override
	public QuestionModel getOneById(long id) {
		return questionDAO.getOneById(id);
	}

	@Override
	public QuestionModel getOneByUEId(String ueid) {
		return questionDAO.getOneByUEId(ueid);
	}

	@Override
	public QuestionModel getOneByName(String name) {
		return questionDAO.getOneByName(name);
	}

	@Override
	public boolean remove(long id) {
		return questionDAO.remove(id);
	}

	@Override
	public List<QuestionModel> getAll() {
		return questionDAO.getAll();
	}

}
