package org.example.stylish.service.serviceImpl;

import io.jsonwebtoken.*;
import org.example.stylish.dto.TokenDto;
import org.example.stylish.dto.UserResponseDto;
import org.example.stylish.exception.EmptyTokenException;
import org.example.stylish.exception.InvalidTokenException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private String JWTSECRET = "qejweqejwiejiejqieqeqewewewqeqeqwewqewqewqewqewqewqewqweewqewqweewweqeeqqw";

    // set token expired in 1 hour
    private long EXPIRED_TIME = 3600000;

    public TokenDto createToken(UserResponseDto userResponseDto) {

        Claims claims = Jwts.claims();
        claims.put("provider", userResponseDto.getProvider());
        claims.put("name", userResponseDto.getName());
        claims.put("email", userResponseDto.getEmail());
        claims.put("picture", userResponseDto.getPicture());

        Date now = new Date(); // get the current time
        Date expiration = new Date(now.getTime() + EXPIRED_TIME);

        JwtBuilder builder = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, JWTSECRET);

        String token = builder.compact();

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);
        tokenDto.setExpiredTime(EXPIRED_TIME);

        return tokenDto;
    }

    public Claims parseToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(JWTSECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims;
    }

    public void validateToken(String token) {
        // exception for empty token and invalid token
        if (token == null || token.isBlank()) {
            throw new EmptyTokenException("No token");
        }
        try {
            Jwts.parser().setSigningKey(JWTSECRET).parseClaimsJws(token);
        } catch (JwtException e) {
            throw new InvalidTokenException("Invalid token", e);
        }
    }
}





