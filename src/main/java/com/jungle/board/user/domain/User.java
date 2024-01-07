package com.jungle.board.user.domain;

import com.jungle.board.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import com.jungle.board.user.domain.type.ProviderType;
import com.jungle.board.user.domain.type.RoleType;

import java.time.LocalDate;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "member", uniqueConstraints = {@UniqueConstraint(columnNames = "nickname")})
public class User extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String nickname;

    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    private String status;

    @Column(nullable = false)
    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private ProviderType authProvider;
}

