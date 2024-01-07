package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayserverApplication.class, args);
    }

    @Bean
    public RouteLocator airTravelRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route(p -> p
                        .path("/flights/**")
                        .filters(f -> f.rewritePath("/flights/(?<segment>.*)", "/${segment}")
                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
                        .uri("lb://flights"))
//                .route(p -> p
//                        .path("/tickets/**")
//                        .filters(f -> f.rewritePath("/tickets/(?<segment>.*)", "/${segment}")
//                                .addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
//                        .uri("lb://airtravel-service"))
                .build();


    }


}
