package com.subject.designprinciplelab.problem.seq03_ocp_strategy;

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

    @Mock
    S3KakaoSender s3KakaoSender;

    @InjectMocks
    S3UserService s3UserService;

    S3Converter s3Converter = new S3Converter();

    @Test
    @DisplayName("알람타입이 없을때 적절한 에러를 뱉는지에 대한 테스트")
    void registerUser_argumentException(){
        S3NotificationType notitype = S3NotificationType.SLACK;
        String message = "테스트 메시지입니다.";
        BDDMockito.given(notificationSenders.get(notitype.getType())).willReturn(null);

        Assertions.assertThatThrownBy(() -> s3UserService.registerUser(message, notitype))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("지원하지 않는 알림타입입니다.");
    }

    @Test
    @DisplayName("적절한 타입이 들어왔을때 해당 구현체의 send() 함수 실행")
    void registerUser_success() {
        S3NotificationType notitype = S3NotificationType.KAKAO;
        String message = "안녕하세요";
        BDDMockito.given(notificationSenders.get(notitype.getType())).willReturn(s3KakaoSender);

        s3UserService.registerUser(message, notitype);

        BDDMockito.verify(s3KakaoSender, BDDMockito.times(1)).send(message);
    }

    @Test
    @DisplayName("모든 입력값이 대문자로 들어오게 하는 Converter 동작 테스트")
    void stringToS3NotificationTypeConverter() {
        String type = "kaKAo";
        S3NotificationType result = s3Converter.convert(type);

        Assertions.assertThat(result).isEqualTo(S3NotificationType.KAKAO);
    }
}