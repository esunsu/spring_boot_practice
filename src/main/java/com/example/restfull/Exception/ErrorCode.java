package com.example.restfull.Exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    ALREADY_EXSISTS_EMAIL(HttpStatus.BAD_REQUEST,"이미 존재하는 회원입니다."),

    NOT_VALID_PASSWORD(HttpStatus.BAD_REQUEST,"잘못된 패스워드 입니다.")

    ;



    private final HttpStatus httpStatus;

    private final String detail;
    }
