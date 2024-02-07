package com.flights.api.repository;

import com.flights.api.model.FlightESEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface FlightESRepository extends ElasticsearchRepository<FlightESEntity, String> {
}
