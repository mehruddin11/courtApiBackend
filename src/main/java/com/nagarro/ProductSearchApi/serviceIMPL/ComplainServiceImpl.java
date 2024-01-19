package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.dto.ComplainResponseDto;
import com.nagarro.ProductSearchApi.exception.ResourceNotFoundException;
import com.nagarro.ProductSearchApi.model.Complain;
import com.nagarro.ProductSearchApi.model.ComplainStatus;
import com.nagarro.ProductSearchApi.model.User;
import com.nagarro.ProductSearchApi.repository.ComplainRepository;
import com.nagarro.ProductSearchApi.service.ComplainService;


@Service
public class ComplainServiceImpl implements ComplainService {

	@Autowired
	private ComplainRepository complainrepo;
	
	@Override
	public Complain saveComplain(Complain complain) {
		// TODO Auto-generated method stub
		return complainrepo.save(complain);
	}

	
	@Override
	public List<Complain> getAllComplain(Long userId) {
		 return complainrepo.findAllByUserId(userId);
	}
	
	@Override
    public void updateComplainStatusById(Long complainId, ComplainStatus newStatus) {
        Complain complain = complainrepo.findById(complainId)
                .orElseThrow(() -> new ResourceNotFoundException("Complain not found with id: " + complainId));

        complain.setStatus(newStatus);
        complainrepo.save(complain);
    }



	@Override
	public List<ComplainResponseDto> getAllComplainWithUserDetails() {
	    List<Complain> complainList = complainrepo.findAll();
	    List<ComplainResponseDto> feedBackResponseDtoList = new ArrayList<>();

	    for (Complain complain : complainList) {
	        ComplainResponseDto feedBackResponseDto = new ComplainResponseDto();

	        User user = complain.getUser();

	        // Set only the necessary user details in the response DTO
	        feedBackResponseDto.setId(user.getId());
	        feedBackResponseDto.setFirstName(user.getFirstName());
	        feedBackResponseDto.setLastName(user.getLastName());
	        feedBackResponseDto.setEmail(user.getEmail());

	        // Set the complain list directly
	        List<Complain> singleComplainList = Collections.singletonList(complain);
	        feedBackResponseDto.setComplain(singleComplainList);

	        feedBackResponseDtoList.add(feedBackResponseDto);
	    }

	    return feedBackResponseDtoList;
	}



	
}
