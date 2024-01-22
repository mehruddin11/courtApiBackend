package com.nagarro.ProductSearchApi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.nagarro.ProductSearchApi.model.CaseModel;

public interface CaseModelRepository extends JpaRepository<CaseModel, Long> {

    @Query("SELECT c FROM CaseModel c WHERE c.user.id = :userId")
    List<CaseModel> findByUserId(@Param("userId") Long userId);

    Optional<CaseModel> findByCaseNumber(String caseNumber);

    Optional<CaseModel> findByCnrNumber(String cnrNumber);
}
