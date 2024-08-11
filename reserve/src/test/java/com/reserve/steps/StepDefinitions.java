package com.reserve.steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.reserve.dto.BookingDto;
import com.reserve.dto.FlightDto;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.util.List;

public class StepDefinitions {
    private static final String BASE_URL = "http://localhost:8082/reserve";

    private static Response response;
    private static final ObjectMapper mapper = new ObjectMapper();

    private static int capacity = 0;

    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    @Given("^User books a flight")
    public void userBooksAFlight() throws JsonProcessingException {

        //--------- First capture the capacity of flight in test before booking ---------------
        RestAssured.baseURI = "http://localhost:8082";
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.queryParam("origin", "Schiphol");
        response = request.get("/all-flights");


        Assert.assertEquals(200, response.getStatusCode());
        List<FlightDto> dtos = mapper.readValue(response.body().asString(), new TypeReference<List<FlightDto>>() {
        });

        dtos.stream().filter(dto -> dto.getFlightNumber().equals("2305675644")).findFirst().ifPresent(c -> {
            capacity = c.getCapacity();
        });

        //--------- Now do actual flight booking ---------------

        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                        "    \"flightNumber\": \"2305675644\",\n" +
                        "    \"bookingPersonId\": \"456456\",\n" +
                        "    \"seatCount\": \"2\",\n" +
                        "    \"bookingDate\": \"2024-01-12T19:34:14\",\n" +
                        "    \"finalBookingPriceInEuro\": 230.34,\n" +
                        "    \"passengerDetails\": [\n" +
                        "        {\n" +
                        "            \"passengerId\": 4564562\n" +
                        "        },\n" +
                        "        {\n" +
                        "            \"passengerId\": 1\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}")
                .post();

        String jsonString = response.asString();

    }


    @Then("Correct booking detail is returned")
    public void flight_booking_details_returned() throws JsonProcessingException {
        Assert.assertEquals(201, response.getStatusCode());
        BookingDto dto = mapper.readValue(response.body().asString(), BookingDto.class);
//        Assert.assertEquals(dto.getFinalBookingPriceInEuro(), "230.34");
//        Assert.assertEquals(dto.getBookingDate(), "2024-01-12T19:34:14");
        Assert.assertEquals(dto.getFlightNumber(), "2305675644");
        Assert.assertEquals(dto.getSeatCount(), 2);

    }

    @And("flight capacity is reduced by seat count")
    public void bookingReducedFlightCapacity() throws JsonProcessingException, InterruptedException {

        Thread.sleep(5000);
        RestAssured.baseURI = "http://localhost:8082";
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.queryParam("origin", "Schiphol");
        response = request.get("/all-flights");


        Assert.assertEquals(200, response.getStatusCode());
        List<FlightDto> dtos = mapper.readValue(response.body().asString(), new TypeReference<List<FlightDto>>() {
        });
        Assert.assertFalse(dtos.isEmpty());
        Assert.assertTrue(dtos.stream().filter(dto -> dto.getFlightNumber().equals("2305675644")).findFirst().stream().findAny().isPresent());
        Assert.assertEquals(capacity - 2, dtos.stream().filter(dto -> dto.getFlightNumber().equals("2305675644")).findFirst().get().getCapacity());

    }


}
