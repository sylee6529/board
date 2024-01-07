package com.jungle.board.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.jungle.board.user.domain.User;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
public class TokenProvider {
    private final String SECRET_KEY;

    public TokenProvider(@Value("${jwt.secret-key}") String secretKey) {
        this.SECRET_KEY = secretKey;
    }

    public String create(User user) {
        Date expiredAt = Date.from(
                LocalDateTime
                        .now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
                        .plus(1, ChronoUnit.DAYS)
        );

        return Jwts.builder()
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .setSubject(user.getId().toString())
                .setIssuer("jungleboard")
                .setIssuedAt(new Date())
                .setExpiration(expiredAt)
                .compact();
    }
}
