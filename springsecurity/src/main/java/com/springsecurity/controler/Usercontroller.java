package com.springsecurity.controler;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.model.User;
import com.springsecurity.services.UserService;

@RestController
@RequestMapping("/user")
public class Usercontroller {

	@Autowired
	private UserService userService;


	// getting all user

	@GetMapping("/")
	public List<User> getAllUser() {
		return this.userService.getUser();
	}

	// getting single user
	@GetMapping("/{id}")
	public User getUser(@PathVariable("id") String name) {
		return this.userService.getSingleUser(name);

	}

	// adding new user
	@PostMapping("/create")
	public User addUser(@RequestBody User user) {
	 
		
	
		return this.userService.addUser(user);

	}
	@GetMapping("/current-user")
	public String getLoggedInUser(Principal principal) {
		return principal.getName();
		
	}

}
