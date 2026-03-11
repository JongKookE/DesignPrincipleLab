package com.subject.designprinciplelab.lab.transactional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionLabUserRepository extends JpaRepository<TransactionLabUser, Long> {
}