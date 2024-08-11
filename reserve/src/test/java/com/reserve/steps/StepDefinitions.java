package com.reserve.steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.reserve.dto.BookingDto;
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

    static {


        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }


    @Given("^User books a flight")
    public void adminAddsNewFlight() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                        "    \"flightNumber\": \"2305675644\",\n" +
                        "    \"bookingPersonId\": \"456456\",\n" +
                        "    \"seatCount\": \"10\",\n" +
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
    public void flight_detail_is_added() throws JsonProcessingException {
        Assert.assertEquals(201, response.getStatusCode());
        BookingDto dto = mapper.readValue(response.body().asString(), BookingDto.class);
//        Assert.assertEquals(dto.getFinalBookingPriceInEuro(), "230.34");
//        Assert.assertEquals(dto.getBookingDate(), "2024-01-12T19:34:14");
        Assert.assertEquals(dto.getFlightNumber(), "2305675644");
        Assert.assertEquals(dto.getSeatCount(), 10);

    }


}
