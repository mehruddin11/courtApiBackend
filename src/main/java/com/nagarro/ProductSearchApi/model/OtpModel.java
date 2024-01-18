package com.nagarro.ProductSearchApi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OtpModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	private String mobileNumber;
    private String otp;
    private Date createdAt;
    
    
    
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public OtpModel(String mobileNumber, String otp) {
		super();
		this.mobileNumber = mobileNumber;
		this.otp = otp;
	}
	public OtpModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "OtpModel [mobileNumber=" + mobileNumber + ", otp=" + otp + "]";
	}
    
    

}
