package com.pragmatic.dao


import com.pragmatic.model.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import spock.lang.Specification
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import org.springframework.dao.InvalidDataAccessApiUsageException

@SpringBootTest
class AccountDaoImplSpec extends Specification {
    @Autowired
    JdbcTemplate jdbcTemplate

    @Autowired
    AccountDao accountDao

    @Sql(executionPhase = AFTER_TEST_METHOD, value = "accounts/clean.sql")
    def "Save: must successfully save account"() {
        when: "must save accounts and return its ids as identifier of their database change"
            Account account4 = Account.builder()
                    .balance(0.0)
                    .currencyId(1)
                    .userId(1)
                    .build()

            Account account5 = Account.builder()
                    .balance(0.0)
                    .currencyId(2)
                    .userId(1)
                    .build()
            Account savedAccount4 =  accountDao.save(account4)
            Account savedAccount5 = accountDao.save(account5)

        then: "verify if accounts returns id, vetify if db return gives same acc"
            savedAccount4 != null
            savedAccount5 != null
            savedAccount4.getId() != null
            savedAccount5.getId() != null //підтвердження до виданого йому ід.

            var daoAccount4 = accountDao.findById(savedAccount4.getId())
            var daoAccount5 = accountDao.findById(savedAccount5.getId())

            daoAccount4.get().equals(account4)
            daoAccount5.get().equals(account5)
    }



    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "accounts/clean.sql"),
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "accounts/create-accounts.sql")
    ])
    def "UpdateBalance: must update balance"() {
        when: "update balance for existing acc"
            def result = accountDao.updateBalance(1.0, 1)
        then: "the result should match the expected output"
            result //boolean result, so its verification

        when: "account does not exist"
            def result1 = accountDao.updateBalance(1.0, 1001)
        then:
            !result1
    }


    def "updateBalance: must throw exception"() {
        when: "we update balance with invalid inputs"
        accountDao.updateBalance(inputBalance, inputAccountId)
        then: "exception must be thrown"
        thrown(InvalidDataAccessApiUsageException)
        where:
        inputBalance || inputAccountId
        -1.0         || null
        null         || 1
        1.0          || -1

    }

    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "accounts/clean.sql"),
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "accounts/create-accounts.sql")
    ])
    def "findById: must pass with finding or not"() {
        var account1 = accountDao.findById(1)
        var account1001 = accountDao.findById(1001)

        expect:
            !account1.isEmpty()
            account1.get().getId() == 1

            account1001.isEmpty()

    }


    def "FindById: should return exception"() {
        when:
            accountDao.findById(inputAccountId)
        then: "must throw exception"
            thrown(exceptionName)
        where:
        inputAccountId  ||  exceptionName
        //такий тип ексепшенів за причини, що спрінг при анотакції репозиторія автоматично переписує ексепшени під себе.
        // у моєму випадку іллігал міняєтсья на наступний
        -1              ||  InvalidDataAccessApiUsageException
        null              ||  InvalidDataAccessApiUsageException
    }




    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "accounts/clean.sql"),
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "accounts/create-accounts.sql")
    ])
    def "FindAccountsByUserId: success flow"() {
        when:
            List<Account> existingList = accountDao.findAccountsByUserId(1);
        then: "expect list with accounts"
            existingList.size() == 2

        when:
        List<Account> notExistingList = accountDao.findAccountsByUserId(1001);
        then: "expect no accs in list"
        notExistingList.size() == 0
    }

    def "FindAccountsByUserId: exception flow"() {
        when:
        accountDao.findAccountsByUserId(inputValue)
        then: "must throw exception"
        thrown(exceptionName)
        where:
        inputValue  ||  exceptionName
        -1          ||  InvalidDataAccessApiUsageException
        null        ||  InvalidDataAccessApiUsageException
    }

    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "accounts/clean.sql"),
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "accounts/create-accounts.sql")
    ])
    def "findAccountByUserIdAndCurrencyId: success flow"() {
        when:
        var account = accountDao.findAccountByUserIdAndCurrencyId(1,1);

        then:" must return acc"
            !account.isEmpty()
            account.get().getUserId() == 1
            account.get().getCurrencyId() == 1

        when:
        var account1 = accountDao.findAccountByUserIdAndCurrencyId(1001,1001);

        then: "must pass but find nothing"
        account1.isEmpty()
        }

    def "findAccountByUserIdAndCurrencyId: exception flow"() {
        when:
        accountDao.findAccountByUserIdAndCurrencyId(inputUserId, inputCurrencyId)
        then: "must throw exception"
        thrown(expectedException)

        where:
        inputUserId || inputCurrencyId || expectedException
        1           || -1              || InvalidDataAccessApiUsageException
        -1          || 1               || InvalidDataAccessApiUsageException
        1           || null            || InvalidDataAccessApiUsageException
        null        || 1               || InvalidDataAccessApiUsageException
        null        || null            || InvalidDataAccessApiUsageException
        -1          || -1              || InvalidDataAccessApiUsageException
    }


}
