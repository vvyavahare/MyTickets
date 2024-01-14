package com.reserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Booking extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;
    private String flightNumber;
    private int seatCount;
    private LocalDateTime bookingDate;
    private Long finalBookingPriceInEuro;

//    @OneToMany
//    private Passenger bookingPersonId;

    @ManyToMany//(fetch = FetchType.LAZY, mappedBy = "booking", cascade = CascadeType.ALL)
    @JoinTable(
            name = "booking_passenger",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    private List<Passenger> passengerDetails;
}
