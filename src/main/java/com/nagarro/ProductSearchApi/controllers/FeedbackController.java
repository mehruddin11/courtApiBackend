package com.nagarro.ProductSearchApi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.dto.FeedBackResponseDto;
import com.nagarro.ProductSearchApi.dto.UserWithFeedbackDTO;
import com.nagarro.ProductSearchApi.model.FeedBack;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.service.FeedBackService;
import com.nagarro.ProductSearchApi.service.UserService;


@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {
	
	
	@Autowired
	private  FeedBackService feedbackService;
	
	@Autowired 
	private UserService userService;
	
	@PostMapping
    public ResponseEntity<FeedBack> saveFeedback(@RequestBody FeedBack feedback) {
		FeedBack savedFeedback = feedbackService.saveFeedback(feedback);
        return new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserWithFeedbackDTO> getUserWithFeedback(@PathVariable Long userId) {
	    User user = userService.getUserById(userId);
	    List<FeedBack> feedbackList = feedbackService.getAllFeedbackByUserId(userId);
	    UserWithFeedbackDTO userWithFeedbackDTO = new UserWithFeedbackDTO();
	    userWithFeedbackDTO.setId(user.getId());
	    userWithFeedbackDTO.setFirstName(user.getFirstName());
	    userWithFeedbackDTO.setLastName(user.getLastName());
	    userWithFeedbackDTO.setState(user.getState());
	    userWithFeedbackDTO.setDistrict(user.getDistrict());
	    userWithFeedbackDTO.setCity(user.getCity());
	    userWithFeedbackDTO.setAddress(user.getAddress());
	    userWithFeedbackDTO.setEmail(user.getEmail());
	    userWithFeedbackDTO.setMobileNumber(user.getMobileNumber());
	    userWithFeedbackDTO.setProfession(user.getProfession());
	    userWithFeedbackDTO.setVerified(user.isVerified());
	    userWithFeedbackDTO.setFeedbackList(feedbackList);

	    return new ResponseEntity<>(userWithFeedbackDTO, HttpStatus.OK);
	}
	
	@GetMapping("/all")
    public ResponseEntity<List<FeedBackResponseDto>> getAllFeedbackWithUserDetails() {
        List<User> userList = userService.getAllUsers();
        List<FeedBackResponseDto> userWithFeedbackDTOList = feedbackService.getAllFeedbackWithUserDetails();

        for (FeedBackResponseDto userWithFeedbackDTO : userWithFeedbackDTOList) {
            User user = userList.stream()
                                .filter(u -> u.getId().equals(userWithFeedbackDTO.getId()))
                                .findFirst()
                                .orElse(null);

            if (user != null) {
                userWithFeedbackDTO.setFirstName(user.getFirstName());
                userWithFeedbackDTO.setLastName(user.getLastName());
                userWithFeedbackDTO.setEmail(user.getEmail());
            }
        }

        return new ResponseEntity<>(userWithFeedbackDTOList, HttpStatus.OK);
    }
	
	
	
	


}
