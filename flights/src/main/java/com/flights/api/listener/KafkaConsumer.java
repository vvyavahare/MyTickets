package com.flights.api.listener;

import com.flights.api.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    FlightRepository repository;
    @KafkaListener(topics = "my-topic")
    public void consumeMessage(String message) {
        log.info("Logger Received message: " + message);
        System.out.println("Received message: " + message);
    }
}
