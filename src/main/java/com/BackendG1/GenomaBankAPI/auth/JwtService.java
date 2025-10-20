package com.BackendG1.GenomaBankAPI.auth;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class JwtService {
    private final SecretKey key;
    private final long expMinutes;

    public JwtService(@Value("${jwt.secret}") String secret,
                      @Value("${jwt.exp-min}") long expMinutes) {
        byte[] raw = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(raw);
        this.expMinutes = expMinutes;
    }

    public String generateToken(UserDetails userDetails) {
        Instant now = Instant.now();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .subject(userDetails.getUsername())
                .claims(Map.of("roles", roles))
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusSeconds(expMinutes * 60)))
                .signWith(key)
                .compact();
    }

    public Claims parse(String token) {
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}