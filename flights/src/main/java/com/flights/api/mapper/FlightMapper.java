package com.flights.api.mapper;


import com.flights.api.dto.FlightDto;
import com.flights.api.model.Flight;
import com.flights.api.model.FlightESEntity;

import java.util.ArrayList;
import java.util.List;

public class FlightMapper {
    public static FlightDto mapToDto(Flight flight, FlightDto model) {
//        model.setId(flight.getId().intValue());
        model.setDestination(flight.getDestination());
        model.setFlight(flight.getFlight());
        model.setOrigin(flight.getOrigin());
        model.setSpeedSeries(flight.getSpeedSeries());
        model.setDepartureTime(flight.getDepartureTime());
        model.setCapacity(flight.getCapacity());
        model.setFlightNumber(flight.getFlightNumber());
        return model;
    }

    public static Flight mapToFlights(FlightDto flightDto, Flight flight) {
        flight.setDestination(flightDto.getDestination());
        flight.setOrigin(flightDto.getOrigin());
        flight.setSpeedSeries(flightDto.getSpeedSeries());
        flight.setFlight(flightDto.getFlight());
        flight.setCapacity(flightDto.getCapacity());
        flight.setDepartureTime(flightDto.getDepartureTime());
        flight.setFlightNumber(flightDto.getFlightNumber());
        return flight;
    }

    public static FlightESEntity mapToFlightEsEntity(FlightESEntity esEntity, Flight flight) {
        esEntity.setDestination(flight.getDestination());
        esEntity.setOrigin(flight.getOrigin());
        esEntity.setSpeedSeries(flight.getSpeedSeries());
        esEntity.setFlight(flight.getFlight());
        esEntity.setCapacity(flight.getCapacity());
        esEntity.setDepartureTime(flight.getDepartureTime());
        esEntity.setFlightNumber(flight.getFlightNumber());
        return esEntity;
    }

    public static List<FlightDto> mapToFlightDtosList(List<FlightDto> flightDtos, List<Flight> flights) {
        flights.forEach(s -> flightDtos.add(mapToDto(s, new FlightDto())));
        return flightDtos;
    }
}
