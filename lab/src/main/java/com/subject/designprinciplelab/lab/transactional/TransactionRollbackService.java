package com.subject.designprinciplelab.lab.transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionRollbackService {
    private final TransactionLabUserRepository repository;

    @Transactional
    public void saveUserAndThrowCheckedException() throws Exception {
        TransactionLabUser labUser = new TransactionLabUser(null, "park");
        repository.save(labUser);
        throw new Exception("checked-exception");
    }

    @Transactional
    public void saveUserAndThrowUncheckedException() {
        TransactionLabUser labUser = new TransactionLabUser(null, "park");
        repository.save(labUser);
        throw new RuntimeException("unchecked-exception");
    }

    public void saveUserWithoutTransactionAndThrowUncheckedException() {
        TransactionLabUser labUser = new TransactionLabUser(null, "park");
        repository.save(labUser);
        throw new RuntimeException("no-transaction-exception");
    }
}