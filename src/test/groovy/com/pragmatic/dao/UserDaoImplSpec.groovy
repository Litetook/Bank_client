package com.pragmatic.dao


import com.pragmatic.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.InvalidDataAccessApiUsageException
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

    @Sql(executionPhase = AFTER_TEST_METHOD, value = "users/clean.sql")
    def "Save: should successfully save account "() {
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

    @SqlGroup([
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "users/create-users.sql"),
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "users/clean.sql")
    ])
    def "findByNameAndEmail: should find acc"() {
        when:
            Optional<User> optional = userDao.findByNameAndEmail('John Doe', 'johndoe@example.com');
        then:
            optional!= null
            optional.isPresent();
            User user = optional.get()
    }

    def "findByNameAndEmail: must throw exception"(){
        when:
        userDao.findByNameAndEmail(name, email)
        then: "must throw exception"
        thrown(expectedException)

        where:
        email ||  name ||   expectedException
        null  || "ba"  ||   InvalidDataAccessApiUsageException
        "ba"  || null   || InvalidDataAccessApiUsageException
        null  || null   || InvalidDataAccessApiUsageException
    }


    @Sql(executionPhase = AFTER_TEST_METHOD, value = "users/clean.sql")
    def "findById: must return user"() {
        given:
            User testUser = User.builder()
                    .name("nameById")
                    .email("email")
                    .password("test_pass")
                    .build()
            User returnedUser  = userDao.save(testUser)
        expect:
            userDao.findById(returnedUser.getId()) != null
        userDao.findById(returnedUser.getId()).get() == testUser //groovy does not have difference.
    }

    def "findById: must throw exception" () {
        when:
        userDao.findById(id)
        then: "must throw exception"
        thrown(expectedException)

        where:
        id              ||   expectedException
        -1              ||   InvalidDataAccessApiUsageException
        null            ||   InvalidDataAccessApiUsageException
    }



    @Sql(executionPhase = AFTER_TEST_METHOD, value = "users/clean.sql")
    def "FindAll: should return complete list"() {
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
