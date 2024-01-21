package com.nagarro.ProductSearchApi.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.dto.AuthResponse;
import com.nagarro.ProductSearchApi.dto.ForgotPassword;
import com.nagarro.ProductSearchApi.dto.loginRequest;
import com.nagarro.ProductSearchApi.exception.DuplicateEmailException;
import com.nagarro.ProductSearchApi.exception.DuplicatePhoneNumberException;
import com.nagarro.ProductSearchApi.exception.InvalidCredentialsException;
import com.nagarro.ProductSearchApi.exception.UserNotFoundException;
import com.nagarro.ProductSearchApi.model.OtpModel;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.UserRepository;
import com.nagarro.ProductSearchApi.service.OtpService;
import com.nagarro.ProductSearchApi.service.UserService;

@RestController
@CrossOrigin("https://courtapptest.azurewebsites.net")
public class UserController {

    @Autowired
    private UserService userService;


    
    @Autowired
    private OtpService otpService;
    
	@Autowired
	private UserRepository userRepository;

    /*
    POST: Authenticate user
    */
//    @PostMapping("/authenticate")
//    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody loginRequest loginRequest) {
//        try {
//            String token = userService.loginUser(loginRequest.getUsername(),loginRequest.getPassword());
//            Map<String, Object> response = new HashMap<>();
//            response.put("token", token);
//            
//            return ResponseEntity.ok(response);
//        } catch (InvalidCredentialsException e) {
//            Map<String, Object> errorResponse = new HashMap<>();
//            errorResponse.put("error", e.getMessage());
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
//        }
//    }
	@PostMapping("/authenticate")
	public ResponseEntity<Map<String, Object>> loginUser(@RequestBody loginRequest loginRequest) {
	    try {
	        AuthResponse authResponse = userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
	        
	        Map<String, Object> response = new HashMap<>();
	        authResponse.getUser().setUsername(null);
//	        authResponse.getUser().setPassword(null);
	        response.put("token", authResponse.getToken());
	        response.put("user", authResponse.getUser());
	        
	        return ResponseEntity.ok(response);
	    } catch (InvalidCredentialsException e) {
	        Map<String, Object> errorResponse = new HashMap<>();
	        errorResponse.put("error", e.getMessage());
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
	    }
	}


    
    /*
     * 
    POST: Register user
    */
    
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> signUpUser(@RequestBody User user, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Validation failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            String token = userService.registerUser(user);
            Map<String, Object> response = new HashMap<>();
            response.put("user", user);
            response.put("token", token);
            response.put("status", 201);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (DuplicatePhoneNumberException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("nextStep", e.getNextStep());
            errorResponse.put("userId", e.getUserId()); 
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        } catch (DuplicateEmailException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("nextStep", e.getNextStep());
            errorResponse.put("userId", e.getUserId()); // Include userId in the response
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }
    
    
    @PutMapping("/update-profile/{userId}")
    public ResponseEntity<Map<String, Object>> updateProfile(
            @PathVariable Long userId,
            @RequestBody User updatedUserData,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", "Validation failed");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }

        try {
            // Assuming userService.updateUserProfile returns the updated user data
            User updatedUser = userService.updateUserProfile(userId, updatedUserData);

            Map<String, Object> response = new HashMap<>();
            response.put("user", updatedUser);
            response.put("status", 200);
            response.put("message", "Profile updated successfully");
            return ResponseEntity.status(HttpStatus.OK).body(response);

        } catch (UserNotFoundException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);

        } catch (DuplicatePhoneNumberException | DuplicateEmailException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }
    }



    
    @PostMapping("/createCredentials")
    public ResponseEntity<Object> createCredentials(@RequestParam("userId") Long userId,
                                                   @RequestParam("username") String username,
                                                   @RequestParam("password") String password) {

        // Validate userId, username, and password
        if (userId == null ) {
            return ResponseEntity.badRequest().body("Invalid userId. Please provide a valid positive number.");
        }

        if (username == null || username.isEmpty()) {
            return ResponseEntity.badRequest().body("Username cannot be empty.");
        }

        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Password cannot be empty.");
        }

        @SuppressWarnings("unchecked")
        
		Optional<User> userOptional = userService.findUserById(userId, username, password);
        
        Map<String, Object> response = new HashMap<>();
        
        if (userOptional.isPresent()) {
        	 response.put("message", "credentail created: ");
        	 response.put("status", 200);

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            
            response.put("message", "No user exists with the provided ID: " + userId);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
 
    
  
    @PostMapping("/forgot-password-otp")
    public ResponseEntity<Object> forgotPassword(@RequestBody ForgotPassword forgotPassword) {
        try {
            // Validate the input
            if (forgotPassword == null || forgotPassword.getMobileNumber() == null) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Invalid request"));
            }

           
            System.out.println("Received mobileNumber: " + forgotPassword.getMobileNumber());

            // Find the user by mobile number
            User user = userService.findByMobileNumber(forgotPassword.getMobileNumber());

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "User with this mobileNumber does not exist."));
            }

            Map<String, Object> response = new HashMap<>();

            if (user.isCredentaialCreated() && user.isVerified()) {
               
                otpService.generateOtp(forgotPassword.getMobileNumber());

                // You can include additional logic here to send the OTP through SMS or other means

                response.putAll(Collections.singletonMap("status", HttpStatus.OK.value()));
                response.putAll(Collections.singletonMap("message", "Reset OTP sent successfully."));
            } else if (!user.isCredentaialCreated()) {
                response.putAll(Collections.singletonMap("status", HttpStatus.BAD_REQUEST.value()));
                response.putAll(Collections.singletonMap("message", "Credentials not created for the user."));
                response.putAll(Collections.singletonMap("status", 401));
            } else if (!user.isVerified()) {
                response.putAll(Collections.singletonMap("status", HttpStatus.BAD_REQUEST.value()));
                response.putAll(Collections.singletonMap("message", "User is not verified."));
                response.putAll(Collections.singletonMap("status", 401));
            } else {
                response.putAll(Collections.singletonMap("status", HttpStatus.INTERNAL_SERVER_ERROR.value()));
                response.putAll(Collections.singletonMap("message", "Internal server error"));
                response.putAll(Collections.singletonMap("status", 500));
            }

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Internal server error"));
        }
    }


    
    @PostMapping("/verify-otp")
    public ResponseEntity<Object> verifyOtp(@RequestBody OtpModel otpModel) {
        try {
            if (otpModel == null || otpModel.getMobileNumber() == null || otpModel.getOtp() == null) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Invalid request"));
            }

            System.out.println("Received mobileNumber: " + otpModel.getMobileNumber());

            User user = userService.findByMobileNumber(otpModel.getMobileNumber());

            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "User with this mobileNumber does not exist."));
            }

            boolean isOtpValid = otpService.verifyOtp(otpModel.getMobileNumber(), otpModel.getOtp());

            if (isOtpValid) {
                Map<String, Object> response = new HashMap<>();
                response.put("message", "OTP verification successful.");
                response.put("status", 200);
                response.put("userId", user.getId());

                user.setPasswordRest(true);
                userRepository.save(user);

                return ResponseEntity.ok(response);
            } else {
                // Invalid OTP
            	Map<String, Object> response = new HashMap<>();
            	response.put("status", 400);
            	  return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Map<String, Object> response = new HashMap<>();
        	response.put("status", 500);
        	  return ResponseEntity.ok(response);
        	
        }
    }

    
    @PostMapping("/reset-password")
    public ResponseEntity<Object> resetPassword(@RequestParam Long userId, @RequestParam String newPassword) {
        try {
            // Validate inputs
            if (userId == null || newPassword == null || newPassword.isEmpty()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message", "Invalid request. userId and newPassword are required."));
            }

            @SuppressWarnings("unchecked")
			Optional<User> updatedUser = userService.restPassword(userId, newPassword);

            // Check if the user was found and the password was updated
            if (updatedUser.isPresent()) {
                // Include user ID in the response
                Long updatedUserId = updatedUser.get().getId();
                Map<String, Object> response = new HashMap<>();
                response.put("userId", updatedUserId);
                response.put("message", "Password reset successful.");

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("message", "User not found with the provided ID."));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "Internal server error"));
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}

// Existing code

//    private void sendResetEmail(String email, String resetToken) {
//        // Email configuration
//        String host = "your_email_host"; // e.g., "smtp.gmail.com"
//        String port = "your_email_port"; // e.g., "587"
//        String username = "your_email_username";
//        String password = "your_email_password";
//
//        // Sender's email address
//        String from = "your_email_address";
//
//        // Recipient's email address
//        String to = email;
//
//        // Set properties for the email server
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", "true");
//        properties.put("mail.smtp.starttls.enable", "true");
//        properties.put("mail.smtp.host", host);
//        properties.put("mail.smtp.port", port);
//
//        
//        Session session = Session.getInstance(properties, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            // Create a MimeMessage object
//            Message message = new MimeMessage(session);
//
//            // Set the sender's and recipient's email addresses
//            message.setFrom(new InternetAddress(from));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//
//            // Set the email subject
//            message.setSubject("Password Reset");
//
//            // Set the email content
//            String emailContent = "Click the following link to reset your password: "
//                    + "http://your_reset_password_endpoint?token=" + resetToken;
//            message.setText(emailContent);
//
//            // Send the email
//            Transport.send(message);
//
//            System.out.println("Reset email sent successfully.");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//    
    
//    @PostMapping()
//    public ResponseEntity<Object> resetPassword(@RequestParam("token") String token,
//                                               @RequestParam("password") String password) {
//        try {
//            // Validate the reset token
//            String email = jwtTokenUtil.getUsernameFromToken(token);
//            if (jwtTokenUtil.validateResetToken(token, email)) {
//                // Update the password
//                userService.resetPassword(email, password);
//                return ResponseEntity.ok("Password reset successfully.");
//            } else {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid or expired token.");
//            }
//        } catch (UserNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//    }






//    @GetMapping("/user")
//    @PreAuthorize("hasAuthority('USER')")
//    public ResponseEntity<Map<String, Object>> userRole() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "User has access");
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('ADMIN')")
//    public ResponseEntity<Map<String, Object>> adminRole() {
//        Map<String, Object> response = new HashMap<>();
//        response.put("message", "Admin has access");
//        return ResponseEntity.ok(response);
//    }
    


//@PostMapping("/register")
//public ResponseEntity<Map<String, Object>> signUpUser(@Validated @RequestBody User user, BindingResult bindingResult) {
//
//  if (bindingResult.hasErrors()) {
//      Map<String, Object> errorResponse = new HashMap<>();
//      errorResponse.put("error", "Validation failed");
//      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
//  }
//
//  try {
//      String token = userService.registerUser(user);
//      Map<String, Object> response = new HashMap<>();
//      response.put("token", token);
//      return new ResponseEntity<>(response, HttpStatus.CREATED);
//  } catch (DuplicateEmailException e) {
//      Map<String, Object> errorResponse = new HashMap<>();
//      errorResponse.put("error", e.getMessage());
//      return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
//  }
//}
