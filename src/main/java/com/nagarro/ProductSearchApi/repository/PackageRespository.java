package com.nagarro.ProductSearchApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.ProductSearchApi.model.UserPackages;

@Repository
public interface PackageRespository  extends JpaRepository<UserPackages, Long> {

	
}
