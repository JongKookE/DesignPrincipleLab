package com.subject.designprinciplelab.lab.java;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CallByValueSemanticsTest {

    @Test
    @DisplayName("Long param creates new value, caller variable stays unchanged")
    public void longParam_createsNewValue_callerVariableUnchanged() {
        Long number = 100L;
        Long convertedNumber = isReferenceMethod(number);

        Assertions.assertThat(convertedNumber).isEqualTo(200L);
        Assertions.assertThat(number).isEqualTo(100L);
    }

    public Long isReferenceMethod(Long number) {
        number += 100L;
        return number;
    }

    @Test
    @DisplayName("Different variables can point to same User instance")
    public void differentVariables_sameUserReference_mutationIsVisible() {
        User user = new User("kim");
        mutateUserName(user);
        Assertions.assertThat(user.getName()).isEqualTo("lee");
    }

    @Test
    @DisplayName("Reassigning callee variable does not affect caller variable")
    public void calleeVariableReassignment_doesNotAffectCallerVariable() {
        User user = new User("kim");
        reassignUser(user);
        Assertions.assertThat(user.getName()).isEqualTo("kim");
    }

    private void mutateUserName(User user) {
        user.setName("lee");
    }

    private void reassignUser(User user) {
        user = new User("park");
        user.setName("choi");
    }

    static class User {
        private String name;

        User(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }
    }
}
