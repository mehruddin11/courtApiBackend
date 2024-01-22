package com.nagarro.ProductSearchApi.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CaseModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String cnrNumber;
	private String caseNumber;
	private String partName;
	private Date nextDate;
	private String status;
	private String complaintName;
	private String purpose_of_hearing;
	private String business;
	private String respondentName;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	
	public void setUser(User user) {
		this.user = user;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCnrNumber() {
		return cnrNumber;
	}
	public void setCnrNumber(String cnrNumber) {
		this.cnrNumber = cnrNumber;
	}
	public String getCaseNumber() {
		return caseNumber;
	}
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	public String getPartName() {
		return partName;
	}
	public void setPartName(String partName) {
		this.partName = partName;
	}
	public Date getNextDate() {
		return nextDate;
	}
	public void setNextDate(Date nextDate) {
		this.nextDate = nextDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getComplaintName() {
		return complaintName;
	}
	public void setComplaintName(String complaintName) {
		this.complaintName = complaintName;
	}
	public String getPurpose_of_hearing() {
		return purpose_of_hearing;
	}
	public void setPurpose_of_hearing(String purpose_of_hearing) {
		this.purpose_of_hearing = purpose_of_hearing;
	}
	public String getBusiness() {
		return business;
	}
	public void setBusiness(String business) {
		this.business = business;
	}
	public String getRespondentName() {
		return respondentName;
	}
	public void setRespondentName(String respondentName) {
		this.respondentName = respondentName;
	}
	
	
}
