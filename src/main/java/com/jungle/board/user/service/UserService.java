package com.jungle.board.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.jungle.board.user.domain.User;
import com.jungle.board.user.domain.UserRepository;

@Slf4j
@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(final User user) {
        if(user == null || user.getNickname() == null || user.getEmail() == null) {
            throw new RuntimeException("Invalid arguments");
        }

        final String nickname = user.getNickname();
        if (userRepository.existsByNickname(nickname)) {
            throw new RuntimeException("Nickname already exists");
        }

        return userRepository.save(user);
    }

    public User getByCredentials(final String userId, final String password) {
        return userRepository.findByUserIdAndPassword(userId, password);
    }
}