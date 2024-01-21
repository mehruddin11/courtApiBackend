package com.nagarro.ProductSearchApi.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.model.UserSelectedPackage;
import com.nagarro.ProductSearchApi.service.UserSelectedPackageService;


@RestController
@RequestMapping("/user-selected-packages")
public class UserSelectedPackageController {

    @Autowired
    private UserSelectedPackageService userSelectedPackageService;

    @PostMapping
    public ResponseEntity<UserSelectedPackage> createUserSelectedPackage(@RequestBody UserSelectedPackage userSelectedPackage) {
        UserSelectedPackage createdPackage = userSelectedPackageService.createUserSelectedPackage(userSelectedPackage);
        return new ResponseEntity<>(createdPackage, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserSelectedPackage> getUserSelectedPackage(@PathVariable long id) {
        UserSelectedPackage userSelectedPackage = userSelectedPackageService.getUserSelectedPackageById(id);
        return new ResponseEntity<>(userSelectedPackage, HttpStatus.OK);
    }

   
}
