package com.innowise.rudkovskii.filter;

import com.innowise.rudkovskii.config.JwtTokenProvider;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtRouteFilter implements GatewayFilter, Ordered {

    private final JwtTokenProvider jwtTokenProvider;

    public JwtRouteFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getPath().value();

        if (path.startsWith("/api/auth/login") || path.startsWith("/api/auth/register")) {
            return chain.filter(exchange);
        }

        String token = extractToken(exchange.getRequest());
        System.out.println(token);

        if (token == null) {
            return onError(exchange, "Missing JWT token", HttpStatus.UNAUTHORIZED);
        }

        if (!jwtTokenProvider.validateToken(token)) {
            return onError(exchange, "Invalid JWT token", HttpStatus.UNAUTHORIZED);
        }

        String username = jwtTokenProvider.getUsernameFromToken(token);
        ServerHttpRequest mutatedRequest = exchange.getRequest().mutate()
                .header("X-User-Id", username)
                .build();

        return chain.filter(exchange.mutate().request(mutatedRequest).build());
    }

    private String extractToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private Mono<Void> onError(ServerWebExchange exchange, String err, HttpStatus status) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        return response.writeWith(Mono.just(response.bufferFactory().wrap(err.getBytes())));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
