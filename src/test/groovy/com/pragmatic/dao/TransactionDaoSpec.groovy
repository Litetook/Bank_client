package com.pragmatic.dao

import com.pragmatic.model.Transaction
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.InvalidDataAccessApiUsageException
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import spock.lang.Shared
import spock.lang.Specification

import java.text.SimpleDateFormat

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD

@SpringBootTest
class TransactionDaoSpec extends Specification {

    @Autowired
    TransactionDao transactionDao;

    @Shared
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Shared
    Date startDate = dateFormat.parse("2024-10-15 14:30:00");
    @Shared
    Date endDate = dateFormat.parse("2024-10-15 16:15:00");

    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "transactions/clean.sql"),
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "transactions/create-transactions.sql")
    ])
    def "Save:: must save and return id as proof"() {
        given:
            var transaction = Transaction.builder()
                .amount(0.0)
                .destinationAccountId(1)
                .sourceAccountId(1)
                .build()
        when:
            transactionDao.save(transaction)

        then :
            transaction.getTransactionId() != null
}

    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "transactions/clean.sql"),
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "transactions/create-transactions.sql")
    ])
    def "FindTransactionById: success flow"() {
        given:
         var existingTransaction = transactionDao.findTransactionById(1);
         var notExistingTransaction = transactionDao.findTransactionById(1001);

        expect:
            existingTransaction.isPresent();
            existingTransaction.get().getTransactionId() == 1;
            notExistingTransaction.isEmpty()
    }

    def "FindTransactionById: exception flow"() {
        when:
        transactionDao.findTransactionById(id)

        then:
        thrown(InvalidDataAccessApiUsageException)

        where:
        id  || _
        -1  || _
        null|| _

    }

    @SqlGroup([
            @Sql(executionPhase = AFTER_TEST_METHOD, value = "transactions/clean.sql"),
            @Sql(executionPhase = BEFORE_TEST_METHOD, value = "transactions/create-transactions.sql")
    ])
    def "FindTransactionsByDateRange: success flow"() {
        given:
        // Формат дати для створення об'єктів Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Створення об'єктів Date
        Date dateFrom = dateFormat.parse("2024-10-15 14:30:00");
        Date dateTo = dateFormat.parse("2024-10-15 16:15:00");

        Date notValidDateFrom = dateFormat.parse("2024-10-16 14:30:00");
        Date notValidDateTo = dateFormat.parse("2024-10-16 16:15:00");

        //give transaction which exists in create sql
        List<Transaction> transactionList = transactionDao.findTransactionsByDateRange(1, dateFrom, dateTo );

        List<Transaction> emptyTransactionList = transactionDao.findTransactionsByDateRange(1, notValidDateFrom, notValidDateTo );

        expect:
        transactionList.size() == 2
        emptyTransactionList.size() == 0


    }

    def "FindTransactionsByDateRange: exception flow"() {

        when:
        transactionDao.findTransactionsByDateRange(accountId, dateTo, dateFrom)

        then:
        thrown(exception)
        where:
        accountId  |  dateTo     |  dateFrom   |  exception
        -1         | startDate   | endDate     | InvalidDataAccessApiUsageException  // Некоректний accountId
        null       | startDate   | endDate     | InvalidDataAccessApiUsageException  // accountId = null
        1          | null        | endDate     | InvalidDataAccessApiUsageException            // dateTo = null
        1          | startDate   | null        | InvalidDataAccessApiUsageException            // dateFrom = null
        1          | null        | null        | InvalidDataAccessApiUsageException            // dateTo і dateFrom = null
        -1         | null        | endDate     | InvalidDataAccessApiUsageException  // Некоректний accountId і dateTo = null
        -1         | startDate   | null        | InvalidDataAccessApiUsageException  // Некоректний accountId і dateFrom = null
        null       | null        | endDate     | InvalidDataAccessApiUsageException  // accountId = null і dateTo = null
        null       | startDate   | null        | InvalidDataAccessApiUsageException  // accountId = null і dateFrom = null
        null       | null        | null        | InvalidDataAccessApiUsageException  // accountId, dateTo і dateFrom = null
    }


}
