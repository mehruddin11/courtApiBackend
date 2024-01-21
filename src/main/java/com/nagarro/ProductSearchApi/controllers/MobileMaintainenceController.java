package com.nagarro.ProductSearchApi.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.nagarro.ProductSearchApi.exception.DuplicateEntryException;
import com.nagarro.ProductSearchApi.model.MobileMaintainenceModel;
import com.nagarro.ProductSearchApi.service.MobileMaintainenceService;

@RestController
@RequestMapping("/api/mobilemaintenances")
public class MobileMaintainenceController {

    @Autowired
    private MobileMaintainenceService mobileMaintainenceService;

    @GetMapping
    public ResponseEntity<List<MobileMaintainenceModel>> getAllMobileMaintenances() {
        List<MobileMaintainenceModel> mobileMaintenances = mobileMaintainenceService.getAllMobileMaintenances();
        return ResponseEntity.ok(mobileMaintenances);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getMobileMaintenanceById(@PathVariable long id) {
        MobileMaintainenceModel mobileMaintenance = mobileMaintainenceService.getMobileMaintenanceById(id);
        if (mobileMaintenance != null) {
            return ResponseEntity.ok(mobileMaintenance);
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Mobile Maintenance not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<Object> createMobileMaintenance(@RequestBody MobileMaintainenceModel mobileMaintenance) {
        try {
            MobileMaintainenceModel createdMobileMaintenance = mobileMaintainenceService.createMobileMaintenance(mobileMaintenance);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMobileMaintenance);
        } catch (DuplicateEntryException e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Duplicate entry. Mobile Maintenance already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteMobileMaintenance(@PathVariable long id) {
        boolean deleted = mobileMaintainenceService.deleteMobileMaintenance(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Mobile Maintenance not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
