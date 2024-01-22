package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.model.Platform;
import com.nagarro.ProductSearchApi.repository.PlatformRepository;
import com.nagarro.ProductSearchApi.service.PlatformService;

@Service
public class PlatformServiceImpl implements PlatformService {

	 @Autowired
	    private PlatformRepository platformRepository;

	@Override
	public List<Platform> getAll() {
		
		return platformRepository.findAll();
	}

}
