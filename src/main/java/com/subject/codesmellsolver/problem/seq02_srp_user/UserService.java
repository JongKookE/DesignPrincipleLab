package com.subject.codesmellsolver.problem.seq02_srp_user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserValidator userValidator;
    private final UserRepository userRepository;
    private final Encrypter encrypter;
    private final SmtpSender smtpSender;

    public void registerUser(String email, String password, String name) {
        userValidator.validate(email, password);
        System.out.println("계정 등록 검증 성공");

        if(userRepository.existsByEmail(email)) throw new DuplicateUserException("중복된 이메일입니다.");

        String encryptedPassword = encrypter.send(password);
        System.out.println("비밀번호 암호화 완료: " + encryptedPassword);

        userRepository.save(email, encryptedPassword, name);
        smtpSender.sendToSMTPServer(email);

        System.out.println(this.getClass());
    }
}