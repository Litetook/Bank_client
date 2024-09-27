package com.pragmatic.dao.rowmapper;

import com.pragmatic.model.Account;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper  implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Account.builder()
                .id(rs.getInt("account_id"))
                .userId(rs.getInt("user_id"))
                .balance(rs.getDouble("balance"))
                .currencyId(rs.getInt("currency_id"))
                .build();
    }


}