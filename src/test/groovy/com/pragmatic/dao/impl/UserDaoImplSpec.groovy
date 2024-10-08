package com.pragmatic.dao.impl

import com.pragmatic.Main
import com.pragmatic.controller.GeneralController
import com.pragmatic.dao.UserDao
import com.pragmatic.model.User
import jakarta.activation.DataSource
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringRunner

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import spock.lang.Specification

@SpringBootTest
class UserDaoImplSpec extends Specification {

    @Autowired
    private GeneralController generalController;

    def "get controller"() {
        expect:"controller is created"
        generalController
    }
//    @SqlGroup([
//            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "users/create-users.sql"),
//            @Sql(executionPhase = AFTER_TEST_METHOD, value = "users/clean.sql")
//    ])
//    def "should find user by its name and email"() {
//        when:
//        Optional<User> optional = userDao.findByNameAndEmail('John Doe', 'johndoe@example.com');
//
//        then:
//
//        optional!= null
//        optional.isPresent();
//        User user = optional.get()
//
//    }

}
