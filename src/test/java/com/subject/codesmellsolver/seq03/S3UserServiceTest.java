package com.subject.codesmellsolver.seq03;

import com.subject.codesmellsolver.problem.seq03_ocp_strategy.S3NotificationSender;
import com.subject.codesmellsolver.problem.seq03_ocp_strategy.S3UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class S3UserServiceTest {
    @Mock
    Map<String, S3NotificationSender> notificationSenders;

    @InjectMocks
    S3UserService s3UserService;

    @Test
    @DisplayName("알람타입이 없을때 적절한 에러를 뱉는지에 대한 테스트")
    void registerUser_argumentException(){
        String notitype = "Slack";
        String message = "테스트 메시지입니다.";
        BDDMockito.given(notificationSenders.get(notitype)).willReturn(null);

        Assertions.assertThatThrownBy(() -> s3UserService.registerUser(message, notitype))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 알림타입입니다.");
    }
}