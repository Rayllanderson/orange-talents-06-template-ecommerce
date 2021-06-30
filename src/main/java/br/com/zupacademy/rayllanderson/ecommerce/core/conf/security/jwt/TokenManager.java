package br.com.zupacademy.rayllanderson.ecommerce.core.conf.security.jwt;

import br.com.zupacademy.rayllanderson.ecommerce.users.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenManager {

    @Value("${ecommerce.jwt.expiration}")
    private String expirationInMillis;

    @Value("${ecommerce.jwt.secret}")
    private String secret;

    public String generateToken(Authentication authenticate) {
        UserDetails user = (UserDetails) authenticate.getPrincipal();
        var today = new Date();
        var expirationDate = new Date(today.getTime() + Long.parseLong(expirationInMillis));
        return Jwts.builder()
                .setIssuer("Api - Mercado livre By Rayllanderson")
                .setSubject(user.getUsername())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public boolean isTokenValid(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String getUserName(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
