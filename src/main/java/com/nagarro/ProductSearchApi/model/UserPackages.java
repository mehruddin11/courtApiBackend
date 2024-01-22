package com.nagarro.ProductSearchApi.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class UserPackages {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String packageName;
	private String packageDes;
	@Column(name = "packageLimit")
	private int limit;
	private int capacity;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date createdAt;

	private int amount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
	private Date modifiedAt;

	@Column(name = "is_active", nullable = true, columnDefinition = "tinyint(1)")
	private boolean isActive;

	@Column(name = "is_deleted", nullable = true, columnDefinition = "tinyint(1)")
	private boolean is_deleted;

	@OneToMany(mappedBy = "packages", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MobileMaintainenceModel> mobiles = new ArrayList<>();
	
	@OneToMany(mappedBy = "userPackages", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Transaction> transactions = new ArrayList<>();

	
	
	

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public List<MobileMaintainenceModel> getMobiles() {
		return mobiles;
	}

	public void setMobiles(List<MobileMaintainenceModel> mobiles) {
		this.mobiles = mobiles;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getPackageDes() {
		return packageDes;
	}

	public void setPackageDes(String packageDes) {
		this.packageDes = packageDes;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(Date modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

}
