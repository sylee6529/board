package com.jungle.board.user.domain.dto.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LoginUserResponse {
    private Long userId;
    private String accessToken;
    private String refreshToken;
}
