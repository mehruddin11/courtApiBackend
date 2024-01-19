package com.nagarro.ProductSearchApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.model.UserPackages;
import com.nagarro.ProductSearchApi.service.PackageService;

@RestController
@RequestMapping("/api/package")
public class PackageController {
	
	
	@Autowired
	private PackageService packageService;
	
	@GetMapping("/allpackages")
	public ResponseEntity<List<UserPackages>> getAllPackages() {
	    List<UserPackages> packages = packageService.getAllPackages();
	    

	    if (packages.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(packages, HttpStatus.OK);
	}
	
	
	 @GetMapping("/single/{id}")
	    public ResponseEntity<UserPackages> getSinglePackage(@PathVariable Long id) {
	        UserPackages singlePackage = packageService.getSinglePackageById(id);

	        if (singlePackage == null) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(singlePackage, HttpStatus.OK);
	    }


}
