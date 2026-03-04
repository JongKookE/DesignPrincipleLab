# 📝 Refactoring Report: User Registration System

| Project      | seq02_srp_user                                                    |
|:-------------|:------------------------------------------------------------------|
| **Topic**    | SRP (Single Responsibility Principle) & DI (Dependency Injection) |
| **Status**   | ✅ **Passed** (Final Revision)                                     |
| **Reviewer** | Staff Engineer (CodeSmellProvider)                                |

## 🚀 1. Overview

기존의 **God Class**였던 `UserService`를 리팩터링하여 **책임(Responsibility)**을 분리하고, Spring Framework의 **IoC 컨테이너**를 활용해 결합도를 낮추는 훈련을
수행함.

---

## 🛑 2. Critical Issues & Fixes (오답 노트)

이번 훈련에서 발생했던 주요 실수와 해결 방법입니다.

### ❌ Issue 1: 보안 사고 (Critical)

암호화 로직을 수행해놓고, 정작 DB에는 **평문(Plain Text) 비밀번호**를 저장하는 치명적인 실수가 발생함.

```java
String encrypted = encrypter.encrypt(password);
userRepository.save(email, password, name); // <-- 실수: 평문 저장
```

Fix: 변수에 담긴 암호화된 값(encrypted)을 정확히 repository에 전달하여 해결. 엔지니어는 데이터의 흐름(Data Flow)을 끝까지 추적해야 함.

### ❌ Issue 2: 불필요한 중개자 (Middle Man Code Smell)

PasswordSender 클래스를 만들었으나, 단순히 Encrypter를 호출하는 역할만 수행함.

```java
// [BEFORE]
class PasswordSender {
    String send() {
        return encrypter.encrypt();
    } // <-- 단순 위임
}
```

### ❌ Issue 3: 비즈니스 로직 누수 (Encapsulation)

"ENCRYPTED_" 같은 접두사를 붙이는 구체적인 로직이 `UserService`에 노출됨.

**Fix**: 암호화 구현체(RSAEncrypter) 내부로 로직을 이동시킴. UserService는 "암호화해라"라는 메시지만 던지도록 추상화 레벨을 맞춤.

### ❌ Issue 4: 의존성 주입 충돌 (Ambiguous Bean)

**Encrypter** 인터페이스의 구현체가 2개(RSA, SHA)여서 Spring Boot 실행 시 NoUniqueBeanDefinitionException 발생.

**Fix**: @Configuration 클래스(EncryptConfig)를 도입하여 외부 설정 파일에서 **어떤 구현체를 사용할지 명시적으로 결정(Binding)**함.

## 🏗️ 3. Architecture Evolution

### AS-IS (Legacy)

- UserService가 모든 일(검증, 암호화, 저장, 메일)을 다 처리함.
- if-else 로직과 new 키워드가 뒤섞인 강한 결합(Tightly Coupled).

### TO-BE (Refactored)

- Layered Architecture 적용
- IoC (Inversion of Control) 적용

```Code snippet
graph TD
Config[EncryptConfig] -->|Creates| RSA[RSAEncrypter]
Spring[Spring Container] -->|Injects RSA| Service[UserService]

    subgraph "UserService Responsibilities"
        Service -->|1. Validate| Validator[UserValidator]
        Service -->|2. Encrypt| Encrypter(Interface)
        Service -->|3. Save| Repository[UserRepository]
        Service -->|4. Notify| Sender[SmtpSender]
    end
    
    RSA -.->|Implements| Encrypter
```

## 💡 4. Tech Stack & Concepts

이 미션을 통해 학습한 핵심 기술입니다.

- SRP (단일 책임 원칙): 클래스는 변경해야 할 이유가 단 하나여야 한다.
- DI (의존성 주입): 객체는 스스로 의존성을 만들지(new) 않고, 외부에서 주입받아야 한다
- Strategy Pattern: 암호화 알고리즘을 Encrypter 인터페이스 뒤로 숨겨 교체 용이하게 만듦.
- Spring Configuration: @Component 스캔 방식보다 더 유연한 @Configuration + @Bean 조합 사용법.

## 👨‍💻 5. Reviewer's Note

- 처음에는 new를 남발하고 보안 구멍도 있었지만, 피드백을 수용하여 Spring 생태계에 걸맞은 구조로 잘 발전시켰습니다.
- 특히 마지막에 Level 3 (Java Config) 방식을 선택한 것은, 라이브러리 개발자나 아키텍트로 성장하기 위한 아주 좋은 선택이었습니다
- 이제 당신의 UserService는 POJO(Plain Old Java Object) 상태를 유지하면서도, 외부 설정에 따라 언제든지 부품을 갈아 끼울 수 있는 유연함을 가졌습니다."




# 추가적으로 Refactoring & Unit Testing Training

## 🎯 프로젝트 목표
레거시 코드를 리팩터링하여 **객체 지향 원칙(SOLID)**을 준수하고, **테스트 가능한(Testable)** 구조로 개선한다.
특히 Spring Context 없이 순수 Java와 Mockito만을 활용한 **고속 단위 테스트** 환경을 구축한다.

## 🛠️ 기술 스택 (Tech Stack)
- **Language:** Java 17
- **Framework:** Spring Boot (DI 컨테이너 활용)
- **Testing:** JUnit5, Mockito, AssertJ

## 🚀 주요 변경 내용

### 1. SRP & DI 적용 (Refactoring)
- **Before:** `UserService`가 직접 `new Repository()`, `new SmtpSender()`를 호출하며 강하게 결합됨.
- **After:** 생성자 주입(Constructor Injection)을 통해 외부에서 의존성을 주입받도록 변경.
    - 효과: 결합도 감소, Mock 객체 주입 가능.

### 2. Mockito 기반 단위 테스트
- 실제 DB나 메일 서버 없이 `UserService`의 로직만 0.1초 내에 검증.
- **Mocking Strategy:**
    - `@Mock`: `UserRepository`, `SmtpSender`, `Encrypter` 가짜 객체 생성.
    - `@InjectMocks`: 테스트 대상인 `UserService`에 가짜 객체 주입.
    - **Stubbing Issue Resolved:** Interface의 `default` 메서드는 Mock 객체에서 실행되지 않음을 인지하고, 최종 호출 메서드(`encrypter.send`)를 Stubbing 함.

### 3. 예외 처리 및 검증 (Exception Handling)
- 중복 이메일 가입 시도 시 `DuplicateUserException` 발생 로직 구현.
- **AssertJ 활용:**
  ```java
  assertThatThrownBy(() -> userService.registerUser(...))
      .isInstanceOf(DuplicateUserException.class)
      .hasMessage("중복된 이메일입니다.");