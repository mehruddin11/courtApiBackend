package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.exception.DuplicateEntryException;
import com.nagarro.ProductSearchApi.model.MobileMaintainenceModel;
import com.nagarro.ProductSearchApi.repository.MobileMaintainenceRepository;
import com.nagarro.ProductSearchApi.service.MobileMaintainenceService;

@Service
public class MobileMaintainenceServiceImpl implements MobileMaintainenceService {

    @Autowired
    private MobileMaintainenceRepository mobileMaintainenceRepository;

    @Override
    public List<MobileMaintainenceModel> getAllMobileMaintenances() {
        return mobileMaintainenceRepository.findAll();
    }

    @Override
    public MobileMaintainenceModel getMobileMaintenanceById(long id) {
        Optional<MobileMaintainenceModel> optionalMobileMaintenance = mobileMaintainenceRepository.findById(id);
        return optionalMobileMaintenance.orElse(null);
    }

    @Override
    public MobileMaintainenceModel createMobileMaintenance(MobileMaintainenceModel mobileMaintenance) {
        try {
            // Check if a MobileMaintainenceModel with the same number already exists
            Optional<MobileMaintainenceModel> existingMobile = mobileMaintainenceRepository.findByNumbers(mobileMaintenance.getNumbers());

            if (existingMobile.isPresent()) {
                // Duplicate entry found, throw exception
                throw new DuplicateEntryException("Mobile number must be unique");
            }

            // No duplicate found, proceed with saving
            return mobileMaintainenceRepository.save(mobileMaintenance);
        } catch (DataIntegrityViolationException e) {
            // Handle other data integrity violations
            throw new RuntimeException("Error creating MobileMaintenance", e);
        }
    }


    @Override
    public boolean deleteMobileMaintenance(long id) {
        try {
            mobileMaintainenceRepository.deleteById(id);
            return true; // Deletion successful
        } catch (EmptyResultDataAccessException e) {
            return false; // Record with given ID not found
        }
    }

}

