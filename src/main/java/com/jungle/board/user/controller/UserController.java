package com.jungle.board.user.controller;

import com.jungle.board.common.CommonResponse;
import com.jungle.board.user.domain.dto.request.LogInUserRequest;
import com.jungle.board.user.domain.dto.response.CreateUserResponse;
import com.jungle.board.user.domain.dto.response.LoginUserResponse;
import com.jungle.board.user.domain.dto.response.RefreshTokenReponse;
import com.jungle.board.user.exception.UserException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jungle.board.user.domain.dto.request.CreateUserRequest;
import com.jungle.board.user.service.UserService;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("api/user")
public class UserController {

    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody CreateUserRequest createUserRequest) {
        try {
            userService.create(createUserRequest);
            log.info("User registered successfully");
            return ResponseEntity.ok(CreateUserResponse.builder()
                    .message(UserException.REGISTER_SUCCESS.getMessage())
                    .build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(CommonResponse.builder()
                    .code(UserException.REGISTER_FAIL.getCode())
                    .message(UserException.REGISTER_FAIL.getMessage())
                    .build());
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LogInUserRequest logInUserRequest) {
        try {
            LoginUserResponse loginUserResponse = userService.authenticate(logInUserRequest);
            if (loginUserResponse != null) {
                return ResponseEntity.ok(loginUserResponse);
            } else {
                return ResponseEntity.badRequest().body(CommonResponse.builder()
                        .code(UserException.LOGIN_FAIL.getCode())
                        .message(UserException.LOGIN_FAIL.getMessage())
                        .build());
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(CommonResponse.builder()
                    .code(UserException.LOGIN_FAIL.getCode())
                    .message(UserException.LOGIN_FAIL.getMessage())
                    .build());
        }
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(HttpServletRequest request) {
        RefreshTokenReponse refreshTokenResponse = userService.refreshToken(request);
        if(refreshTokenResponse != null) {
            return ResponseEntity.ok(refreshTokenResponse);
        } else {
            return ResponseEntity.badRequest().body(CommonResponse.builder()
                    .code(HttpStatus.UNAUTHORIZED.toString())
                    .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                    .build());
        }
    }
}
