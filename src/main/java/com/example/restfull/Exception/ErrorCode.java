package com.example.restfull.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_EXSISTS_EMAIL(HttpStatus.BAD_REQUEST,"이미 존재하는 회원입니다."),

    NOT_VALID_PASSWORD(HttpStatus.BAD_REQUEST,"8글자 이상의 패스워드를 입력하시오."),

    NOT_EQUALS_CODE(HttpStatus.BAD_REQUEST, "잘못된 코드입니다."),

    NOT_VALID_INPUT(HttpStatus.BAD_REQUEST, "올바른 형식으로 입력하시오."),

    NOT_EXSISTS_MEMBER(HttpStatus.BAD_REQUEST, "존재하지 않는 회원입니다."),

    NOT_CORRECT_PASSWORD(HttpStatus.BAD_REQUEST, "잘못된 패스워드입니다."),

    NOT_VALID_REFRESHTOKEN(HttpStatus.BAD_REQUEST, "유효한 토큰이 아닙니다."),

    NOT_CERTIFIED_MEMBER(HttpStatus.BAD_REQUEST, "이메일 인증이 되어있지 않습니다."),

    NOT_EXSISTS_BOARD(HttpStatus.BAD_REQUEST, "존재하지 않는 게시판입니다."),

    NOT_CORRECT_MEMBER(HttpStatus.BAD_REQUEST, "작성자가 아닙니다.");

    private final HttpStatus httpStatus;

    private final String detail;

}
