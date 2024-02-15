package com.flights.api.service.impl;

import com.flights.api.constants.FlightsConstants;
import com.flights.api.dto.FlightDto;
import com.flights.api.exception.ResourceNotFoundException;
import com.flights.api.exception.ResponseNotFoundException;
import com.flights.api.mapper.FlightMapper;
import com.flights.api.model.Flight;
import com.flights.api.model.FlightESEntity;
import com.flights.api.repository.FlightESRepository;
import com.flights.api.repository.FlightRepository;
import com.flights.api.service.IFlightService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class FlightServiceImpl implements IFlightService {
    private FlightRepository flightRepository;
    private FlightESRepository elasticRepository;


    @Override
    public Flight createFlight(FlightDto flightDto) {

        Flight flight = FlightMapper.mapToFlights(flightDto, new Flight());
        Flight savedFlight = flightRepository.save(flight);
        elasticRepository.save(FlightMapper.mapToFlightEsEntity(new FlightESEntity(), savedFlight));
        return savedFlight;

    }

    @Override
    public FlightDto findFlightById(Long id) {
        Flight flight = flightRepository.findById(id.intValue()).orElseThrow(
                () -> new ResourceNotFoundException("Flight", "Id", id.toString())
        );
        FlightDto flightDto = FlightMapper.mapToDto(flight, new FlightDto());
        return flightDto;
    }

    @Override
    public List<FlightDto> findAllFlights(Optional<String> origin, Optional<String> orderBy) {
        if (origin.isPresent()) {
            List<Flight> flights = flightRepository.findByOrigin(origin.get());
            List<FlightDto> dtoList = new ArrayList<>();
            dtoList = FlightMapper.mapToFlightDtosList(dtoList, flights);
            return dtoList;
        }
        if (orderBy.isPresent()) {
            Optional<List<Flight>> response = getOrderedListResponse(orderBy);
            if (response.isPresent()) {
                List<FlightDto> dtoList = new ArrayList<>();
                return FlightMapper.mapToFlightDtosList(dtoList, response.get());
            } else {
                throw new ResponseNotFoundException(FlightsConstants.FLIGHT_RESOURCE, FlightsConstants.ID_RESOURCE, FlightsConstants.ORDERBY_RESOURCE);
            }
        }
        return null;
    }

    private Optional<List<Flight>> getOrderedListResponse(Optional<String> orderBy) {
        String val = orderBy.get();
        if (FlightsConstants.DESTINATION.equalsIgnoreCase(val)) {
            return Optional.of(flightRepository.findAll(Sort.by("destination")));
        } else if (FlightsConstants.REVERSE_DESTINATION.equalsIgnoreCase(val)) {
            return Optional.of(flightRepository.findAll(Sort.by(Sort.Direction.DESC, "destination")));
        }
        return Optional.empty();
    }

}