package com.pragmatic.dao.rowmapper;

import com.pragmatic.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TransactionRowMapper implements RowMapper<Transaction> {

    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Transaction.builder()
                .transactionId(rs.getInt("transaction_id"))
                .sourceAccountId(rs.getInt("source_account_id"))
                .destinationAccountId(rs.getInt("destination_account_id"))
                .amount(rs.getDouble("amount"))
                .actionDate(rs.getDate("action_date"))
                .build();
    }
}
