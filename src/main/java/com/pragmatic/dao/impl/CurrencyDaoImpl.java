package com.pragmatic.dao.impl;


import com.pragmatic.dao.rowmapper.CurrencyRowMapper;
import com.pragmatic.model.Currency;
import com.pragmatic.sql.SqlQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.StreamUtils.copyToString;

@Log4j2
@Repository
public class CurrencyDaoImpl extends NamedParameterJdbcDaoSupport {
    private static final String CURRENCY_ID_PLACEHOLDER = "currencyId";
    private static final String SYMBOL = "symbol";

    private final String findCurrencyByIdSql;
    private final String findCurrencyBySymbolSql;
    private final String findAllCurrenciesSql;

    CurrencyRowMapper currencyRowMapper = new CurrencyRowMapper();

    public CurrencyDaoImpl(
            DataSource dataSource,
            @Value("classpath:/db/sql/currencyRepository/findCurrencyById.sql")Resource findCurrencyByIdSqlResource,
            @Value("classpath:/db/sql/currencyRepository/findCurrencyBySymbol.sql") Resource findCurrencyBySymbolSqlResource,
            @Value("classpath:/db/sql/currencyRepository/findAllCurrencies.sql") Resource findAllCurrenciesSqlResource
    ) throws IOException {
        this.findCurrencyByIdSql = copyToString(
                findCurrencyByIdSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.findCurrencyBySymbolSql = copyToString(
                findCurrencyBySymbolSqlResource.getInputStream(),
                Charset.defaultCharset()
        );

        this.findAllCurrenciesSql = copyToString(
                findAllCurrenciesSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.setDataSource(dataSource);
    }

    public Optional<Currency> execCurrencyQuery(SqlQuery sqlQuery) {
        log.trace(sqlQuery.getQuery());
        var currencies = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(sqlQuery.getQuery(), sqlQuery.getParams(), currencyRowMapper);
        if (currencies.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(currencies.getFirst());

    }

    public Optional<Currency> findCurrencyById(Integer id) {
        SqlQuery.Builder builder = SqlQuery.builder().query(findCurrencyByIdSql);
        builder.param(CURRENCY_ID_PLACEHOLDER, id);
        return execCurrencyQuery(builder.build());
    }

    public  Optional<Currency> findCurrencyBySymbol(String symbol) {
        SqlQuery.Builder builder = SqlQuery.builder()
                .query(findCurrencyBySymbolSql)
                .param(SYMBOL, symbol);
        return execCurrencyQuery(builder.build());

    }

    public List<Currency> findAll() {
        SqlQuery.Builder builder = SqlQuery.builder()
                .query(findAllCurrenciesSql);

        SqlQuery query = builder.build();

        return  Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(query.getQuery(), query.getParams(), currencyRowMapper);
    }

}
