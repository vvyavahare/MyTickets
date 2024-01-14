package com.reserve.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
class PassengerBookingKey implements Serializable {

    @Column(name = "passenger_id")
    Long passengerId;

    @Column(name = "booking_id")
    Long bookingId;

}