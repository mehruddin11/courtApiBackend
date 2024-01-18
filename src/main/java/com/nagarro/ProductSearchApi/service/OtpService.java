package com.nagarro.ProductSearchApi.service;

import com.nagarro.ProductSearchApi.model.OtpModel;

public interface OtpService {

	 OtpModel generateOtp(String mobileNumber);

	    boolean verifyOtp(String mobileNumber, String otp);
}
