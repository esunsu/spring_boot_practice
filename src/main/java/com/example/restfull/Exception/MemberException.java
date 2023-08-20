package com.example.restfull.Exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.Objects;

@Getter
public class MemberException extends RuntimeException {
    private final ErrorCode errorCode;
    public MemberException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }
}
