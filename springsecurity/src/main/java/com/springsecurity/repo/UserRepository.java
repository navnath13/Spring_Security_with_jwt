package com.springsecurity.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
   public Optional<User> findByUsername(String email);
  
}
