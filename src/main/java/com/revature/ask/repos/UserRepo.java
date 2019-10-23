package com.revature.ask.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.revature.ask.users.User;

public interface UserRepo extends CrudRepository<User, Long>{
	//List<User> findAll();
	//User findOne();
}
