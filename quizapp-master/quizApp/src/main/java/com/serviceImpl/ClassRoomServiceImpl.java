package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ClassRoomDAO;
import com.model.ClassRoomModel;
import com.service.ClassRoomService;
import com.utility.Utility;

@Service
public class ClassRoomServiceImpl implements ClassRoomService<ClassRoomModel>{

	@Autowired
	private ClassRoomDAO<ClassRoomModel> classRoomDAO;
	
	@Override
	public boolean save(ClassRoomModel model) {
		
		model.setUeid(Utility.getUEID("CLASSROOM"));
		return classRoomDAO.save(model);
	}

	@Override
	public boolean update(ClassRoomModel model) {
		return classRoomDAO.update(model);
	}

	@Override
	public ClassRoomModel getOneById(long id) {
		return classRoomDAO.getOneById(id);
	}

	@Override
	public ClassRoomModel getOneByUEId(String ueid) {
		return classRoomDAO.getOneByUEId(ueid);
	}

	@Override
	public ClassRoomModel getOneByName(String name) {
		return classRoomDAO.getOneByName(name);
	}

	@Override
	public boolean remove(long id) {
		return classRoomDAO.remove(id);
	}

	@Override
	public List<ClassRoomModel> getAll() {
		return classRoomDAO.getAll();
	}

	
}
