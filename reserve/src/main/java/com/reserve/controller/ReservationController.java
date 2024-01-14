package com.reserve.controller;

import com.reserve.dto.BookingDto;
import com.reserve.dto.ErrorResponseDto;
import com.reserve.dto.FlightDto;
import com.reserve.service.FlightsFeignClient;
import com.reserve.service.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
//@AllArgsConstructor
public class ReservationController {


    @Autowired
    FlightsFeignClient client;

    @Autowired
    IBookingService service;

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

    @PostMapping("/reserve")
    public ResponseEntity<BookingDto> reserve(@RequestBody BookingDto bookingDto) {
        log.debug("creating a new reservation:{}", bookingDto);
        service.createBooking(bookingDto);
//        ResponseEntity<List<FlightDto>> flightDtoList = client.fetchFlightDetails(origin, Optional.empty());

        return ResponseEntity.status(HttpStatus.CREATED).body(bookingDto);
    }

    @GetMapping("/flights")
    public ResponseEntity<List<FlightDto>> getFlights(@RequestParam Optional<String> origin,
                                                      @RequestParam Optional<String> orderBy) {
        ResponseEntity<List<FlightDto>> flightDtoListResponse = client.fetchFlightDetails(origin.orElse(null), orderBy.orElse(null));
        return flightDtoListResponse;
    }

}