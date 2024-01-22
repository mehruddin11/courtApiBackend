package com.nagarro.ProductSearchApi.serviceIMPL;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nagarro.ProductSearchApi.model.Transaction;
import com.nagarro.ProductSearchApi.repository.TransactionRepository;
import com.nagarro.ProductSearchApi.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransactionById(long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public Transaction getTransactionByTransactionId(long transactionId) {
        return transactionRepository.findByTransactionId(transactionId);
    }

    @Override
    public Transaction createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
}
