package com.flights.api;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableFeignClients
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
//@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
@OpenAPIDefinition(
        info = @Info(
                title = "Flights microservice REST API Documentation",
                description = "Here you can find Flights microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Vishal Vyavahare",
                        email = "vishal@myairtravel.com",
                        url = "https://www.myairtravel.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "https://www.myairtravel.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Flights microservice REST API Documentation",
                url = "https://www.myairtravel.com/swagger-ui.html"
        )
)
public class FlightsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightsApplication.class, args);
    }

}
