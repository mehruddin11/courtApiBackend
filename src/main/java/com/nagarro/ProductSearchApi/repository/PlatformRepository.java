package com.nagarro.ProductSearchApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ProductSearchApi.model.Platform;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    // You can add custom queries if needed
}
