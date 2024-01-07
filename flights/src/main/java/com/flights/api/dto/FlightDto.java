package com.flights.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;


@Data
@Schema(
        name = "Flights",
        description = "Schema to hold Flights information"
)
public class FlightDto {

//    @NotEmpty(message = "AccountNumber can not be a null or empty")
//    @Pattern(regexp = "(^$|[0-9]{10})", message = "AccountNumber must be 10 digits")
//    @Schema(
//            description = "Account Number of Bank account", example = "3454433243"
//    )
//    private Long flightNumber;

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


}