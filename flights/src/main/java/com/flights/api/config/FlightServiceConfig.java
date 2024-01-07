package com.flights.api.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "flights")
@Data
@ToString
public class FlightServiceConfig {

    private String msg;
    private String message;
    private String buildVersion;
    private Map<String, String> contactDetails;
    private List<String> onCallSupport;
}
