package com.flights.api.listener;

import com.flights.api.model.FlightESEntity;
import com.flights.api.repository.FlightESRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    FlightESRepository repository;

    @KafkaListener(topics = "my-topic")
    public void consumeMessage(String message) {
        log.info("Logger Received message: " + message);
        FlightESEntity flight = new FlightESEntity();
        flight.setId(1L);
        flight.setFlight("KLM");
        flight.setFlightNumber("NUMR12344");
        flight.setDestination("Pune");
        flight.setOrigin("cdd  "+message);
        repository.save(flight);
        log.info("Saved data to ES, message: " + message);
    }
}
