package com.nagarro.ProductSearchApi.service;

import java.util.List;

import com.nagarro.ProductSearchApi.model.UserPackages;

public interface PackageService {
	
	List<UserPackages> getAllPackages();
	
	 UserPackages getSinglePackageById(Long id);

}
