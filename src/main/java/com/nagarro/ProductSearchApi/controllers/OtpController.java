package com.nagarro.ProductSearchApi.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.model.OtpModel;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.UserRepository;
import com.nagarro.ProductSearchApi.service.OtpService;
import com.nagarro.ProductSearchApi.service.UserService;

@RestController
@CrossOrigin("https://courtapptest.azurewebsites.net")
public class OtpController {

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;
    
    @Autowired
	private UserRepository userRepository;

    @PostMapping("/generate")
    public ResponseEntity<Map<String, String>> generateOtp(@RequestBody OtpModel otpRequest) {
        try {
            User user = userService.findByMobileNumber(otpRequest.getMobileNumber());

            if (user != null) {
                OtpModel generatedOtp = otpService.generateOtp(user.getMobileNumber());

                Map<String, String> response = new HashMap<>();
                response.put("message", "OTP generated successfully");
               
                response.put("otp", generatedOtp.getOtp());
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, String> response = new HashMap<>();
                response.put("error", "User not found for the provided mobile number");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to generate OTP");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/verify")
    public ResponseEntity<Map<String, Object>> verifyOtp(@RequestBody OtpModel otpVerificationRequest) {
        try {
        	if (otpService.verifyOtp(otpVerificationRequest.getMobileNumber(), otpVerificationRequest.getOtp())) {
        	    Map<String, Object> response = new HashMap<>();
        	    User user = userService.findByMobileNumber(otpVerificationRequest.getMobileNumber());
        	    response.put("message", "OTP verified successfully");
        	    response.put("status", 200);
        	    user.setVerified(true);
        	    userRepository.save(user);
        	    return ResponseEntity.status(HttpStatus.OK).body(response);
        	    
        	} else {
                Map<String, Object> response = new HashMap<>();
                response.put("error", "Invalid OTP");
                response.put("status", 400);
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
              
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "Failed to verify OTP");
            response.put("status", 500);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
