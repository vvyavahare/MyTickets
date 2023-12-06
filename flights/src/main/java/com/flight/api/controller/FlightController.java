package com.flight.api.controller;

import java.util.List;
import java.util.Optional;

import com.flight.api.constants.FlightsConstants;
import com.flight.api.exception.ResponseNotFoundException;
import com.flight.api.mapper.FlightMapper;
import com.flight.api.model.Flight;
import com.flight.api.model.FlightModel;
import com.flight.api.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {


    @Autowired
    private FlightRepository repository;

    @PostMapping("/flight")
    public ResponseEntity<FlightModel> createFlight(@RequestBody Flight flight) {
        Flight flightCreated = repository.save(flight);
        return ResponseEntity.status(HttpStatus.CREATED).body(FlightMapper.mapToDto(flightCreated, new FlightModel()));
    }

    @GetMapping("/flight")
    public ResponseEntity<List<Flight>> getAllFlights(@RequestParam Optional<String> origin, @RequestParam Optional<String> orderBy) {
        if (origin.isPresent()) {
            return ResponseEntity.ok().body(repository.findByOrigin(origin.get()));
        }
        if (orderBy.isPresent()) {
            Optional<ResponseEntity<List<Flight>>> response = getOrderedListResponse(orderBy);
            if (response.isPresent()) {
                return response.get();
            } else {
                throw new ResponseNotFoundException(FlightsConstants.FLIGHT_RESOURCE, FlightsConstants.ID_RESOURCE, FlightsConstants.ORDERBY_RESOURCE);
            }
        }
        return ResponseEntity.ok().body(repository.findAll());
    }


    @GetMapping("/flight/{id}")
    public ResponseEntity<Flight> findFlightById(@PathVariable Integer id) {
        Optional<Flight> flightOptional = repository.findById(id);
        if (flightOptional.isPresent()) {
            return ResponseEntity.ok().body(repository.findById(id).get());
        } else {
            throw new ResponseNotFoundException(FlightsConstants.FLIGHT_RESOURCE, FlightsConstants.ID_RESOURCE, id.toString());
        }
    }

    private Optional<ResponseEntity<List<Flight>>> getOrderedListResponse(Optional<String> orderBy) {
        String val = orderBy.get();
        if (FlightsConstants.DESTINATION.equalsIgnoreCase(val)) {
            return Optional.of(ResponseEntity.ok().body(repository.findAll(Sort.by("destination"))));
        } else if (FlightsConstants.REVERSE_DESTINATION.equalsIgnoreCase(val)) {
            return Optional.of(ResponseEntity.ok().body(repository.findAll(Sort.by(Sort.Direction.DESC, "destination"))));
        }
        return Optional.empty();
    }
}
