package com.nagarro.ProductSearchApi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MobileMaintainenceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String Numbers;
	private boolean isdeleted;
	private boolean isActive;
	
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNumbers() {
		return Numbers;
	}
	public void setNumbers(String numbers) {
		Numbers = numbers;
	}
	public boolean isIsdeleted() {
		return isdeleted;
	}
	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	
	

}
