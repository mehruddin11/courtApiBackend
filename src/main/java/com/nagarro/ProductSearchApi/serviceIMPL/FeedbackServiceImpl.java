package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.dto.FeedBackResponseDto;
import com.nagarro.ProductSearchApi.model.FeedBack;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.FeedbackRepository;
import com.nagarro.ProductSearchApi.service.FeedBackService;




@Service
public class FeedbackServiceImpl implements FeedBackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Override
	public FeedBack saveFeedback(FeedBack feedback) {
		return feedbackRepository.save(feedback);
	}
	
	
	 @Override
	  public List<FeedBack> getAllFeedbackByUserId(Long userId) {
	        return feedbackRepository.findAllByUserId(userId);
	  }

	 @Override
	 public List<FeedBackResponseDto> getAllFeedbackWithUserDetails() {
	     List<FeedBack> feedbackList = feedbackRepository.findAll();
	     List<FeedBackResponseDto> feedBackResponseDtoList = new ArrayList<>();

	     for (FeedBack feedback : feedbackList) {
	         FeedBackResponseDto feedBackResponseDto = new FeedBackResponseDto();

	         // Set user details directly from the feedback's associated user
	         User user = feedback.getUser();

	         // Set only the necessary user details in the response DTO
	         feedBackResponseDto.setId(user.getId());
	         feedBackResponseDto.setFirstName(user.getFirstName());
	         feedBackResponseDto.setLastName(user.getLastName());
	         feedBackResponseDto.setEmail(user.getEmail());

	         // Set feedback details
	         feedBackResponseDto.setFeedbackList(Collections.singletonList(feedback));

	         feedBackResponseDtoList.add(feedBackResponseDto);
	     }

	     return feedBackResponseDtoList;
	 }


	 

	   

}
