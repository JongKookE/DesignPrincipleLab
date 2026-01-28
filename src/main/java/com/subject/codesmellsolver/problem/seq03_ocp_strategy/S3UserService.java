package com.subject.codesmellsolver.problem.seq03_ocp_strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class S3UserService {
//    private final UserRepository userRepository;
    private final Map<String, S3NotificationSender> notificationSenderHashMap;

    public void registerUser(String message, String notiType) {
        // ... (회원 저장 로직 생략) ...

        notificationSenderHashMap.get(notiType).send(message);
    }
}