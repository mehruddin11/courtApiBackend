package com.nagarro.ProductSearchApi.service;

import com.nagarro.ProductSearchApi.model.UserSelectedPackage;

public interface UserSelectedPackageService {

    UserSelectedPackage createUserSelectedPackage(UserSelectedPackage userSelectedPackage);

    UserSelectedPackage getUserSelectedPackageById(long id);

    // Add other service methods as needed
}
