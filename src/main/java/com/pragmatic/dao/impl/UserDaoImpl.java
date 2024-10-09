package com.pragmatic.dao.impl;


import com.pragmatic.dao.UserDao;
import com.pragmatic.dao.rowmapper.UserRowMapper;
import com.pragmatic.dto.impl.UserDtoImpl;
import com.pragmatic.model.User;
import com.pragmatic.sql.SqlQuery;
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
import static com.pragmatic.sql.SqlQuery.Builder;

@Log4j2
@Repository
public class UserDaoImpl extends NamedParameterJdbcDaoSupport implements UserDao {
    private static final String USER_ID_PLACEHOLDER = "userId";
    private static final String NAME_PLACEHOLDER = "name";
    private static final String EMAIL_PLACEHOLDER = "email";
    private static final String PASSWORD_PLACEHOLDER  = "password";

    private final String findUserByIdSql;
    private final String findUserByAttributesSql;
    private final String findAllUsersSql;
    private final String createUserSql;

    UserRowMapper userRowMapper = new UserRowMapper();

    public UserDaoImpl(
            DataSource dataSource,
            @Value("classpath:/db/sql/userDao/findUserById.sql") Resource findByIdSqlResource,
            @Value("classpath:/db/sql/userDao/findByAttributes.sql") Resource findByAttributesSqlResource,
            @Value("classpath:/db/sql/userDao/findAllUsers.sql") Resource findAllSqlResource,
            @Value("classpath:/db/sql/userDao/createUser.sql") Resource createUserSqlResource
            ) throws IOException {
        this.findUserByIdSql = copyToString(
                findByIdSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.findUserByAttributesSql = copyToString(
                findByAttributesSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.findAllUsersSql = copyToString(
                findAllSqlResource.getInputStream(),
                Charset.defaultCharset()
        );
        this.createUserSql = copyToString(
                createUserSqlResource.getInputStream(),
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
                        new String[]{USER_ID_PLACEHOLDER}
                );

        Assert.isTrue(result > 0, "Insert wasn't successful");
        Assert.notNull(keyHolder.getKey(), "Can't get id for the new record");

        return keyHolder.getKey().intValue();
    }

    public User save(User user) {
        SqlQuery.Builder builder= SqlQuery.builder().query(createUserSql)
                .param(NAME_PLACEHOLDER, user.getName())
                .param(EMAIL_PLACEHOLDER, user.getEmail())
                .param(PASSWORD_PLACEHOLDER, user.getPassword());

        Integer userId = insert(builder.build());
        user.setId(userId);
        return user;
    }



    public Optional<User> execUserQuery(SqlQuery sqlQuery) {
        log.trace(sqlQuery.getQuery());
        var users = Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(sqlQuery.getQuery(), sqlQuery.getParams(), userRowMapper);
        if (users.isEmpty()) {
            return  Optional.empty();
        }
        return Optional.of(users.getFirst());
    }

    @Override
    public Optional<User> findById(Integer id) {
        Builder builder = SqlQuery.builder()
                .query(findUserByIdSql)
                .param(USER_ID_PLACEHOLDER, id);
        return  execUserQuery(builder.build());
    }

    @Override
    public Optional<User> findByAttributes(UserDtoImpl userDto) {
        SqlQuery sqlQuery = SqlQuery.builder()
                .query(findUserByAttributesSql)
                .param(NAME_PLACEHOLDER, userDto.getName())
                .param(EMAIL_PLACEHOLDER, userDto.getEmail())
                .build();
        return  execUserQuery(sqlQuery);
    }

    @Override
    public Optional<User> findByNameAndEmail(String name,  String email) {
        SqlQuery sqlQuery = SqlQuery.builder()
                .query(findUserByAttributesSql)
                .param(NAME_PLACEHOLDER, name)
                .param(EMAIL_PLACEHOLDER, email)
                .build();
        return  execUserQuery(sqlQuery);
    }

    @Override
    public List<User> findAll() {
        SqlQuery sqlQuery = SqlQuery.builder()
                .query(findAllUsersSql)
                .build();
        return  Objects.requireNonNull(getNamedParameterJdbcTemplate())
                .query(sqlQuery.getQuery(), sqlQuery.getParams(), userRowMapper);
    }
}