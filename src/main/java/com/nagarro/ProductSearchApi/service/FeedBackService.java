package com.nagarro.ProductSearchApi.service;

import java.util.List;

import com.nagarro.ProductSearchApi.dto.FeedBackResponseDto;
import com.nagarro.ProductSearchApi.model.FeedBack;


public interface FeedBackService {

	FeedBack saveFeedback(FeedBack feedback);
	List<FeedBack> getAllFeedbackByUserId(Long userId);
	
	 List<FeedBackResponseDto> getAllFeedbackWithUserDetails();
	
	
}
