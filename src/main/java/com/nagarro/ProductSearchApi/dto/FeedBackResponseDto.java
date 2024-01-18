package com.nagarro.ProductSearchApi.dto;

import java.util.List;

import com.nagarro.ProductSearchApi.model.FeedBack;

public class FeedBackResponseDto {

	
	  // Fields from User class
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // Fields from FeedBack class
    private List<FeedBack> feedbackList;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<FeedBack> getFeedbackList() {
		return feedbackList;
	}

	public void setFeedbackList(List<FeedBack> feedbackList) {
		this.feedbackList = feedbackList;
	}
    
    
}
