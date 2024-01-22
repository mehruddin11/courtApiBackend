package com.nagarro.ProductSearchApi.service;

import java.util.List;
import com.nagarro.ProductSearchApi.model.Transaction;

public interface TransactionService {
    List<Transaction> getAllTransactions();
    Transaction getTransactionById(long id);
    Transaction getTransactionByTransactionId(long transactionId);
    Transaction createTransaction(Transaction transaction);
}
