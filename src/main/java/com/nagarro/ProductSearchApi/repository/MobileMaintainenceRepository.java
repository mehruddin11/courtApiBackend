package com.nagarro.ProductSearchApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nagarro.ProductSearchApi.model.MobileMaintainenceModel;

@Repository
public interface MobileMaintainenceRepository extends JpaRepository<MobileMaintainenceModel, Long> {
	
	
	 @Query("SELECT m FROM MobileMaintainenceModel m WHERE m.Numbers = :Numbers")
	  Optional<MobileMaintainenceModel> findByNumbers(String Numbers);
}

