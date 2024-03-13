package net.crud.crud.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Date;

@AllArgsConstructor
public class JwtUtil {

    public String generateToken(UserDetails userDetails){
        Algorithm algorithm = Algorithm.HMAC256("senha123");

        return JWT.create()
                .withIssuer("WEG")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 300))
                .withSubject(userDetails.getUsername())
                .sign(algorithm);
    }


    public String getUsername(String token){
        return JWT.decode(token).getSubject();
    }
}
