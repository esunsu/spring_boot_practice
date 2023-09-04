package com.example.restfull.Exception;

import lombok.Getter;

@Getter
public class BoardException extends RuntimeException {
    private final ErrorCode errorCode;

    public BoardException(ErrorCode errorCode) {
        super(errorCode.getDetail());
        this.errorCode = errorCode;
    }
}
