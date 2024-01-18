package com.nagarro.ProductSearchApi.dto;

import java.util.Date;

import com.nagarro.ProductSearchApi.model.ComplainStatus;

public class ComplainDTO {
    private Long id;
    private String message;
    private String topics;
    private Date createdAt;
    private ComplainStatus status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTopics() {
		return topics;
	}
	public void setTopics(String topics) {
		this.topics = topics;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public ComplainStatus getStatus() {
		return status;
	}
	public void setStatus(ComplainStatus status) {
		this.status = status;
	}

   
	
}

