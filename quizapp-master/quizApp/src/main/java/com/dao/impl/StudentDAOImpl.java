package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dao.StudentDAO;
import com.model.QuizModel;
import com.model.StudentModel;

@Repository
public class StudentDAOImpl implements StudentDAO <StudentModel>{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@Override
	public boolean save(long id,String ueid,String name) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("INSERT INTO student(id,ueid,name,profile_pic) values(?,?,?,?)");
		return jdbcTemplate.update(sql.toString(), id,ueid,name,null)>0;
	}

	@Override
	public boolean update(StudentModel model) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("UPDATE student set name = ? where id =?");
		return jdbcTemplate.update(sql.toString(),model.getName(),model.getId())>0;
	}

	@Override
	public StudentModel getOneById(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT student.id as id,student.ueid as ueid,student.name as name,student.profile_pic as profile_pic from student where student.id = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {id}
				,this::mapRowToModel);
	}

	@Override
	public StudentModel getOneByUEId(String ueid) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT student.id as id,student.ueid as ueid,student.name as name,student.profile_pic as profile_pic from student where student.ueid = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {ueid}
				,this::mapRowToModel);
	}

	@Override
	public StudentModel getOneByName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT student.id as id,student.ueid as ueid,student.name as name,student.profile_pic as profile_pic from student where student.name = ?");
		return jdbcTemplate.queryForObject(sql.toString(), new Object[] {name}
				,this::mapRowToModel);
	}

	@Override
	public List<StudentModel> getAll(){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT student.id as id,student.ueid as ueid,student.name as name,student.profile_pic as profile_pic from student");
		return jdbcTemplate.queryForObject(sql.toString()
				,this::mapRowsToModel);
	}
	
	@Override
	public boolean remove(long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM student where id = ?");
		return jdbcTemplate.update(sql.toString(),id)>0;
	}
	
	@Override
	public StudentModel getStudentQuizzes(long studentId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT FROM shq.student_id as sid,shq.quiz_id as qid,shq.attempt as attempt,shq.result as result,shq.grade as grade from student_has_quiz shq where shq.student_id=?");
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql.toString(), studentId);
		List<QuizModel> quizList = new ArrayList<QuizModel>();
		QuizModel quiz = new QuizModel();
		StudentModel studentModel = new StudentModel();
		
		for(Map row :rows) {
			quiz.setId(Long.valueOf((String) row.get("qid")));
			studentModel.setId(Long.valueOf((String) row.get("sid")));
			quiz.setAttempt(Integer.valueOf((String)row.get("attempt")));
			quiz.setResult(Double.valueOf((String)row.get("result")));
			quiz.setGrade((String)row.get("grade"));
			
			quizList.add(quiz);
		}
		
		studentModel.setQuizzes(quizList);
		return studentModel;
		
	}
	
	public QuizModel getStudentQuizz(long studentId,long quizId) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT FROM shq.student_id as sid,shq.quiz_id as qid,shq.attempt as attempt,shq.result as result,shq.grade as grade from student_has_quiz shq where shq.student_id=? and shq.quiz_id=?");
		List<Map<String,Object>> rows = jdbcTemplate.queryForList(sql.toString(), studentId,quizId);
		
		QuizModel quiz = new QuizModel();
		StudentModel studentModel = new StudentModel();
		
		for(Map row :rows) {
			
			quiz.setId(Long.valueOf((String) row.get("qid")));
			studentModel.setId(Long.valueOf((String) row.get("sid")));
			quiz.setAttempt(Integer.valueOf((String)row.get("attempt")));
			quiz.setResult(Double.valueOf((String)row.get("result")));
			quiz.setGrade((String)row.get("grade"));
			
			
		}
		
		
		return quiz;
		
	}
	
	
	private StudentModel mapRowToModel(ResultSet rs,int numRows) throws SQLException {
		
		StudentModel studentModel = new StudentModel();
		
		studentModel.setId(rs.getLong("id"));
		studentModel.setUeid(rs.getString("ueid"));
		studentModel.setName(rs.getString("name"));
		studentModel.setProPic(rs.getString("profile_pic"));
		
		
		
		return studentModel;
	}

	
	private List<StudentModel> mapRowsToModel(ResultSet rs,int numRows) throws SQLException {
		
		StudentModel studentModel = new StudentModel();
		List<StudentModel> students = new ArrayList<StudentModel>();
		
		int i = 0;
		for(i=0;i<numRows;i++) {
			
			studentModel.setId(rs.getLong("id"));
			studentModel.setUeid(rs.getString("ueid"));
			studentModel.setName(rs.getString("name"));
			studentModel.setProPic(rs.getString("profile_pic"));
			
			students.add(studentModel);
		}

		return students;
	}
}
