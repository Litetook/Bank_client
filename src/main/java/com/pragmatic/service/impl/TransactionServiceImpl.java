package com.pragmatic.service.impl;

import com.pragmatic.dao.TransactionDao;
import com.pragmatic.dto.request.TransactionsByRangeRequest;
import com.pragmatic.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class TransactionServiceImpl {
    @Autowired
    TransactionDao transactionDao;

    public List<Transaction> findTransactionsByDateRange(TransactionsByRangeRequest request) {
        Date dateFrom = new Date(new Timestamp(request.getDateFrom()).getTime());
        Date dateTo = new Date(new Timestamp(request.getDateTo()).getTime());
        return transactionDao.findTransactionsByDateRange(request.getAccountId(), dateFrom, dateTo);
    }
}
