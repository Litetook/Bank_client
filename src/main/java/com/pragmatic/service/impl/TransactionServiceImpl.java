package com.pragmatic.service.impl;

import com.pragmatic.dao.TransactionDao;
import com.pragmatic.dto.request.TransactionsByRangeRequest;
import com.pragmatic.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl {
    @Autowired
    TransactionDao transactionDao;

    public List<Transaction> findTransactionsByDateRange(TransactionsByRangeRequest request) {
        return transactionDao.findTransactionsByDateRange(request.getAccountId(), request.getDateFrom(), request.getDateTo());
    }
}
