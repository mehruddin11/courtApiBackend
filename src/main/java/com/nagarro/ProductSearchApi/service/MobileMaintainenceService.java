package com.nagarro.ProductSearchApi.service;

import java.util.List;

import com.nagarro.ProductSearchApi.model.MobileMaintainenceModel;

public interface MobileMaintainenceService {
    List<MobileMaintainenceModel> getAllMobileMaintenances();

    MobileMaintainenceModel getMobileMaintenanceById(long id);

    MobileMaintainenceModel createMobileMaintenance(MobileMaintainenceModel mobileMaintenance);

    boolean deleteMobileMaintenance(long id);
}
