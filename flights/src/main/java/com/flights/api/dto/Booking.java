package com.flights.api.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class Booking {

    private Long bookingId;
    private String flightNumber;
    private int seatCount;
    private LocalDateTime bookingDate;
    private Long finalBookingPriceInEuro;

    private List<Passenger> passengerDetails;
}