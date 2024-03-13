package net.crud.crud.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtUtil {

    private final SecretKey key;

    public JwtUtil(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("senha123");
        this.key = Keys.hmacShaKeyFor(password.getBytes());
    }

    public String generateToken(UserDetails userDetails){

        return Jwts.builder()
                .issuer("WEG")
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 300000))
                .signWith(this.key, Jwts.SIG.HS256)
                .subject(userDetails.getUsername())
                //esse compact faz retornar uma string
                .compact();
    }

    private Jws<Claims> tokenValidation(String token){
        return getParser().parseSignedClaims(token);
    }

    private JwtParser getParser(){
        return Jwts.parser()
                .verifyWith(this.key)
                .build();
    }

    public String getUsername(String token){
        return tokenValidation(token)
                .getPayload()
                .getSubject();
    }
}
