package com.subject.designprinciplelab.problem.seq03_ocp_strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class S3UserService {
//    private final UserRepository userRepository;
    private final Map<String, S3NotificationSender> notificationSenders;

    public void registerUser(String message, S3NotificationType notificationType) {
        // ... (회원 저장 로직 생략) ...
        // Optional.of -> null이 들어올 수 없음 Optional.ofNullable -> null이 들어올수도 있음
        Optional<S3NotificationSender> notificationSender = Optional.ofNullable(notificationSenders.get(notificationType.getType()));
        notificationSender
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 알림타입입니다."))
                .send(message);
    }
}