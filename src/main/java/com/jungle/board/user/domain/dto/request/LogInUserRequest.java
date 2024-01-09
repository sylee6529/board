package com.jungle.board.user.domain.dto.request;

import lombok.Getter;

@Getter
public class LogInUserRequest {
    private String userId;
    private String password;
}
