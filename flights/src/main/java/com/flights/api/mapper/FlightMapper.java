package com.flights.api.mapper;


import com.flights.api.dto.FlightDto;
import com.flights.api.model.Flight;

import java.util.ArrayList;
import java.util.List;

public class FlightMapper {
    public static FlightDto mapToDto(Flight flight, FlightDto model) {
//        model.setId(flight.getId().intValue());
        model.setDestination(flight.getDestination());
        model.setFlight(flight.getFlight());
        model.setOrigin(flight.getOrigin());
        model.setSpeedSeries(flight.getSpeedSeries());
        return model;
    }

    public static Flight mapToFlights(FlightDto flightDto, Flight flight) {
        flight.setDestination(flightDto.getDestination());
        flight.setOrigin(flightDto.getOrigin());
        flight.setSpeedSeries(flightDto.getSpeedSeries());
        flight.setFlight(flightDto.getFlight());
        return flight;
    }

    public static List<FlightDto> mapToFlightDtosList(List<FlightDto> flightDtos, List<Flight> flights) {
        flights.forEach(s -> flightDtos.add(mapToDto(s, new FlightDto())));
        return flightDtos;
    }
}
