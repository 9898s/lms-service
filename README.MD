# LMS 서비스 홈페이지

## 📄 개요
- 이 서비스는 학습 관리 시스템(Learning Management System)의 기능을 제공하는 웹 애플리케이션입니다. 
- 이 서비스는 사용자들이 강좌를 등록하고 수강할 수 있으며, 관리자는 회원과 강좌, 카테고리, 배너 등을 관리할 수 있습니다.
- 기본적인 회원가입, 로그인, 로그아웃 기능을 비롯하여 비밀번호 찾기, 로그인 히스토리 기능 등이 포함되어 사용자와 관리자의 편의성을 고려한 서비스를 제공합니다.

## 📌 기능 사항
1. 회원가입 및 가입 인증메일 전송
    - 사용자는 회원가입을 할 수 있습니다.
    - 가입 시 입력한 이메일 주소로 인증 메일을 전송하여 회원 가입을 완료할 수 있도록 합니다.
2. 로그인 및 로그아웃
    - 회원은 아이디와 비밀번호를 입력하여 로그인할 수 있습니다.
    - 로그아웃 기능을 통해 로그인한 회원이 로그아웃할 수 있습니다.
3. 비밀번호 찾기(비밀번호 초기화 기능)
    - 비밀번호를 잊어버린 회원은 등록한 이메일 주소를 통해 비밀번호 초기화 링크를 받을 수 있습니다.
    - 링크를 통해 새로운 비밀번호를 설정할 수 있습니다.
4. 관리자(백오피스) 회원 관리
    - 관리자는 회원 목록을 조회하고 회원 정보를 관리할 수 있습니다.
    - 회원의 상태를 변경하거나 삭제할 수 있습니다.
5. 관리자(백오피스) 카테고리 관리
    - 관리자는 카테고리 목록을 조회하고 카테고리를 추가, 수정, 삭제할 수 있습니다.
6. 관리자(백오피스) 강좌 관리
    - 관리자는 강좌 목록을 조회하고 강좌를 추가, 수정, 삭제할 수 있습니다.
7. 회원 로그인시 로그인 히스토리(로그) 기능
    - 회원은 로그인할 때마다 로그인 기록이 남도록 합니다.
8. 관리자 회원 상세 정보에 로그인 목록 보기 기능
    - 관리자는 특정 회원의 상세 정보를 조회할 때, 해당 회원의 로그인 기록을 확인할 수 있습니다.
9. 배너관리(백오피스 기능)
    - 관리자는 배너 목록을 조회하고 배너를 추가, 수정, 삭제할 수 있습니다.
10. 프론트 배너 노출 기능
    - 사용자가 웹 페이지를 접속하면 관리자가 등록한 배너가 노출됩니다.

## 📚 개발 환경
- Java (JDK 1.8), Spring Boot 2.7.14, Spring Security, Spring Mail
- JPA, MySQL, Lombok, Thymeleaf, Tomcat 8.5.91, IntelliJ IDEA, GitHub

## 📅 개발 기간
- 2023/07/26 ~ 2023/08/01

## 🗳️ ERD
<img width="603" alt="ERD" src="https://github.com/9898s/seoul-public-wifi/assets/46531692/08c4b456-5725-4eba-ba46-86c60e9374e1">