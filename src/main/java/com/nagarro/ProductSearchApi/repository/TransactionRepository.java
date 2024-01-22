package com.nagarro.ProductSearchApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nagarro.ProductSearchApi.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	  @Query("SELECT t FROM Transaction t WHERE t.transactionid = :transactionId")
	    Transaction findByTransactionId(@Param("transactionId") long transactionId);
}

