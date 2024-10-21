package com.pragmatic.dao.rowmapper;

import com.pragmatic.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper  implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Account.builder()
                .id(rs.getLong("account_id"))
                .userId(rs.getLong("user_id"))
                .balance(rs.getBigDecimal("balance"))
                .currencyId(rs.getLong("currency_id"))
                .build();
    }


}