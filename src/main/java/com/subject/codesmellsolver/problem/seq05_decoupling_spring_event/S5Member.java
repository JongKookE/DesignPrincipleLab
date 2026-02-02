package com.subject.codesmellsolver.problem.seq05_decoupling_spring_event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class S5Member {
    private Long id;
    private String name;
    private String email;

    public S5Member(String name, String email){
        this.name = name;
        this.email = email;
    }
}