package com.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.StudentModel;
import com.service.StudentService;

@RestController
@RequestMapping("api/student")
public final class StudentController {

	@Autowired
	private StudentService<StudentModel> studentService;
	
	@PutMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody StudentModel model){
		if(studentService.update(model)) {
			return new ResponseEntity(HttpStatus.OK.getReasonPhrase(),HttpStatus.OK);

		}
		else {
			return new ResponseEntity(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/getByID/{id}")
	public ResponseEntity<StudentModel> getByID(@PathParam(value = "id") long id){
		
		return new ResponseEntity(studentService.getOneById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getByUEID/{ueid}")
	public ResponseEntity<StudentModel> getByUEID(@PathParam(value = "ueid") long ueid){
		
		return new ResponseEntity(studentService.getOneById(ueid),HttpStatus.OK);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<StudentModel> getByUEID(@PathParam(value = "name") String name){
		
		return new ResponseEntity(studentService.getOneByName(name),HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> remove(@PathParam(value="id") long id){
		return new ResponseEntity(studentService.remove(id),HttpStatus.OK);
	}
	
	
}
