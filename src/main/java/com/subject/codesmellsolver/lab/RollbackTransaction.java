package com.subject.codesmellsolver.lab;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RollbackTransaction {
    private final LabUserRepository repository;

    @Transactional
    public void doNotRollback() throws Exception {
        LabUser labUser = new LabUser(null, "park");
        repository.save(labUser);
        throw new Exception("checked-exception");
    }

    @Transactional
    public void doRollback(){
        LabUser labUser = new LabUser(null, "park");
        repository.save(labUser);
        throw new RuntimeException("unchecked-exception");
    }

    public void doNotTransaction() {
        LabUser labUser = new LabUser(null, "park");
        repository.save(labUser);
        throw new RuntimeException("no-transaction-exception");
    }
}