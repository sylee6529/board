package com.jungle.board.user.controller;

import com.jungle.board.common.CommonResponse;
import com.jungle.board.user.domain.dto.request.LogInUserRequest;
import com.jungle.board.util.TokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jungle.board.user.domain.User;
import com.jungle.board.user.domain.dto.UserDto;
import com.jungle.board.user.domain.dto.request.CreateUserRequest;
import com.jungle.board.user.service.UserService;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserController {

    private UserService userService;
    private TokenProvider tokenProvider;

    public UserController(UserService userService, TokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest createUserRequest) {
        try {
            if(createUserRequest == null || createUserRequest.getNickname() == null || createUserRequest.getEmail() == null) {
                throw new RuntimeException("Invalid arguments");
            }
            User user = userService.create(createUserRequest.toEntity(createUserRequest));
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            log.error("Failed to register com.jungle.jungleboard.user", e);
            CommonResponse commonResponse = CommonResponse.builder()
                    .code("400")
                    .message("Failed to register com.jungle.jungleboard.user")
                    .build();
            return ResponseEntity.badRequest().body(commonResponse);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LogInUserRequest logInUserRequest) {
        User user = userService.getByCredentials(logInUserRequest.getUserId(), logInUserRequest.getPassword());
        if (user != null) {
            final String token = tokenProvider.create(user);
            final UserDto responseUserDto = UserDto.builder()
                    .id(user.getId().toString())
                    .userId(user.getUserId())
                    .password(user.getPassword())
                    .token(token)
                    .build();
            return ResponseEntity.ok(responseUserDto);
        } else {
            CommonResponse commonResponse = CommonResponse.builder()
                    .code("400")
                    .message("Failed to login")
                    .build();
            return ResponseEntity.badRequest().body(commonResponse);
        }
    }
}
