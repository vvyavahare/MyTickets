package com.reserve.service;

import com.reserve.dto.BookingDto;
import com.reserve.model.Booking;

import java.util.Optional;

public interface IBookingService {
    Booking createBooking(BookingDto Dto);

//    BookingDto findFlightById(Long id);

//    List<BookingDto> findAllFlights(Optional<String> origin, Optional<String> orderBy);
}