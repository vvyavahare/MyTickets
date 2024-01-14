package com.reserve.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Passenger extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;
    private String prefix;
    private String firstName;
    private String lastName;
    private LocalDateTime dateOfBirth;
    @ManyToMany(mappedBy = "passengerDetails")//(cascade = CascadeType.ALL)
//    @JoinColumn(name = "booking_id")
    private List<Booking> booking;
}
