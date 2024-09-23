package com.pragmatic.repository;

import com.pragmatic.dao.AccountRowMapper;
import com.pragmatic.model.Account;
import com.pragmatic.sql.SqlQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
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
public class AccountRepositoryImpl  extends NamedParameterJdbcDaoSupport {
    private static final String USERID = "userid";
    private static final String CURRENCYID = "currencyid";
    private static final String BALANCE = "balance";
    private static final String ACCOUNTID = "accountid";

    private final String createAccountSql;

    @Autowired
    private UserRepositoryImpl userRepo;

    AccountRowMapper accountRowMapper = new AccountRowMapper();

    public AccountRepositoryImpl(
            DataSource dataSource,
            @Value("classpath:/db/sql/accountRepository/create.sql") Resource createAccountSqlResource
    ) throws IOException {
        this.createAccountSql = copyToString(
                createAccountSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.userRepo = userRepo;
        this.setDataSource(dataSource);
    }

//    public List<Account> findAll() {
//        String sql = "SELECT * from accounts";
//        return jdbcTemplate.query(sql, new AccountRowMapper());
//    }

    private Integer insert(SqlQuery sqlQuery) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int result = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .update(
                        sqlQuery.getQuery(),
                        sqlQuery.getParams(),
                        keyHolder,
                        new String[]{ACCOUNTID}
                );

        Assert.isTrue(result > 0, "Insert wasn't successful");
        Assert.notNull(keyHolder.getKey(), "Can't get id for the new record");

        return keyHolder.getKey().intValue();
    }

    public Account save (Account account) {
        SqlQuery.Builder builder= SqlQuery.builder().query(createAccountSql);
        builder.param(USERID, account.getUserId());
        builder.param(CURRENCYID, account.getCurrencyId());
        builder.param(BALANCE, account.getBalance());
        Integer accountId = insert(builder.build());
        account.setId(accountId);
        return account;


    }

    public Optional<Account> findById(Integer id){
        String sql = "Select * from accounts where accountid = ?";
//        НЕПОНЯТНО
//        return Optional.ofNullable( jdbcTemplate.queryForObject(sql, new Object[]{id}, accountRowMapper));
        return  Optional.ofNullable(null);

    }

}
