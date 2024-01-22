package com.nagarro.ProductSearchApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.model.CaseModel;
import com.nagarro.ProductSearchApi.service.CaseModelService;

@RestController
@RequestMapping("/api/casemodels")
public class CaseModelController {

    @Autowired
    private CaseModelService caseModelService;

    @GetMapping("/search")
    public ResponseEntity<?> searchCaseModels(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String caseNumber,
            @RequestParam(required = false) String cnrNumber
    ) {
        if (id != null) {
            CaseModel caseModel = caseModelService.findById(id);
            return ResponseEntity.ok(caseModel);
        } else if (userId != null) {
            List<CaseModel> caseModels = caseModelService.findByUserId(userId);
            return ResponseEntity.ok(caseModels);
        } else if (caseNumber != null) {
            // Fetch by Case Number
            CaseModel caseModel = caseModelService.findByCaseNumber(caseNumber);
            return ResponseEntity.ok(caseModel);
        } else if (cnrNumber != null) {
            // Fetch by CNR Number
            CaseModel caseModel = caseModelService.findByCnrNumber(cnrNumber);
            return ResponseEntity.ok(caseModel);
        } else {
            // If no parameters are provided, retrieve all case models
            List<CaseModel> allCaseModels = caseModelService.getAllCaseModels();
            return ResponseEntity.ok(allCaseModels);
        }
    }

    
    @PostMapping
    public ResponseEntity<?> createCaseModel(@RequestBody CaseModel caseModel) {
        try {
           
            CaseModel createdCaseModel = caseModelService.createCaseModel(caseModel);
            return ResponseEntity.ok(createdCaseModel);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create CaseModel: " + e.getMessage());
        }
    }
}

