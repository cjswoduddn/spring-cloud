package exercise.cloud.userservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.cloud.userservice.dto.UserDto;
import exercise.cloud.userservice.service.UserService;
import exercise.cloud.userservice.vo.RequestLogin;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final UserService userService;
    private final String expirationTime;
    private final String sharedKey;

    public AuthenticationFilter(AuthenticationManager authenticationManager, UserService userService,
                                String expirationTime, String sharedKey) {
        super(authenticationManager);
        this.userService = userService;
        this.expirationTime = expirationTime;
        this.sharedKey = sharedKey;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            RequestLogin requestLogin = new ObjectMapper().readValue(request.getInputStream(), RequestLogin.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestLogin.getEmail(),
                            requestLogin.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String username = ((User) authResult.getPrincipal()).getUsername();
        UserDto userDto = userService.getUserByEmail(username);
        SecretKey secretKey = Keys.hmacShaKeyFor(sharedKey.getBytes());
        String token = Jwts.builder()
                .setSubject(userDto.getUserId())
                .setExpiration(new Date(System.currentTimeMillis()+Long.parseLong(expirationTime)))
                .signWith(secretKey)
                .compact();

        response.addHeader("token", token);
        response.addHeader("userId", userDto.getUserId());
    }
}