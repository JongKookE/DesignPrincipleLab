package com.subject.codesmellsolver.seq04;

import com.subject.codesmellsolver.problem.seq04_aop.TrackTimeAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class S4AopTest {

    @InjectMocks
    TrackTimeAspect trackTimeAspect;

    @Mock
    ProceedingJoinPoint proceedingJoinPoint;

    @Mock
    MethodSignature signature;

    @Test
    @DisplayName("Aspect 내부 로직 단위 테스트")
    public void trackTimeAnnotationTest() throws Throwable {
        BDDMockito.given(proceedingJoinPoint.getSignature()).willReturn(signature);
        // When
        trackTimeAspect.around(proceedingJoinPoint);
        BDDMockito.verify(proceedingJoinPoint, BDDMockito.times(1)).proceed();
    }
}