package com.subject.designprinciplelab.lab.transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest(classes = TransactionLabTestApplication.class)
class TransactionRollbackServiceTest {

    @Autowired
    private TransactionRollbackService transactionRollbackService;

    @Autowired
    private TransactionLabUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("checked exception does not roll back by default, so the data is saved")
    void checkedException_doesNotRollbackByDefault() {
        Exception exception = assertThrowsExactly(
                Exception.class,
                () -> transactionRollbackService.saveUserAndThrowCheckedException()
        );

        assertThat(exception).hasMessage("checked-exception");
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("unchecked exception rolls back by default, so the data is not saved")
    void uncheckedException_rollsBackByDefault() {
        RuntimeException exception = assertThrowsExactly(
                RuntimeException.class,
                () -> transactionRollbackService.saveUserAndThrowUncheckedException()
        );

        assertThat(exception).hasMessage("unchecked-exception");
        assertThat(userRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("without Transactional, the data is saved even when an exception is thrown")
    void noTransaction_persistsDataEvenWhenExceptionIsThrown() {
        RuntimeException exception = assertThrowsExactly(
                RuntimeException.class,
                () -> transactionRollbackService.saveUserWithoutTransactionAndThrowUncheckedException()
        );

        assertThat(exception).hasMessage("no-transaction-exception");
        assertThat(userRepository.count()).isEqualTo(1);
    }
}