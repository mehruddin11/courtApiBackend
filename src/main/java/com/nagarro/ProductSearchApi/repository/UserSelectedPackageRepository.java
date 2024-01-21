package com.nagarro.ProductSearchApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ProductSearchApi.model.UserSelectedPackage;

public interface UserSelectedPackageRepository extends JpaRepository<UserSelectedPackage, Long> {

}
