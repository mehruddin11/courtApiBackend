package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.dto.AuthResponse;
import com.nagarro.ProductSearchApi.exception.DuplicateEmailException;
import com.nagarro.ProductSearchApi.exception.DuplicatePhoneNumberException;
import com.nagarro.ProductSearchApi.exception.InvalidCredentialsException;
import com.nagarro.ProductSearchApi.exception.PasswordResetNotAllowedException;
import com.nagarro.ProductSearchApi.exception.UserNotFoundException;
import com.nagarro.ProductSearchApi.exception.UserNotVerifiedException;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.UserRepository;
import com.nagarro.ProductSearchApi.security.JwtTokenUtil;
import com.nagarro.ProductSearchApi.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	
//	@Override
//	public String loginUser(String username, String password) {
//	    try {
//	        // Authenticate the user
//	        Authentication authentication = authenticationManager
//	                .authenticate(new UsernamePasswordAuthenticationToken(username, password));
//	        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//	        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//
//	       
//	        User user = userRepository.findByUsername(username);
//	        if (user != null && user.isVerified() && user.isCredentaialCreated()) {
//	            String token = jwtTokenUtil.generateToken(userDetails);
//	            return token;
//	        } else {
//	            throw new UserNotVerifiedException("");
//	        }
//	    } catch (AuthenticationException | UserNotVerifiedException e) {
//	        throw new InvalidCredentialsException("Invalid email or password or User not verified or credentials not created");
//	    }
//	}
	
	public AuthResponse loginUser(String username, String password) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            User user = userRepository.findByUsername(username);
            if (user != null && user.isVerified() && user.isCredentaialCreated()) {
                String token = jwtTokenUtil.generateToken(userDetails);
                AuthResponse authResponse = new AuthResponse(token, user);
                return authResponse;
            } else {
                throw new UserNotVerifiedException("");
            }
        } catch (AuthenticationException | UserNotVerifiedException e) {
            throw new InvalidCredentialsException("Invalid email or password or User not verified or credentials not created");
        }
    }



	@Override
	public String registerUser(User user) {
	    user.setPassword(passwordEncoder.encode(user.getPassword()));

	    try {
	        System.out.print("1->>>>>>>>>>>>>>>Error");

	        // Check for duplicate mobile number
	        User duplicateUserByMobileNumber = userRepository.findByMobileNumber(user.getMobileNumber());
	        if (duplicateUserByMobileNumber != null) {
	            int nextStep = duplicateUserByMobileNumber.isVerified() ? 3 : 2;
	            if (duplicateUserByMobileNumber.isCredentaialCreated() && duplicateUserByMobileNumber.isVerified()) {
	                throw new DuplicateEmailException("Already registered", 1, duplicateUserByMobileNumber.getId());
	            }
	            throw new DuplicatePhoneNumberException("User already exists with this mobile number", nextStep, duplicateUserByMobileNumber.getId());
	        }

	        // Check for duplicate email
	        User duplicateByEmail = userRepository.findByEmail(user.getEmail());
	        if (duplicateByEmail != null) {
	            int nextStep = duplicateByEmail.isVerified() ? 3 : 2;
	            if (duplicateByEmail.isCredentaialCreated() && duplicateByEmail.isVerified()) {
	                throw new DuplicateEmailException("Already registered", 1, duplicateByEmail.getId());
	            }
	            throw new DuplicateEmailException("User already exists with this email.", nextStep, duplicateByEmail.getId());
	        }

	        userRepository.save(user);

	        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
	                user.getUsername(), user.getPassword(), getAuthorities(user.getRoles())
	        );
	        String token = jwtTokenUtil.generateToken(userDetails);

	        return token;
	    } catch (DataIntegrityViolationException e) {
	        System.out.print("3->>>>>>>>>>>>>>>Error");
	        throw new RuntimeException("Registration failed due to a data integrity violation", e);
	    }
	}



	
	private Collection<? extends GrantedAuthority> getAuthorities(String roles) {
	    return Arrays.stream(roles.split(","))
	            .map(SimpleGrantedAuthority::new)
	            .collect(Collectors.toList());
	}

	
	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UserNotFoundException("User not found with email: " + email);
		}
		return user;
	}
	
	 
	 @Override
	    public User findByMobileNumber(String mobileNumber) {
	        try {
	        	 return userRepository.findByMobileNumber(mobileNumber);
	        } catch (Exception e) {
	           
	            e.printStackTrace();
	            return null;
	        }
	    }
	 
	 
	@SuppressWarnings("rawtypes")
	@Override
	public Optional findUserById(Long userId, String username, String password) {
		 Optional<User> existingUser = userRepository.findById(userId);
	        if (existingUser.isPresent()) {
	            User userToUpdate = existingUser.get();
	            
	            User duplicateUserByUsername = userRepository.findByUsername(username);
	           
	            if (duplicateUserByUsername != null) {
	            	System.out.print("helllloooo");
	            	System.out.println("duplicate user" + duplicateUserByUsername);
	                throw new DuplicateEmailException("User already exists with this username. Please verify.", 0, userId);
	                
	            }
	            userToUpdate.setUsername(username);
	            userToUpdate.setPassword(passwordEncoder.encode(password));
	            userToUpdate.setCredentaialCreated(true);
	            userRepository.save(userToUpdate);
	            
	        } else {
	        	
	            Map<String, String> response = new HashMap<>();
	            response.put("message", "No user exists with the provided ID: " + userId);
	            
	        }
			return existingUser;
	}


	


	@Override
	public Optional<User> restPassword(long id, String newPassword) {
	    try {
	        Optional<User> optionalUser = userRepository.findById(id);

	        if (optionalUser.isPresent() ) {
	            User user = optionalUser.get();

	            if (user.isPasswordRest()) {
	            	
	            	user.setPassword(passwordEncoder.encode(newPassword));
	            	User updatedUser = userRepository.save(user);
	            	return Optional.of(updatedUser);
	            }
	            else {
	              
	                throw new PasswordResetNotAllowedException();
	            }
	           
	        } else {
	            
	            throw new NotFoundException();
	        }
	    }  catch (Exception e) {
	        e.printStackTrace(); // Log the exception
	        throw new RuntimeException("Error updating user password by ID", e);
	    }
	}
	
	@Override
	public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }


	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}


	 @Override
	    @Transactional
	    public User updateUserProfile(long userId, User updatedUserData) {
	        // Check if the provided email is already associated with another user
	        String newEmail = updatedUserData.getEmail();
	        User existingUserWithEmail = userRepository.findByEmail(newEmail);

	        if (existingUserWithEmail != null && !existingUserWithEmail.getId().equals(userId)) {
	            throw new DuplicateEmailException("Email address is already in use by another user",0, null);
	        }

	       
	        User existingUser = userRepository.findById(userId)
	                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));

	        // Update user fields
	     // Update user fields
	        existingUser.setFirstName(updatedUserData.getFirstName());
	        existingUser.setLastName(updatedUserData.getLastName());
	        existingUser.setEmail(newEmail);
	        existingUser.setState(updatedUserData.getState());
	        existingUser.setDistrict(updatedUserData.getDistrict());
	        existingUser.setCity(updatedUserData.getCity());
	        existingUser.setAddress(updatedUserData.getAddress());
//	        existingUser.setMobileNumber(updatedUserData.getMobileNumber());
	        existingUser.setCategory(updatedUserData.getCategory());
	       existingUser.setProfession(updatedUserData.getProfession());
	        
	        // Update other fields as needed

	        // Save the updated user
	        return userRepository.save(existingUser);
	    }



	@Override
	public List<User> getAlluserData() {
		return userRepository.findAll();
	}
	 
	 






////	reset password
//	@Override
//    public void resetPassword(String email, String password) {
//        User user = userRepository.findByEmail(email);
//        if (user == null) {
//            throw new UserNotFoundException("User not found with email: " + email);
//        }
//
//        user.setPassword(passwordEncoder.encode(password));
//        userRepository.save(user);
//    }

}
