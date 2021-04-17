package exercise.cloud.gatewayserver.filter;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
@Slf4j
public class AuthorizationFilter extends AbstractGatewayFilterFactory<AuthorizationFilter.Config> {

    @Value("${token.shared_key}")
    private String sharedKey;

    public AuthorizationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();

            if (!request.getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                return onError(exchange, "No Authorization Header", HttpStatus.UNAUTHORIZED);
            }

            String authorizationHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
            String userId = request.getHeaders().get("User-Id").get(0);
            String jwt = authorizationHeader.replace("Bearer", "");
            if (!isJwtValid(jwt, userId)) {
                return onError(exchange, "JWT Token is not Valid!", HttpStatus.UNAUTHORIZED);
            }
            return chain.filter(exchange);
        });

    }

    private boolean isJwtValid(String jwt, String userId) {
        try {
            return
                    Jwts.parserBuilder()
                            .setSigningKey(Keys.hmacShaKeyFor(sharedKey.getBytes()))
                            .build().parseClaimsJws(jwt).getBody().getSubject().equals(userId)
                    ;
        } catch (JwtException e) {
            return false;
        }

    }

    // Mono, Flux -> Spring WebFlux의 반환형
    private Mono<Void> onError(ServerWebExchange exchange, String message, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        log.error(message);
        return response.setComplete();
    }

    public static class Config {
    }

    ;
}
