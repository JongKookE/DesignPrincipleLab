package com.subject.designprinciplelab.problem.seq02_srp_user;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserValidator userValidator;
    @Mock
    UserRepository userRepository;
    @Mock
    Encrypter encrypter;
    @Mock
    SmtpSender smtpSender;

    @InjectMocks
    UserService userService;

    @Test
    @DisplayName("회원가입 시 암호화된 비밀번호 저장 및 메일 발송 검증")
    void registerUser_success() {
        // Given (준비)
        String email = "test@example.com";
        String rawPw = "password123";
        String encryptedPw = "ENCRYPTED_HASH_VALUE";
        String name = "TempName";

        // TODO 3: Encrypter가 "password123"을 받으면 "ENCRYPTED_HASH_VALUE"를 리턴하도록 Stubbing 하세요.
        // "encrypter야, 'password123'이 들어오면 'HASHED_PW'를 뱉어라"
        given(encrypter.send("password123")).willReturn(encryptedPw);

        // When (실행)
        // TODO 4: UserService의 registerUser를 실행하세요.
        userService.registerUser(email, rawPw, name);

        // Then (검증)
        // TODO 5: UserRepository.save()가 호출되었는지, 그때 전달된 User 객체의 비밀번호가 암호화된 상태인지 검증하세요.
        // hint: verify(...) 및 ArgumentCaptor 등을 활용하거나 간단히 호출 여부 확인.
        // "userRepository야, save 메서드가 호출된 적 있니?" (인자는 아무거나 상관없음: any())
        verify(userRepository).save(email, encryptedPw, name);

        // TODO 6: EmailSender.send()가 해당 이메일로 호출되었는지 검증하세요.
        verify(smtpSender).sendToSMTPServer(email);
    }

    @Test
    @DisplayName("중복된 이메일 검증 테스트")
    void registerUser_fail_duplicate(){
        String email = "duplicate@example.com";
        String rawPw = "password123";
        String name = "BadUser";

        given(userRepository.existsByEmail(any())).willReturn(true);

        Assertions.assertThatThrownBy(() -> userService.registerUser(email, rawPw, name)).isInstanceOf(DuplicateUserException.class)
                .hasMessage("중복된 이메일입니다.");

    }
}