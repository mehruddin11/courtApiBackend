package com.nagarro.ProductSearchApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ProductSearchApi.model.User;

public interface UserRepository extends JpaRepository<User,Long> {
	User findByEmail(String email);

	boolean existsByMobileNumber(String mobileNumber);
//	User  findByMobileNumber(String mobileNumber);
	 User findByMobileNumber(String mobileNumber);
	

	User findByUsername(String username);
	
	
	 
}

