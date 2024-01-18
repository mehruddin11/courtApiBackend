package com.nagarro.ProductSearchApi.service;

import java.util.List;

import com.nagarro.ProductSearchApi.dto.ComplainResponseDto;
import com.nagarro.ProductSearchApi.model.Complain;
import com.nagarro.ProductSearchApi.model.ComplainStatus;

public interface ComplainService {

	Complain saveComplain(Complain complain);
	
	List<Complain> getAllComplain(Long userId);
     List<ComplainResponseDto> getAllComplainWithUserDetails();

	
	
	 void updateComplainStatusById(Long complainId, ComplainStatus newStatus);
}
