package com.nagarro.ProductSearchApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.ProductSearchApi.model.FeedBack;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Long> {
	
	 List<FeedBack> findAllByUserId(Long userId);
	 

}
