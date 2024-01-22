package com.nagarro.ProductSearchApi.model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class UserSelectedPackage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private int packageExpirey;
	private boolean is_expired;
	private boolean limitreach;
	
	@ManyToOne
	@JoinColumn(name = "user_package_id") 
	private UserPackages userPackages;
	
	@ManyToOne 
	 @JoinColumn(name = "user_id")
    private User user;
	

	public UserPackages getUserPackages() {
		return userPackages;
	}

	public void setUserPackages(UserPackages userPackages) {
		this.userPackages = userPackages;
	}

	

	public void setUser(User user) {
		this.user = user;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getPackageExpirey() {
		return packageExpirey;
	}

	public void setPackageExpirey(int packageExpirey) {
		this.packageExpirey = packageExpirey;
	}

	public boolean isIs_expired() {
		return is_expired;
	}

	public void setIs_expired(boolean is_expired) {
		this.is_expired = is_expired;
	}

	public boolean isLimitreach() {
		return limitreach;
	}

	public void setLimitreach(boolean limitreach) {
		this.limitreach = limitreach;
	}

}
