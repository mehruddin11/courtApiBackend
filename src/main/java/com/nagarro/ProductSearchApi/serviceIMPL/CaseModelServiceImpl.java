package com.nagarro.ProductSearchApi.serviceIMPL;

// CaseModelServiceImpl.java


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.model.CaseModel;
import com.nagarro.ProductSearchApi.repository.CaseModelRepository;
import com.nagarro.ProductSearchApi.service.CaseModelService;

@Service
public class CaseModelServiceImpl implements CaseModelService {

    @Autowired
    private CaseModelRepository caseModelRepository;

    @Override
    public CaseModel findById(Long id) {
        return caseModelRepository.findById(id).orElse(null);
    }

    @Override
    public List<CaseModel> findByUserId(Long userId) {
        return caseModelRepository.findByUserId(userId);
    }

    @Override
    public CaseModel findByCaseNumber(String caseNumber) {
        return caseModelRepository.findByCaseNumber(caseNumber).orElse(null);
       
    }

    @Override
    public CaseModel findByCnrNumber(String cnrNumber) {
        return caseModelRepository.findByCnrNumber(cnrNumber).orElse(null);
       
    }

    @Override
    public List<CaseModel> getAllCaseModels() {
        return caseModelRepository.findAll();
    }

    @Override
    public CaseModel createCaseModel(CaseModel caseModel) {
      
        return caseModelRepository.save(caseModel);
    }
}
