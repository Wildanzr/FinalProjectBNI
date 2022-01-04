package com.wildannn.apigateway.util;


import com.wildannn.apigateway.exception.JwtTokenMalformedException;
import com.wildannn.apigateway.exception.JwtTokenMissingException;
import io.jsonwebtoken.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class JwtUtil {

    private final String jwtSecret = "kucingmeong";

    public Claims getClaims(final String token) {
        try {
            return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " => " + e);
        }
        return null;
    }

    public void validateToken(final String token) throws JwtTokenMalformedException, JwtTokenMissingException {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
        } catch (SignatureException ex) {
            log.info("A");
            throw new JwtTokenMalformedException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.info("B");
            throw new JwtTokenMalformedException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.info("C");
            throw new JwtTokenMalformedException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.info("D");
            throw new JwtTokenMalformedException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.info("E");
            throw new JwtTokenMissingException("JWT claims string is empty.");
        }
    }

}