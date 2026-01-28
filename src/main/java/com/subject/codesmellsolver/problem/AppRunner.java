package com.subject.codesmellsolver.problem;

import com.subject.codesmellsolver.problem.seq03_ocp_strategy.S3NotificationType;
import com.subject.codesmellsolver.problem.seq03_ocp_strategy.S3UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final S3UserService s3UserService;

    @Override
    public void run(String... args) {
        s3UserService.registerUser("이건 메시지입니다.", S3NotificationType.KAKAO);
    }
}