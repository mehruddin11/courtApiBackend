package com.nagarro.ProductSearchApi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Complain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id ;
	private String message;
	private String topics;
	
	 @Temporal(TemporalType.TIMESTAMP)
	 @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	 private Date createdAt;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	 @Enumerated(EnumType.STRING)
	 @Column(columnDefinition = "ENUM('PENDING', 'RECEIVED', 'RESOLVED', 'UNRESOLVED', 'CLOSED')")
	 private ComplainStatus status;
	 
	 
	 @PrePersist
	    protected void onCreate() {
	        createdAt = new Date();
	    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ComplainStatus getStatus() {
		return status;
	}

	public void setStatus(ComplainStatus status) {
		this.status = status;
	}
	 
	 
	 
	 

}
