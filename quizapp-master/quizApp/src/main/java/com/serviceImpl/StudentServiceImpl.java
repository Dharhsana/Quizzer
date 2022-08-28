package com.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StudentDAO;
import com.model.StudentModel;
import com.service.StudentService;
import com.utility.Utility;

@Service
public class StudentServiceImpl implements StudentService<StudentModel>{

	@Autowired
	StudentDAO<StudentModel> studentDAO;
	
	

	@Override
	public boolean update(StudentModel model) {
		return studentDAO.update(model);
	}

	@Override
	public StudentModel getStudent(long id) {
		return studentDAO.getOneById(id);

	}

	@Override
	public StudentModel getOneById(long id) {
		return studentDAO.getOneById(id);
	}

	@Override
	public StudentModel getOneByUEId(String ueid) {
		return studentDAO.getOneByUEId(ueid);
	}

	@Override
	public StudentModel getOneByName(String name) {
		return studentDAO.getOneByName(name);

	}

	@Override
	public boolean remove(long id) {
		return studentDAO.remove(id);

	}

	@Override
	public List<StudentModel> getAllStudents() {
		return studentDAO.getAll();
	}

}
