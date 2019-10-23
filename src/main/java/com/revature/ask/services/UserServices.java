package com.revature.ask.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.ask.users.User;
import com.revature.ask.repos.UserRepo;

@Service
public class UserServices {

	@Autowired
	private UserRepo userRepo;
	
	public List<User> findAll() {
		return (List<User>) userRepo.findAll();
	}

	
}
