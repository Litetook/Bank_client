package com.pragmatic.dao.rowmapper;

import com.pragmatic.model.Currency;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CurrencyRowMapper implements RowMapper<Currency> {

    @Override
    public Currency mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Currency.builder()
                .id(rs.getLong("currency_id"))
                .symbol(rs.getString("symbol"))
                .build();

    }


}



