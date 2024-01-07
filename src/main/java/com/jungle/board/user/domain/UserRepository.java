package com.jungle.board.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNickname(String nickname);
    Boolean existsByNickname(String nickname);
    User findByUserIdAndPassword(String userId, String password);
}
