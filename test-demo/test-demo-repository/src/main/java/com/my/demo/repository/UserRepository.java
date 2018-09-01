package com.my.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.my.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByUsername(String username);
	
	public List<User> findByUserRealName(String userRealName);
}
