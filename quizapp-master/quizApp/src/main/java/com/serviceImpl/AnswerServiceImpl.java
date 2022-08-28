package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.impl.AnswerDAOImpl;
import com.model.AnswerModel;
import com.service.AnswerService;
import com.utility.Utility;

	
@Service
public class AnswerServiceImpl implements AnswerService<AnswerModel> {

	@Autowired
	AnswerDAOImpl answerDAOImpl;
	
	@Override
	public boolean save(AnswerModel model) {
		
		model.setUeid(Utility.getUEID("ANSWER"));
		return answerDAOImpl.save(model);
	}

	@Override
	public boolean save(List<AnswerModel> models) {
		
		models.forEach((i)->i.setUeid(Utility.getUEID("ANSWER")));
		
		return answerDAOImpl.save(models).length>0;
	}
	
	@Override
	public boolean update(AnswerModel model) {
		
		return answerDAOImpl.update(model);
	}

	@Override
	public boolean update(List<AnswerModel> models) {
		
		return answerDAOImpl.update(models).length>0;
	}
	
	@Override
	public AnswerModel getOneById(long id) {
		
		return answerDAOImpl.getOneById(id);
	}

	@Override
	public AnswerModel getOneByUEId(String ueid) {
		return answerDAOImpl.getOneByUEId(ueid);
	}

	@Override
	public AnswerModel getOneByName(String name) {
		
		return answerDAOImpl.getOneByName(name);
	}

	@Override
	public boolean remove(long id) {
		
		return answerDAOImpl.remove(id);
	}

}
