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

    def "get jdbctemplate"() {
        expect:"jdbc template is created"
        jdbcTemplate != null
    }


    def "Should save and return same user with id, should find same user from repo"() {
        given:
            User testUser = User.builder()
            .name("name")
            .email("email")
            .password("testpass")
            .build()
           User returnedUser  = userDao.save(testUser)
        expect:
            returnedUser != null
            testUser.id != null

    }
    //чи треба його робити, якщо він вже включає в себе підметод сейв, який базується на ньому.
    def "ExecUserQuery"() {
    }

    def "FindById"() {
        given:
            User testUser = User.builder()
                    .name("nameById")
                    .email("email")
                    .password("test_pass")
                    .build()
            User returnedUser  = userDao.save(testUser)
        expect:
            userDao.findById(returnedUser.id) != null
        userDao.findById(returnedUser.id).get().equals( returnedUser)

    }

    @SqlGroup([
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "users/create-users.sql"),
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "users/clean.sql")
    ])
    def FindByNameAndEmail() {
        when:
            Optional<User> optional = userDao.findByNameAndEmail('John Doe', 'johndoe@example.com');

        then:
            optional!= null
            optional.isPresent();
            User user = optional.get()
    }


    def "FindAll"() {
        given:
            User user1 = User.builder().email("1").password("1").name("1").build()
            User user2 = User.builder().email("2").password("2").name("2").build()

            userDao.save(user1)
            userDao.save(user2)

            List<User> originList = Arrays.asList(user1,user2)

            List<User> returnedList = userDao.findAll()

        expect:
            returnedList.equals(originList)
    }

}
