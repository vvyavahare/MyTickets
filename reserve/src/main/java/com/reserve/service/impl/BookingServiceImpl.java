package com.reserve.service.impl;

import com.reserve.dto.BookingDto;
import com.reserve.mapper.BookingsMapper;
import com.reserve.model.Booking;
import com.reserve.repository.BookingRepository;
import com.reserve.service.IBookingService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingServiceImpl implements IBookingService {
    private BookingRepository repository;
    private KafkaTemplate<String, String> template;

    @Override
    public Booking createBooking(BookingDto dto) {

        Booking booking = BookingsMapper.mapToEntity(dto, new Booking());
        Booking savedEntity = repository.save(booking);
        template.send("my-topic", savedEntity.getSeatCount() + " bookings done successfully");
        return savedEntity;
    }
}