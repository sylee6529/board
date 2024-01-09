package com.jungle.board.user.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jungle.board.user.domain.dto.request.CreateUserRequest;
import com.jungle.board.user.domain.dto.request.LogInUserRequest;
import com.jungle.board.user.domain.dto.response.LoginUserResponse;
import com.jungle.board.user.domain.dto.response.RefreshTokenReponse;
import com.jungle.board.user.domain.type.RoleType;
import com.jungle.board.util.TokenProvider;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.jungle.board.user.domain.User;
import com.jungle.board.user.domain.UserRepository;

import java.io.IOException;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public void create(final CreateUserRequest createUserRequest) {
        final String nickname = createUserRequest.getNickname();
        if (userRepository.existsByNickname(nickname)) {
            throw new RuntimeException("Nickname already exists");
        }

        userRepository.save(User.builder()
            .userId(createUserRequest.getUserId())
            .birth(createUserRequest.getBirth())
            .authProvider(createUserRequest.getAuthProvider())
            .password(passwordEncoder.encode(createUserRequest.getPassword()))
            .nickname(createUserRequest.getNickname())
            .role(RoleType.USER)
            .status("ACTIVE")
            .email(createUserRequest.getEmail())
            .build());
    }

    public User getByCredentials(final String userId, final String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }

    public LoginUserResponse authenticate(LogInUserRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserId(),
                        request.getPassword()
                )
        );

        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid username or password");
        }

        var user = userRepository.findByUserId(request.getUserId())
                .orElseThrow();

        String accessToken = tokenProvider.generateToken(user);
        String refreshToken = tokenProvider.generateRefreshToken(user);
        return LoginUserResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .build();
    }

    public RefreshTokenReponse refreshToken(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return null;
        }

        final String refreshToken = authHeader.substring(7);
        final String userId = tokenProvider.extractUsername(refreshToken);

        if (userId != null) {
            var user = this.userRepository.findByUserId(userId)
                    .orElseThrow();
            if (tokenProvider.isTokenValid(refreshToken, user)) {
                String accessToken = tokenProvider.generateToken(user);
                RefreshTokenReponse response = RefreshTokenReponse.builder()
                        .accessToken(accessToken)
                        .build();
                return response;
            }
        }

        return null;
    }
}