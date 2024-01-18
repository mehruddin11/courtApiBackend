package com.nagarro.ProductSearchApi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nagarro.ProductSearchApi.model.Complain;
import com.nagarro.ProductSearchApi.model.FeedBack;

public interface ComplainRepository  extends JpaRepository<Complain, Long> {
	List<Complain> findAllByUserId(Long userId);
}
