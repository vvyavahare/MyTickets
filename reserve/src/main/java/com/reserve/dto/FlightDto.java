package com.reserve.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;


@Data
@Schema(
        name = "Flights",
        description = "Schema to hold Flights information"
)
public class FlightDto {

    @NotEmpty(message = "Flight Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Flight Number must be 10 digits")
    @Schema(
            description = "Flight Number of Carrier", example = "3454433243"
    )
    private String flightNumber;

    @NotEmpty(message = "Flight Name can not be a null or empty")
    @Schema(
            description = "Flight name of AirTravel system", example = "KLM"
    )
    private String flight;

    @NotEmpty(message = "Origin can not be a null or empty")
    @Schema(
            description = "Origin of flight", example = "Schiphol"
    )
    private String origin;

    @NotEmpty(message = "destination can not be a null or empty")
    @Schema(
            description = "Destination of flight", example = "JFK Newyork"
    )
    private String destination;

    @NotEmpty(message = "Speed series of the flight can not be empty or null")
    @Schema(
            description = "Speed series of the flight", example = "737"
    )
    private List<Integer> speedSeries;

    @NotEmpty(message = "Capacity of the flight can not be empty or null")
    @Schema(
            description = "Capacity of the flight ", example = "737"
    )
    private int capacity;

    @NotEmpty(message = "flight departure time can not be empty or null")
    @Schema(
            description = "flight departure time  ", example = "2023-15-01:18.22.00"
    )
    private LocalDateTime departureTime;

}