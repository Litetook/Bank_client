package com.pragmatic.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import spock.lang.Specification
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD

@SpringBootTest
class CurrencyDaoImplSpec extends Specification {

    @Autowired
    CurrencyDao currencyDao;


    @SqlGroup([
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "currencies/create-currencies.sql"),
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "currencies/clean.sql"),
    ])
    def "FindCurrencyById: successful flow"() {
        when:
        var currency1 = currencyDao.findCurrencyById(1);
        then:
        currency1.isPresent()

        when:
        var currency2 = currencyDao.findCurrencyById(1001)
        then:
        currency2.isEmpty()

    }

    def "FindCurrencyById: exception flow"() {
        when:
        var currency1 = currencyDao.findCurrencyById(id);
        then:
        thrown(InvalidDataAccessApiUsageException)
        where:
        id   || exceptionName
        -1   || InvalidDataAccessApiUsageException
        null || InvalidDataAccessApiUsageException
    }


    @SqlGroup([
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "currencies/create-currencies.sql"),
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "currencies/clean.sql"),
    ])
    def "FindCurrencyBySymbol: successful flow"() {
        when:
        var existingCurrency = currencyDao.findCurrencyBySymbol("USD")
        var notExistingCurrency = currencyDao.findCurrencyBySymbol("BLA");

        then:
        existingCurrency.isPresent()
        existingCurrency.get().getSymbol()
        notExistingCurrency.isEmpty()
    }

    def "FindCurrencyBySymbol: exception flow"() {
        when:
        currencyDao.findCurrencyBySymbol(symbol)
        then:
        thrown(exception)

        where:
        symbol || exception
        ""     || InvalidDataAccessApiUsageException
        null   || InvalidDataAccessApiUsageException

    }
}