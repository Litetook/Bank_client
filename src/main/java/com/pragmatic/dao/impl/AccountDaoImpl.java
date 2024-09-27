package com.pragmatic.dao.impl;

import com.pragmatic.dao.rowmapper.AccountRowMapper;
import com.pragmatic.dto.impl.AccountDtoImpl;
import com.pragmatic.model.Account;
import com.pragmatic.sql.SqlQuery;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.util.StreamUtils.copyToString;

@Log4j2
@Repository
public class AccountDaoImpl extends NamedParameterJdbcDaoSupport {
    private static final String USER_ID_PLACEHOLDER = "userId";
    private static final String CURRENCY_ID_PLACEHOLDER = "currencyId";
    private static final String BALANCE_PLACEHOLDER = "balance";
    private static final String ACCOUNT_ID_PLACEHOLDER = "accountId";

    private final String createAccountSql;
    private final String findAccountByIdSql;
    private final String findExistingAccountByParamsSql;
    private final String findAccountsByUserIdSql;
    private final String updateBalanceSql;

    AccountRowMapper accountRowMapper = new AccountRowMapper();

    public AccountDaoImpl(
            DataSource dataSource,
            @Value("classpath:/db/sql/accountRepository/createAccount.sql") Resource createAccountSqlResource,
            @Value("classpath:/db/sql/accountRepository/findAccountById.sql") Resource findAccountByIdSqlResource,
            @Value("classpath:/db/sql/accountRepository/findByAttributes.sql") Resource findAccountByAttributesSqlResource,
            @Value("classpath:/db/sql/accountRepository/findAccountsByUserId.sql") Resource findAccountsByUserIdSql,
            @Value("classpath:/db/sql/accountRepository/updateBalance.sql") Resource updateBalanceSql
            ) throws IOException {
        this.createAccountSql = copyToString(
                createAccountSqlResource.getInputStream(),
                Charset.defaultCharset()
        );

        this.findAccountByIdSql = copyToString(
                findAccountByIdSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.findExistingAccountByParamsSql = copyToString(
                findAccountByAttributesSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.findAccountsByUserIdSql = copyToString(
                findAccountsByUserIdSql.getInputStream(),
                Charset.defaultCharset()
        );

        this.updateBalanceSql = copyToString(
                updateBalanceSql.getInputStream(),
                Charset.defaultCharset()
        );


        this.setDataSource(dataSource);
    }


    private Integer insert(SqlQuery sqlQuery) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .update(
                        sqlQuery.getQuery(),
                        sqlQuery.getParams(),
                        keyHolder,
                        new String[]{ACCOUNT_ID_PLACEHOLDER}
                );

        Assert.isTrue(result > 0, "Insert wasn't successful");
        Assert.notNull(keyHolder.getKey(), "Can't get id for the new record");

        return keyHolder.getKey().intValue();
    }

    public Account save (Account account) {
        SqlQuery.Builder builder= SqlQuery.builder().query(createAccountSql);
        builder.param(USER_ID_PLACEHOLDER, account.getUserId());
        builder.param(CURRENCY_ID_PLACEHOLDER, account.getCurrencyId());
        builder.param(BALANCE_PLACEHOLDER, account.getBalance());

        Integer accountId = insert(builder.build());
        account.setId(accountId);
        return account;
    }

    private boolean execUpdateQuery(SqlQuery sqlQuery) {
        return getNamedParameterJdbcTemplate().update(sqlQuery.getQuery(), sqlQuery.getParams()) > 0;
    }

    public boolean updateBalance(Double balance, Integer accountId) {
        SqlQuery.Builder builder = SqlQuery.builder()
                .query(updateBalanceSql)
                .param(BALANCE_PLACEHOLDER, balance)
                .param(ACCOUNT_ID_PLACEHOLDER, accountId);
        return execUpdateQuery(builder.build());
    }

    public Optional<Account> findById(Integer id){
        SqlQuery.Builder builder = SqlQuery.builder().query(findAccountByIdSql);
        builder.param(ACCOUNT_ID_PLACEHOLDER, id);
        return execAccountByIdQuery(builder.build());
    }

    public  Optional<Account> execAccountByIdQuery(SqlQuery sqlQuery) {
        log.trace(sqlQuery.getQuery());

        var accounts = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(sqlQuery.getQuery(), sqlQuery.getParams(), accountRowMapper);
        if (accounts.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(accounts.getFirst());
    }

    public List<Account> execAccountsQuery(SqlQuery sqlQuery) {
        log.trace(sqlQuery.getQuery());
        var accounts = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(sqlQuery.getQuery(), sqlQuery.getParams(), accountRowMapper);
        return accounts;
    }

    public Optional<Account> findExistAccountByParams(AccountDtoImpl accountDtoImpl) {
        SqlQuery.Builder builder = SqlQuery.builder().query(findExistingAccountByParamsSql);
        builder.param(USER_ID_PLACEHOLDER, accountDtoImpl.getUserId());
        builder.param(CURRENCY_ID_PLACEHOLDER, accountDtoImpl.getCurrencyId());
        return execAccountByIdQuery(builder.build());
    }

    public List<Account> findAccountsByUserId(Integer userId) {
        SqlQuery.Builder builder = SqlQuery.builder()
                .query(findAccountsByUserIdSql)
                .param(USER_ID_PLACEHOLDER, userId);
        return execAccountsQuery(builder.build());
    }



}
