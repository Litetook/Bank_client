package com.pragmatic.dao;

import com.pragmatic.model.Account;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountRowMapper  implements RowMapper<Account> {

    @Override
    public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
        var account = Account.builder()
                .id(rs.getInt("accountid"))
                .userId(rs.getInt("userid"))
                .balance(rs.getDouble("balance"))
                .currencyId(rs.getInt("currencyid"))
                .build();
        return  account;
    }


}