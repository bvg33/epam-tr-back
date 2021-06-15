package com.epam.tr.security.jwt;

import com.epam.tr.exceptions.NotValidTokenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static com.epam.tr.security.secret.JWTSecretHolder.JWT_SECRET;


@Component
public class JWTProvider {
    private static final String EXCEPTION = "Not valid token";

    public String generateToken(String login) {
        Date date = Date.from(LocalDate.now().plusDays(15).
                atStartOfDay(ZoneId.systemDefault()).toInstant());
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public boolean validateToken(String token) throws Exception {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new NotValidTokenException(EXCEPTION);
        }
    }

}