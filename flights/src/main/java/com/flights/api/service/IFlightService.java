package com.flights.api.service;

import com.flights.api.dto.FlightDto;
import com.flights.api.model.Flight;

import java.util.List;
import java.util.Optional;

public interface IFlightService {
    Flight createFlight(FlightDto flightDto);

    FlightDto findFlightById(Long id);

    List<FlightDto> findAllFlights(Optional<String> origin, Optional<String> orderBy);
}
