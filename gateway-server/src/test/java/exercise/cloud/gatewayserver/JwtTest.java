package exercise.cloud.gatewayserver;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class JwtTest {

    @Test
    @DisplayName("jwt 테스팅")
    public void jwtTest() throws Exception{
        //given
        String sharedKey = "djflkjwenclksndlkjweofjseklfjldfsdfdfsdfsdfsdfsdfsdfso";
        SecretKey key = Keys.hmacShaKeyFor(sharedKey.getBytes());
        String subject = "Hello, Jwt";
        String token = Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis()+1000))
                .signWith(Keys.hmacShaKeyFor(sharedKey.getBytes()))
                .compact();

        //when
        assertEquals(
        Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(sharedKey.getBytes()))
                .build().parseClaimsJws(token).getBody().getSubject(),
                subject
        );
        //then
    }
}
