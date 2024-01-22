package com.nagarro.ProductSearchApi.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "District is required")
    private String district;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Mobile number is required")
    private String mobileNumber;

   
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('ADVOCATE', 'USER', 'OFFICE')")
    private UserCategory category;
    
    private boolean isCredentaialCreated;
    
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FeedBack> feedbackList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complain> complainList = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSelectedPackage> userPackagesList = new ArrayList<>();
  
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CaseModel> caseModels = new ArrayList<>();
    
    
//	public List<FeedBack> getFeedbackList() {
//		return feedbackList;
//	}
//


//
//	public void setFeedbackList(List<FeedBack> feedbackList) {
//		this.feedbackList = feedbackList;
//	}
//
//
//
//
//	public List<Complain> getComplainList() {
//		return complainList;
//	}
//
//
//
//
//	public void setComplainList(List<Complain> complainList) {
//		this.complainList = complainList;
//	}



//
//	public List<UserSelectedPackage> getUserPackagesList() {
//		return userPackagesList;
//	}
//
//	
//	
//
//	public void setUserPackagesList(List<UserSelectedPackage> userPackagesList) {
//		this.userPackagesList = userPackagesList;
//	}


//	public List<Transaction> getTransactions() {
//		return transactions;
//	}


//	public void setTransactions(List<Transaction> transactions) {
//		this.transactions = transactions;
//	}


	@Nullable
    private String username;

    private String profession;
    
     @Nullable
    private String password;
    
 
    private boolean isPasswordRest;
    
	private String roles;
	
	private boolean isVerified;
	
	

	public Long getId() {
		return id;
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
	
	
	



	public boolean isPasswordRest() {
		return isPasswordRest;
	}


	public void setPasswordRest(boolean isPasswordRest) {
		this.isPasswordRest = isPasswordRest;
	}


	public UserCategory getCategory() {
		return category;
	}

	public void setCategory(UserCategory category) {
		this.category = category;
	}

	public String getUsername() {
		return username;
	}

	
    public void setUsername(@Nullable String newUsername) {
        this.username = newUsername;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}
	

	public boolean isCredentaialCreated() {
		return isCredentaialCreated;
	}

	public void setCredentaialCreated(boolean isCredentaialCreated) {
		this.isCredentaialCreated = isCredentaialCreated;
	}
	
	public User() {
		super();
	}


	public User(Long id, @NotBlank(message = "First name is required") String firstName,
			@NotBlank(message = "Last name is required") String lastName,
			@NotBlank(message = "State is required") String state,
			@NotBlank(message = "District is required") String district,
			@NotBlank(message = "City is required") String city,
			@NotBlank(message = "Address is required") String address,
			@NotBlank(message = "Email is required") String email,
			@NotBlank(message = "Mobile number is required") String mobileNumber, UserCategory category,
			boolean isCredentaialCreated, String username, String profession, String password, boolean isPasswordRest,
			String roles, boolean isVerified) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.state = state;
		this.district = district;
		this.city = city;
		this.address = address;
		this.email = email;
		this.mobileNumber = mobileNumber;
		this.category = category;
		this.isCredentaialCreated = isCredentaialCreated;
		this.username = username;
		this.profession = profession;
		this.password = password;
		this.isPasswordRest = isPasswordRest;
		this.roles = roles;
		this.isVerified = isVerified;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", state=" + state
				+ ", district=" + district + ", city=" + city + ", address=" + address + ", email=" + email
				+ ", mobileNumber=" + mobileNumber + ", category=" + category + ", isCredentaialCreated="
				+ isCredentaialCreated + ", username=" + username + ", profession=" + profession + ", password="
				+ password + ", isPasswordRest=" + isPasswordRest + ", roles=" + roles + ", isVerified=" + isVerified
				+ "]";
	}
	
	
	

	
}

