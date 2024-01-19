package com.nagarro.ProductSearchApi.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.ProductSearchApi.dto.ComplainDTO;
import com.nagarro.ProductSearchApi.dto.ComplainResponseDto;
import com.nagarro.ProductSearchApi.dto.UserWithComplainDTO;
import com.nagarro.ProductSearchApi.model.Complain;
import com.nagarro.ProductSearchApi.model.ComplainStatus;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.service.ComplainService;
import com.nagarro.ProductSearchApi.service.UserService;

@RestController
@RequestMapping("/api/complain")
public class ComplainController {
	
	@Autowired
	private ComplainService complainservice;
	
	@Autowired
	private UserService userservice;
	
	@PostMapping("/saveComplain")
	public ResponseEntity<Complain> saveComplain(@RequestBody Complain complain) {
	    Complain savedComplain = complainservice.saveComplain(complain);
	    return new ResponseEntity<>(savedComplain, HttpStatus.CREATED);
	}
	

	@GetMapping("/{userId}")
	public ResponseEntity<UserWithComplainDTO> getUserWithFeedback(@PathVariable Long userId) {
	    User user = userservice.getUserById(userId);
	    List<Complain> complainList = complainservice.getAllComplain(userId);

	    UserWithComplainDTO userWithComplainDTO = new UserWithComplainDTO();
	    userWithComplainDTO.setId(user.getId());
	    userWithComplainDTO.setFirstName(user.getFirstName());
	    userWithComplainDTO.setLastName(user.getLastName());
	    userWithComplainDTO.setState(user.getState());
	    userWithComplainDTO.setDistrict(user.getDistrict());
	    userWithComplainDTO.setCity(user.getCity());
	    userWithComplainDTO.setAddress(user.getAddress());
	    userWithComplainDTO.setEmail(user.getEmail());
	    userWithComplainDTO.setMobileNumber(user.getMobileNumber());
	    userWithComplainDTO.setProfession(user.getProfession());
	    userWithComplainDTO.setVerified(user.isVerified());
	    userWithComplainDTO.setComplainList(complainList.stream()
	            .map(this::convertToComplainDTO)
	            .collect(Collectors.toList()));

	    return new ResponseEntity<>(userWithComplainDTO, HttpStatus.OK);
	}

	private ComplainDTO convertToComplainDTO(Complain complain) {
	    ComplainDTO complainDTO = new ComplainDTO();
	    complainDTO.setId(complain.getId());
	    complainDTO.setMessage(complain.getMessage());
	    complainDTO.setTopics(complain.getTopics());
	    complainDTO.setCreatedAt(complain.getCreatedAt());
	    complainDTO.setStatus(complain.getStatus());
	    return complainDTO;
	}
	
	// existing code

    @PutMapping("/updateStatus/{complainId}/{newStatus}")
    public ResponseEntity<String> updateComplainStatusById(
            @PathVariable Long complainId,
            @PathVariable ComplainStatus newStatus) {
        complainservice.updateComplainStatusById(complainId, newStatus);
        return new ResponseEntity<>("Complain status updated successfully.", HttpStatus.OK);
    }
    
    
	@GetMapping("/all")
    public ResponseEntity<List<ComplainResponseDto>> getAllFeedbackWithUserDetails() {
        List<User> userList = userservice.getAllUsers();
        List<ComplainResponseDto> userWithFeedbackDTOList = complainservice.getAllComplainWithUserDetails();

        for (ComplainResponseDto userWithFeedbackDTO : userWithFeedbackDTOList) {
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
