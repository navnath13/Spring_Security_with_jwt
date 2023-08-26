package com.springsecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springsecurity.repo.UserRepository;
@Service
public class CustomerUserDetailService implements UserDetailsService{
  
	@Autowired
	private UserRepository userRepository;
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		//loading user by user name
		return this.userRepository.findByUsername(email).orElseThrow(()-> new RuntimeException("user not found"));
	}

}
