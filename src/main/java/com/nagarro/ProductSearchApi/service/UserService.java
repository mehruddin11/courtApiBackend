package com.nagarro.ProductSearchApi.service;

import java.util.List;
import java.util.Optional;

import com.nagarro.ProductSearchApi.dto.AuthResponse;
import com.nagarro.ProductSearchApi.model.User;

public interface UserService {

	   AuthResponse loginUser(String email, String password);

	User getUserByEmail(String email);

	String registerUser(User user);
	User findByMobileNumber(String mobileNumber);
	
	@SuppressWarnings("rawtypes")
	Optional  findUserById(Long userId, String username, String password);
	
	
	@SuppressWarnings("rawtypes")
	Optional  restPassword(long id,String password);

	User getUserById(Long userId);

	List<User> getAllUsers();
	
	 User updateUserProfile(long userId, User updatedUserData);
	
	 List<User> getAlluserData();

}
