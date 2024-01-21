package com.nagarro.ProductSearchApi.serviceIMPL;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.model.UserSelectedPackage;
import com.nagarro.ProductSearchApi.repository.UserSelectedPackageRepository;
import com.nagarro.ProductSearchApi.service.UserSelectedPackageService;

@Service
public class UserSelectedPackageServiceImpl implements UserSelectedPackageService {

    @Autowired
    private UserSelectedPackageRepository userSelectedPackageRepository;

    @Override
    public UserSelectedPackage createUserSelectedPackage(UserSelectedPackage userSelectedPackage) {
        return userSelectedPackageRepository.save(userSelectedPackage);
    }

    @Override
    public UserSelectedPackage getUserSelectedPackageById(long id) {
        return userSelectedPackageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserSelectedPackage not found with id: " + id));
    }

    // Add other service methods as needed
}
