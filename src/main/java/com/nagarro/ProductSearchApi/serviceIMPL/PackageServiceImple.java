package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.model.UserPackages;
import com.nagarro.ProductSearchApi.repository.PackageRespository;
import com.nagarro.ProductSearchApi.service.PackageService;


@Service
public class PackageServiceImple implements PackageService {

	@Autowired
	private PackageRespository packageRespositoty;
	@Override
	public List<UserPackages> getAllPackages() {
		// TODO Auto-generated method stub
		return packageRespositoty.findAll();
	}
	@Override
    public UserPackages getSinglePackageById(Long id) {
        // Implementation to get a single package by ID
        return packageRespositoty.findById(id).orElse(null);
    }

}
