package com.nagarro.ProductSearchApi.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class UserPackages {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String packageName;
	private String packageDes;
	@Column(name="packageLimit")
	private int limit;
	private int capacity;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_at", nullable = false, updatable = false)
	private Date createdAt;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modified_at")
	private Date modifiedAt;
	
	@Column(name = "is_active", nullable = true, columnDefinition = "tinyint(1)")
	private boolean isActive;

	@Column(name = "is_deleted", nullable = true, columnDefinition = "tinyint(1)")
	private boolean is_deleted;

	
	public long getPackageId() {
		return id;
	}
	public String getPackageDes() {
		return packageDes;
	}

	public void setPackageDes(String packageDes) {
		this.packageDes = packageDes;
	}

	public void setPackageId(long id) {
		this.id = id;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
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
	public boolean isIs_active() {
		return isActive;
	}
	public void setIs_active(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean isIs_deleted() {
		return is_deleted;
	}
	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
	

}
