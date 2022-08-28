package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TutorDAO;
import com.model.TutorModel;
import com.service.TutorService;

@Service
public class TutorServiceImpl implements TutorService<TutorModel> {

	@Autowired
	private TutorDAO<TutorModel> tutorDAO;
	
	@Override
	public boolean update(TutorModel model) {
		return tutorDAO.update(model);
	}

	@Override
	public TutorModel getOneById(long id) {
		return tutorDAO.getOneById(id);

	}

	@Override
	public TutorModel getOneByUEId(String ueid) {
		return tutorDAO.getOneByUEId(ueid);

	}

	@Override
	public TutorModel getOneByName(String name) {
		return tutorDAO.getOneByName(name);
	}

	@Override
	public boolean remove(long id) {
		return tutorDAO.remove(id);

	}

	@Override
	public List<TutorModel> getAll() {
		return tutorDAO.getAll();

	}

	
}
