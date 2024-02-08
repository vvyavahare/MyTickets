package com.flights.api.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flights.api.dto.Booking;
import com.flights.api.model.Flight;
import com.flights.api.model.FlightESEntity;
import com.flights.api.repository.FlightESRepository;
import com.flights.api.repository.FlightRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
public class KafkaConsumer {

    @Autowired
    FlightESRepository esRepository;

    @Autowired
    FlightRepository dbRepository;

    @Autowired
    ObjectMapper mapper;

    @KafkaListener(topics = "my-topic")
    public void consumeMessage(String message) {
        Booking booking = null;
        Flight dbFlight = null;
        log.info("Logger Received message: " + message);
        try {
            if (!StringUtils.isEmpty(message)) {
                booking = mapper.readValue(message, Booking.class);
            } else {
                log.info("Empty message received in kafka");
            }
        } catch (JsonProcessingException e) {
            log.error("Error in parsing message received in Kafka, check with published if anything has changed in message format.");
        }

        if (booking != null) {
            dbFlight = dbRepository.findByFlightNumber(booking.getFlightNumber());
            if (dbFlight != null) {
                dbFlight.setCapacity(dbFlight.getCapacity() - booking.getSeatCount());
                dbRepository.save(dbFlight);
            }
        }
        FlightESEntity flight = new FlightESEntity();
        flight.setId(ThreadLocalRandom.current().nextLong());
        flight.setFlight(dbFlight.getFlight());
        flight.setFlightNumber(dbFlight.getFlightNumber());
        flight.setDestination(dbFlight.getDestination());
        flight.setOrigin(dbFlight.getOrigin());
        flight.setCapacity(dbFlight.getCapacity());


        esRepository.save(flight);
        log.info("Saved data to ES, message: " + message);
    }
}
