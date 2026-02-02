package com.subject.codesmellsolver.problem.seq05_decoupling_spring_event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface S5MemberRepository extends JpaRepository<S5Member, Long> {
}