package com.subject.codesmellsolver.problem;

import com.subject.codesmellsolver.problem.seq02_srp_user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    // seq02
    private final UserService userService;

    @Override
    public void run(String... args) {
        userService.registerUser("email@google.com", "password", "lorem ipsum");
    }
}
