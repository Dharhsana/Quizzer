package com.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dao.UserDAO;
import com.model.StudentModel;
import com.model.Token;
import com.model.UserModel;
import com.service.UserService;
import com.utility.Authentication;

@RestController
@RequestMapping("/api/user")
public final class UserController {

	@Autowired
	private UserService<UserModel, Token> userService;
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestBody UserModel user){
		if(userService.save(user)) {
			return new ResponseEntity<String>(HttpStatus.OK.getReasonPhrase(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@GetMapping("/login")
	public ResponseEntity<Token> userLogin(@RequestParam("email") String email,@RequestParam("password") String password){
		return new ResponseEntity<Token>(userService.login(email, password),HttpStatus.OK);
	}
	
	@GetMapping("/login/otp")
	public ResponseEntity<Token> userLoginWithOTP(@RequestParam("email") String email,@RequestParam("otp") String otp){
		return new ResponseEntity<Token>(userService.loginWithOTP(email, otp),HttpStatus.OK);
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> userLogout(@RequestBody Token token){
		return new ResponseEntity<String>(userService.logout(token),HttpStatus.OK);
	}
	
	@GetMapping("/update")
	public ResponseEntity<String> updateUser(@RequestHeader("token-string") String token ,@RequestBody UserModel model){
		
		if(!Authentication.checkToken(token)) {
			return new ResponseEntity<String>(Authentication.TOKEN_EXPIRED,HttpStatus.BAD_REQUEST);

		}
		if(userService.update(model)) {
			return new ResponseEntity<String>(HttpStatus.OK.getReasonPhrase(),HttpStatus.OK);

		}
		else {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST.getReasonPhrase(),HttpStatus.BAD_REQUEST);

		}
	}
	
	@GetMapping("/getByID/{id}")
	public ResponseEntity<UserModel> getByID(@RequestHeader("token-string") String token ,@PathVariable(value = "id") long id){
		
		
		
		return new ResponseEntity(userService.getOneById(id),HttpStatus.OK);
	}
	
	@GetMapping("/getByUEID/{ueid}")
	public ResponseEntity<UserModel> getByUEID(@RequestHeader("token-string") String token , @PathVariable(value = "ueid") String ueid){
		
		
		
		return new ResponseEntity(userService.getOneByUEId(ueid),HttpStatus.OK);
	}
	
	@GetMapping("/getByName/{name}")
	public ResponseEntity<UserModel> getByName(@RequestHeader("token-string") String token ,@PathVariable(value = "name") String name){
		
		
		
		return new ResponseEntity(userService.getOneByName(name),HttpStatus.OK);
	}
	
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> remove(@RequestHeader("token-string") String token ,@PathVariable(value="id") long id){
		
		
		
		return new ResponseEntity(userService.remove(id)?"USER REMOVED":"USER DOES NOT EXIST",HttpStatus.OK);
	}
	
}
