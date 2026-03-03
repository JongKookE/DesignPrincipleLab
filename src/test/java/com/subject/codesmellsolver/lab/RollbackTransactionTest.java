package com.subject.codesmellsolver.lab;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

@SpringBootTest
class RollbackTransactionTest {

    @Autowired
    private RollbackTransaction rollbackTransaction;

    @Autowired
    private LabUserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("checked exception 기본적으로 rollback이 되지 않기에 예외가 발생해도 트랜잭션이 작동하지않아 db에 값이 저장된다.")
    void checkedException_doesNotRollbackByDefault() {
        Exception exception = assertThrowsExactly(Exception.class, () -> rollbackTransaction.doNotRollback());

        assertThat(exception).hasMessage("checked-exception");
        assertThat(userRepository.count()).isEqualTo(1);
    }

    @Test
    @DisplayName("unchecked exception은 기본적으로 rollback을 지원하기에 예외가 발생하면 트랜잭션이 작동되어 db에 값이 저장되지않는다.")
    void uncheckedException_doRollbackByDefault() {
        Exception exception = assertThrowsExactly(RuntimeException.class, () -> rollbackTransaction.doRollback());

        assertThat(exception).hasMessage("unchecked-exception");
        assertThat(userRepository.count()).isEqualTo(0);
    }

    @Test
    @DisplayName("@Transactional을 붙이지 않는다면 트랜잭션은 작동되지않아 DB에 값이 저장된다.")
    void doesNotTransactional() {
        Exception exception = assertThrowsExactly(RuntimeException.class, () -> rollbackTransaction.doNotTransaction());
        assertThat(exception).hasMessage("no-transaction-exception");
        assertThat(userRepository.count()).isEqualTo(1);
    }
}