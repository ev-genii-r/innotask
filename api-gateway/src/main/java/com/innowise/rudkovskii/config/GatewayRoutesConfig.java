package com.innowise.rudkovskii.config;

import com.innowise.rudkovskii.filter.JwtRouteFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    private final JwtRouteFilter jwtRouteFilter;

    public GatewayRoutesConfig(JwtRouteFilter jwtRouteFilter) {
        this.jwtRouteFilter = jwtRouteFilter;
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-public", r -> r.path("/api/auth/login", "/api/auth/register")
                        .uri("http://localhost:8083"))

                .route("auth-secured", r -> r.path("/api/**")
                        .filters(f -> f.filter(jwtRouteFilter))
                        .uri("http://localhost:8083"))

                .route("user-service", r -> r.path("/api/**")
                        .filters(f -> f.filter(jwtRouteFilter))
                        .uri("http://localhost:8082"))

                .build();
    }
}
