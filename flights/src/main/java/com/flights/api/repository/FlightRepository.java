package com.flights.api.repository;


import com.flights.api.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
    List<Flight> findByOrigin(String origin);

    Flight findByFlightNumber(String flightNumber);
}




