package com.reserve.dto;

import com.reserve.model.Passenger;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(
        name = "Booking",
        description = "Schema to hold Bookings information"
)
public class BookingDto {


    @NotEmpty(message = "Flight Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Flight Number must be 10 digits")
    @Schema(
            description = "Flight Number of Carrier", example = "3454433243"
    )
    private String flightNumber;


    @NotEmpty(message = "Booking Price In Euros of the flight can not be empty or null")
    @Schema(
            description = "Booking Price In Euros of the flight ", example = "737"
    )
    private BigDecimal finalBookingPriceInEuro;

    @NotEmpty(message = "flight booking Date Time can not be empty or null")
    @Schema(
            description = "flight booking Date Time ", example = "2023-15-01:18.22.00"
    )
    private LocalDateTime bookingDate;

    private int seatCount;
    private List<Passenger> passengerDetails;
}