package com.reserve.mapper;

import com.reserve.dto.BookingDto;
import com.reserve.model.Booking;
import com.reserve.model.BookingEsEntity;

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

    public static BookingEsEntity mapToBookingEsEntity(BookingEsEntity esEntity, Booking booking) {
        esEntity.setId(booking.getBookingId());
        esEntity.setBookingDate(booking.getBookingDate());
        esEntity.setFlightNumber(booking.getFlightNumber());
        esEntity.setSeatCount(booking.getSeatCount());
        esEntity.setPassengerDetails(booking.getPassengerDetails());
        esEntity.setFinalBookingPriceInEuro(booking.getFinalBookingPriceInEuro());
        return esEntity;
    }
}