package com.nagarro.ProductSearchApi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/package")
public class PackageController {
	
	
	@GetMapping
	public String getText() {
		return "hello";
	}
	
	

}
