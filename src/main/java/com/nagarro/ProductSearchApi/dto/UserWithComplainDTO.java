package com.nagarro.ProductSearchApi.dto;

import java.util.List;

public class UserWithComplainDTO {

	  private Long id;
	    private String firstName;
	    private String lastName;
	    private String state;
	    private String district;
	    private String city;
	    private String address;
	    private String email;
	    private String mobileNumber;
	    private String category;
	    private String profession;
	    private boolean isVerified;
	   
	    private List<ComplainDTO> complainList;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}

		public String getLastName() {
			return lastName;
		}

		public void setLastName(String lastName) {
			this.lastName = lastName;
		}

		public String getState() {
			return state;
		}

		public void setState(String state) {
			this.state = state;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public String getCategory() {
			return category;
		}

		public void setCategory(String category) {
			this.category = category;
		}

		public String getProfession() {
			return profession;
		}

		public void setProfession(String profession) {
			this.profession = profession;
		}

		public boolean isVerified() {
			return isVerified;
		}

		public void setVerified(boolean isVerified) {
			this.isVerified = isVerified;
		}

		public List<ComplainDTO> getComplainList() {
			return complainList;
		}

		public void setComplainList(List<ComplainDTO> complainList) {
			this.complainList = complainList;
		}

		
	    
}
