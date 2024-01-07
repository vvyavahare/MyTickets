package com.flights.api.controller;


import com.flights.api.config.FlightServiceConfig;
import com.flights.api.constants.FlightsConstants;
import com.flights.api.dto.ErrorResponseDto;
import com.flights.api.dto.FlightDto;
import com.flights.api.exception.ResponseNotFoundException;
import com.flights.api.mapper.FlightMapper;
import com.flights.api.model.Flight;
import com.flights.api.repository.FlightRepository;
import com.flights.api.service.IFlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
public class FlightController {

    @Autowired
    IFlightService flightService;

    @Operation(
            summary = "Create Flight REST API",
            description = "REST API to create new Flight detail inside Air travel system"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    @PostMapping("/flight")
    public ResponseEntity<FlightDto> createFlight(@RequestBody FlightDto flightDto) {
        log.debug("creating new flight with origin:{} & destination:{}", flightDto.getOrigin(), flightDto.getDestination());
        Flight flightCreated = flightService.createFlight(flightDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(FlightMapper.mapToDto(flightCreated, new FlightDto()));
    }


    @GetMapping("/flight")
    public ResponseEntity<List<FlightDto>> getAllFlights(@RequestParam Optional<String> origin, @RequestParam Optional<String> orderBy) {
        log.debug("searching all flights for origin:{} & order By:{}", origin.get(), orderBy.get());

        List<FlightDto> flights = flightService.findAllFlights(origin, orderBy);
        return ResponseEntity.ok().body(flights);
    }

    @GetMapping("/flight/{id}")
    public ResponseEntity<FlightDto> findFlightById(@PathVariable Integer id) {
        FlightDto flightDto = flightService.findFlightById(id.longValue());
        return ResponseEntity.status(HttpStatus.OK).body(flightDto);
    }

}
