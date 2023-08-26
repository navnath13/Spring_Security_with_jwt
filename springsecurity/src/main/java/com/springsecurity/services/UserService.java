package com.springsecurity.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurity.model.User;
import com.springsecurity.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	// getting list of user

	public List<User> getUser() {
		return this.userRepository.findAll();

	}

	// getting single user

	public User getSingleUser(String name) {
			User user = this.userRepository.findByUsername(name).orElseThrow(()-> new IllegalAccessError("this user can be register"));
		return user ;
	
	}

	// adding new user
	public User addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return this.userRepository.save(user);
	}

}
