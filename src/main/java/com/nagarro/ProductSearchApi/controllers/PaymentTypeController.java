package com.nagarro.ProductSearchApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.model.Platform;
import com.nagarro.ProductSearchApi.service.PlatformService;

@RestController
@RequestMapping("/platform")
public class PaymentTypeController {
	

	@Autowired
    private PlatformService platformService;
	
    @GetMapping("/all")
    public ResponseEntity<List<Platform>> getAllPlatforms() {
        List<Platform> platforms = platformService.getAll();
        return ResponseEntity.ok(platforms);
    }

}
