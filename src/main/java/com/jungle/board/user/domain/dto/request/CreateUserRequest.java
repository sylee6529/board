package com.jungle.board.user.domain.dto.request;

import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;
import com.jungle.board.user.domain.User;
import com.jungle.board.user.domain.type.ProviderType;

import java.time.LocalDate;

@Getter
public class CreateUserRequest {
    private String userId;
    private String nickname;
    private String email;
    private String password;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private ProviderType authProvider;

    public User toEntity(CreateUserRequest createUserRequest) {
        return User.builder()
                .userId(createUserRequest.getUserId())
                .nickname(createUserRequest.getNickname())
                .email(createUserRequest.getEmail())
                .password(createUserRequest.getPassword())
                .birth(createUserRequest.getBirth())
                .authProvider(createUserRequest.getAuthProvider())
                .build();
    }
}
