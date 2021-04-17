package exercise.cloud.userservice.security;

import exercise.cloud.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;


@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomService customService;
    private final UserService userService;
    @Value("${token.expiration_time}")
    private String expirationTime;
    @Value("${token.shared_key}")
    private String sharedKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.authorizeRequests().antMatchers("/users/**").permitAll();
        http

                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/**").authenticated()
                .and()
                .addFilter(new AuthenticationFilter(authenticationManager(), userService,
                        expirationTime, sharedKey))
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customService).passwordEncoder(passwordEncoder);
    }

}
