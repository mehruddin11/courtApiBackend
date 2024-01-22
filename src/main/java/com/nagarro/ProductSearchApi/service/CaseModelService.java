package com.nagarro.ProductSearchApi.service;

import java.util.List;

import com.nagarro.ProductSearchApi.model.CaseModel;

public interface CaseModelService {

	 CaseModel findById(Long id);

	    List<CaseModel> findByUserId(Long userId);

	    CaseModel findByCaseNumber(String caseNumber);

	    CaseModel findByCnrNumber(String cnrNumber);

	    List<CaseModel> getAllCaseModels();

	    CaseModel createCaseModel(CaseModel caseModel);
}
