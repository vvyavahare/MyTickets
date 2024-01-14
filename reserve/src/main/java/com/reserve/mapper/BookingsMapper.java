package com.reserve.mapper;

import com.reserve.dto.BookingDto;
import com.reserve.model.Booking;

import java.util.List;

public class BookingsMapper {
    public static BookingDto mapToDto(Booking entity, BookingDto dto) {
        dto.setBookingDate(entity.getBookingDate());
        dto.setFinalBookingPriceInEuro(entity.getFinalBookingPriceInEuro());
        dto.setFlightNumber(entity.getFlightNumber());
        dto.setSeatCount(entity.getSeatCount());
        dto.setPassengerDetails(entity.getPassengerDetails());
        return dto;
    }

    public static Booking mapToEntity(BookingDto dto, Booking entity) {
        entity.setBookingDate(dto.getBookingDate());
        entity.setFinalBookingPriceInEuro(dto.getFinalBookingPriceInEuro());
        entity.setSeatCount(dto.getSeatCount());
        entity.setPassengerDetails(dto.getPassengerDetails());
        entity.setFlightNumber(dto.getFlightNumber());
        return entity;
    }

    public static List<BookingDto> mapEntityListToDtosList(List<BookingDto> dtos, List<Booking> entities) {
        entities.forEach(s -> dtos.add(mapToDto(s, new BookingDto())));
        return dtos;
    }
}