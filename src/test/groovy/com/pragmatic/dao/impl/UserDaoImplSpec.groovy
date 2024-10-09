package com.pragmatic.dao.impl

import com.pragmatic.dao.UserDao
import com.pragmatic.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import spock.lang.Specification
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD


@SpringBootTest
class UserDaoImplSpec extends Specification {

    @Autowired JdbcTemplate jdbcTemplate;
    @Autowired UserDao userDao;

    def "get controller"() {
        expect:"controller is created"
        jdbcTemplate != null
    }

    @SqlGroup([
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "users/create-users.sql"),
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "users/clean.sql")
    ])
    def "should find user by its name and email"() {
        when:
        Optional<User> optional = userDao.findByNameAndEmail('John Doe', 'johndoe@example.com');

        then:

        optional!= null
        optional.isPresent();
        User user = optional.get()

    }

}
