package com.jungle.board.user.domain.dto.response;

import lombok.Builder;

@Builder
public class CreateUserResponse {
    private Long id;
    private String token;
//    private String accessToken;
//    private String refreshToken;
}
