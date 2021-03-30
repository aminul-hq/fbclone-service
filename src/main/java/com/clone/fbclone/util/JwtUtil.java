package com.clone.fbclone.util;

import com.clone.fbclone.entities.UserEntity;
import com.clone.fbclone.services.model.UserDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Aminul Hoque
 * @since 2021-03-17
 */

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolved) {
        final Claims claims = extractAllClaims(token);
        return claimsResolved.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserEntity entity) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, entity);
    }

    private String createToken(Map<String, Object> claims, UserEntity entity) {
        return Jwts.builder().setClaims(claims).setSubject(entity.getEmail()).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDTO entity) {
        final String user = extractEmail(token);
        return (user.equals(entity.getEmail()) && !isTokenExpired(token));
    }
}
