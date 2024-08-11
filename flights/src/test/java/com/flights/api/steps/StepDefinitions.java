package com.flights.api.steps;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flights.api.dto.FlightDto;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;

public class StepDefinitions {
    private static final String BASE_URL = "http://localhost:8081/flight";

    private static Response response;
    private static final ObjectMapper mapper = new ObjectMapper();

    static {


        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @Given("^User requests flight detail by Id")
    public void when_user_requests_flight_detail_by_Id() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.get("/12");

        String jsonString = response.asString();

    }

    @Then("Correct Flight detail is returned")
    public void flight_detail_is_returned() {
        Assert.assertEquals(200, response.getStatusCode());
        response.body().asString().equals("{\"flightNumber\":\"8205675646\",\"flight\":\"German Airways\",\"origin\":\"Schiphol\",\"destination\":\"Hannover\",\"speedSeries\":null,\"capacity\":700,\"departureTime\":\"2024-08-08T00:00:00\"}");
    }

    @Given("^Admin adds new flight detail")
    public void adminAddsNewFlight() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request.body("{\n" +
                        "    \"flight\": \"KLM\",\n" +
                        "    \"origin\": \"Schiphol\",\n" +
                        "    \"destination\": \"JFK Newyork\",\n" +
                        "    \"speedSeries\": [\n" +
                        "        3701,\n" +
                        "        500\n" +
                        "    ],\n" +
                        "    \"capacity\": 700,\n" +
                        "    \"departureTime\": \"2024-01-12T02:34:14\"\n" +
                        "}")
                .post();

        String jsonString = response.asString();

    }


    @Then("Correct Flight detail is added")
    public void flight_detail_is_added() throws JsonProcessingException {
        Assert.assertEquals(201, response.getStatusCode());
        FlightDto dto = mapper.readValue(response.body().asString(), FlightDto.class);
        Assert.assertEquals(dto.getOrigin(), "Schiphol");
        Assert.assertEquals(dto.getFlight(), "KLM");
        Assert.assertEquals(dto.getDestination(), "JFK Newyork");
    }


    @Given("^User tries get all flights by origin place")
    public void userGetsFlightsByOrigin() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        request.queryParam("origin", "Schiphol");
        response = request.get();

        String jsonString = response.asString();

    }

    @Then("Correct Flight details are returned")
    public void flight_detail_are_returned() throws JsonProcessingException {
        Assert.assertEquals(200, response.getStatusCode());

        List<FlightDto> dtos = mapper.readValue(response.body().asString(), new TypeReference<List<FlightDto>>() {
        });

        FlightDto dto = dtos.get(0);
        Assert.assertEquals(dto.getOrigin(), "Schiphol");
    }

}
