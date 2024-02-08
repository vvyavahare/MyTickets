package com.flights.api.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class Passenger {
    private Long passengerId;
    private String prefix;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    private List<Booking> booking;
}