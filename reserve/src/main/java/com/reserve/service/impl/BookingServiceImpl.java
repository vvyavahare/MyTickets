package com.reserve.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.reserve.dto.BookingDto;
import com.reserve.mapper.BookingsMapper;
import com.reserve.model.Booking;
import com.reserve.model.BookingEsEntity;
import com.reserve.repository.BookingEsRepository;
import com.reserve.repository.BookingRepository;
import com.reserve.service.IBookingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class BookingServiceImpl implements IBookingService {
    private BookingRepository repository;
    private KafkaTemplate<String, String> template;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BookingEsRepository esRepository;

    @Override
    public Booking createBooking(BookingDto dto) {

        Booking booking = BookingsMapper.mapToEntity(dto, new Booking());
        Booking savedEntity = repository.save(booking);
        esRepository.save(BookingsMapper.mapToBookingEsEntity(new BookingEsEntity(),savedEntity));
        try {
            extracted(savedEntity);
        } catch (JsonProcessingException e) {
            log.error("Error to send data to Kafka Cluster: ", e);
        }
        return savedEntity;
    }

    private void extracted(Booking savedEntity) throws JsonProcessingException {
        template.send("my-topic", mapper.writeValueAsString(savedEntity));
        log.info("Sent kafka message successfully");
    }
}