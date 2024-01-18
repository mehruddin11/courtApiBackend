package com.nagarro.ProductSearchApi.dto;

public class ForgotPassword {

	private String mobileNumber;

	public String getMobileNumber() {
		return mobileNumber;
	}
	private boolean isOptVerified;

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public ForgotPassword() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ForgotPassword(String mobileNumber) {
		super();
		this.mobileNumber = mobileNumber;
	}

	public boolean isOptVerified() {
		return isOptVerified;
	}

	public void setOptVerified(boolean isOptVerified) {
		this.isOptVerified = isOptVerified;
	}
	
	
}
