package com.wildannn.user.util;

import com.wildannn.user.exception.JwtTokenMalformedException;
import com.wildannn.user.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Log4j2
public class JwtUtil {

    private final String jwtSecret = "kucingmeong";

    private final long tokenValidity = 600000L;

    public Claims getClaims(final String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) {
            System.out.println(e.getMessage() + " -> " + e);
        }
        return null;
    }

    public String generateToken(String id) {
        Claims claims = Jwts.claims().setSubject(id);
        long nonMillis = System.currentTimeMillis();
        long expMillis = nonMillis + tokenValidity;
        Date exp = new Date(expMillis);

        return Jwts.builder().setClaims(claims)
                .setIssuedAt(new Date(nonMillis))
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            throw new JwtTokenMalformedException("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            throw new JwtTokenMissingException("JWT claims string is empty");
        }
    }

    public boolean validate(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        validateToken(token);

        return true;
    }

    public String getUsername(String token) {
        Claims claims = getClaims(token);

        return claims.get("sub").toString();
    }
}















