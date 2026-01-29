package com.subject.codesmellsolver.seq04;

import com.subject.codesmellsolver.problem.seq04_aop.S4TestService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;

@SpringBootTest
@ExtendWith(OutputCaptureExtension.class)
public class S4AopContextTest {
    @Autowired
    S4TestService s4TestService;

    @Test
    @DisplayName("AOP 적용 확인: 로그가 찍히는지 눈으로 확인하거나 OutputCapture를 사용")
    void trackTimeAspectIntegrationTest(CapturedOutput output) {
//        s4TestService.checkAop();
        Assertions.assertThat(s4TestService.checkAop()).contains("VVVVV");

        Assertions.assertThat(output.getOut()).contains("executed in").contains("checkAop");
    }
}