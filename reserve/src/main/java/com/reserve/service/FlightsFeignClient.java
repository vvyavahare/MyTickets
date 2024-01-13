package com.reserve.service;

import com.reserve.dto.FlightDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@FeignClient("flights")
public interface FlightsFeignClient {

    @GetMapping(value = "/flight", consumes = "application/json")
    public ResponseEntity<List<FlightDto>> fetchFlightDetails(@RequestParam String origin, @RequestParam String orderBy);
//    public ResponseEntity<List<FlightDto>> fetchFlightDetails(@RequestParam Optional<String> origin, @RequestParam Optional<String> orderBy);

}