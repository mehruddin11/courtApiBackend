package com.nagarro.ProductSearchApi.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ProductSearchApi.model.OtpModel;

public interface OtpRepository extends JpaRepository<OtpModel, Long> {
	 OtpModel findByMobileNumber(String mobileNumber);

	void deleteByCreatedAtBefore(LocalDateTime twoMinutesAgo);
}
