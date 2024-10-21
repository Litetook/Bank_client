package com.pragmatic.dao.impl;

import com.pragmatic.dao.TransactionDao;
import com.pragmatic.dao.rowmapper.TransactionRowMapper;
import com.pragmatic.model.Transaction;
import com.pragmatic.sql.SqlQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

import static org.springframework.util.StreamUtils.copyToString;

@Log4j2
@Repository
public class TransactionDaoImpl  extends NamedParameterJdbcDaoSupport implements TransactionDao {
    private static final String TRANSACTION_ID_PLACEHOLDER = "transactionId";
    private static final String SOURCE_ACCOUNT_ID_PLACEHOLDER = "sourceAccountId";
    private static final String DESTINATION_ACCOUNT_ID_PLACEHOLDER = "destinationAccountId";
    private static final String AMOUNT_PLACEHOLDER = "amount";
    private static final String ACTION_DATE_PLACEHOLDER = "actionDate";
//    CUSTOM FOR WHERE CONDITION TO FIND BY DATE
    private static final String DATE_FROM_PLACEHOLDER = "dateFrom";
    private static final String DATE_TO_PLACEHOLDER = "dateTo";
    private static final String ACCOUNT_ID_PLACEHOLDER = "accountId";

    private final String findTransactionByIdSql;
    private final String findTransactionsByDateRangeSql;
    //private final String findAllSql;
    private final String createTransactionSql;

    TransactionRowMapper transactionRowMapper = new TransactionRowMapper();

    public TransactionDaoImpl (
            DataSource dataSource,
            @Value("classpath:/db/sql/transactionDao/findTransactionById.sql") Resource findTransactionByIdSqlResource,
            @Value("classpath:/db/sql/transactionDao/findTransactionsByDateRange.sql") Resource findTransactionsByDateRangeSqlResource,
//            @Value("classpath:/db/sql/transactionDao/findAllTransactions.sql") Resource findAllSqlResource,
            @Value("classpath:/db/sql/transactionDao/createTransaction.sql") Resource createTransactionSqlResource
            ) throws IOException {
        this.setDataSource(dataSource);
        this.createTransactionSql = copyToString(
                createTransactionSqlResource.getInputStream(),
                Charset.defaultCharset()
        );

        this.findTransactionByIdSql= copyToString(
                findTransactionByIdSqlResource.getInputStream(),
                Charset.defaultCharset()
        );

//        this.findAllSql = copyToString(
//                findAllSqlResource.getInputStream(),
//                Charset.defaultCharset()
//        );

        this.findTransactionsByDateRangeSql = copyToString(
                findTransactionsByDateRangeSqlResource.getInputStream(),
                Charset.defaultCharset()
        );

    }


    private Long insert(SqlQuery sqlQuery) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .update(
                        sqlQuery.getQuery(),
                        sqlQuery.getParams(),
                        keyHolder,
                        new String[]{TRANSACTION_ID_PLACEHOLDER}
                );
        return  keyHolder.getKey().longValue();
    }

    @Override
    public Transaction save(Transaction transaction) {
        SqlQuery sqlQuery = SqlQuery
                .builder()
                .query(createTransactionSql)
                .param(SOURCE_ACCOUNT_ID_PLACEHOLDER, transaction.getSourceAccountId())
                .param(DESTINATION_ACCOUNT_ID_PLACEHOLDER, transaction.getDestinationAccountId())
                .param(AMOUNT_PLACEHOLDER, transaction.getAmount())
                .param(ACTION_DATE_PLACEHOLDER, transaction.getActionDate())
                .build();
        Long transactionId = insert(sqlQuery);
        transaction.setTransactionId(transactionId);
        return  transaction;
    }

    private Optional<Transaction> execTransactionQuery(SqlQuery sqlQuery) {
        log.trace(sqlQuery);

        var transactions = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(sqlQuery.getQuery(), sqlQuery.getParams(), transactionRowMapper);
        if (transactions.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(transactions.getFirst());
    }

    private List<Transaction> execTransactionsQuery(SqlQuery sqlQuery) {
        return Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(sqlQuery.getQuery(), sqlQuery.getParams(), transactionRowMapper);
    }


    @Override
    public Optional<Transaction> findTransactionById(Long transactionId) {
        if (transactionId == null) {throw new IllegalArgumentException("transactionId cannot be null");}
        if (transactionId <= 0) {throw new IllegalArgumentException("transactionId cannot be zero or less");}
        SqlQuery sqlQuery = SqlQuery.builder()
                .query(findTransactionByIdSql)
                .param(TRANSACTION_ID_PLACEHOLDER, transactionId)
                .build();
        return  execTransactionQuery(sqlQuery);
    }

    @Override
    public List<Transaction> findTransactionsByDateRange(Long accountId, Date dateFrom, Date dateTo) {
        if (accountId == null || dateFrom == null || dateTo == null) {throw new IllegalArgumentException("arg can not be null");}
        if (accountId<= 0) {throw new IllegalArgumentException("id can not be less than 0");}
        SqlQuery sqlQuery = SqlQuery.builder()
                .query(findTransactionsByDateRangeSql)
                .param(DATE_TO_PLACEHOLDER, dateTo)
                .param(DATE_FROM_PLACEHOLDER,dateFrom)
                .param(ACCOUNT_ID_PLACEHOLDER, accountId)
                .build();
        return  execTransactionsQuery(sqlQuery);
    }


//    @Override
//    public List<Transaction> findAll() {
//        SqlQuery sqlQuery = SqlQuery.builder()
//                .query(findAllSql)
//                .build();
//        return  execTransactionsQuery(sqlQuery);
//    }

}
