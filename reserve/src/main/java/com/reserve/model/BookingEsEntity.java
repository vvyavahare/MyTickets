package com.reserve.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "bookings_index")

@Data
public class BookingEsEntity
//        extends BaseEntity
{
    @Id
    @GeneratedValue
    private Long id;
    private String flightNumber;
    private int seatCount;
    private LocalDateTime bookingDate;
    private Long finalBookingPriceInEuro;
    private List<Passenger> passengerDetails;
}