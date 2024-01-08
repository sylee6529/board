package com.jungle.board.user.exception;

import lombok.Getter;

@Getter
public enum UserException {
    REGISTER_SUCCESS("회원가입에 성공하였습니다.", "200"),
    REGISTER_FAIL("회원가입에 실패하였습니다.", "400"),
    LOGIN_SUCCESS("로그인에 성공하였습니다.", "200"),
    LOGIN_FAIL("로그인에 실패하였습니다.", "400");


    private String message;
    private String code;

    UserException(String message, String code) {
        this.message = message;
        this.code = code;
    }
}
