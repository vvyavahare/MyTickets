package com.flights.api.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(indexName = "flights_index")
@Data
public class FlightESEntity
//        extends BaseEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flight;
    private String origin;
    private String destination;
    private List<Integer> speedSeries;
    private int capacity;
    private LocalDateTime departureTime;
    private String flightNumber;
    private String host;
}
