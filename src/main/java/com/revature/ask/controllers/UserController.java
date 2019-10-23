package com.revature.ask.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.revature.ask.services.UserServices;
import com.revature.ask.users.User;

@Controller
public class UserController {

	@Autowired
	UserServices userService;
	
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> findAll() {
		List<User> users = userService.findAll();
		
		return ResponseEntity.ok(users);
	}
	
	
}
