package com.wildannn.apigateway.config;

import com.wildannn.apigateway.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("http://localhost:8100"))
                .route("user", r -> r.path("/users/**").filters(f -> f.filter(filter)).uri("http://localhost:8100"))
                .route("post", r -> r.path("/posts/**").filters(f -> f.filter(filter)).uri("http://localhost:8300"))
                .build();
    }
}