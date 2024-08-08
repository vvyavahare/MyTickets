package com.flights.api.steps;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinitions {
    private static final String BASE_URL = "http://localhost:8081/flight";

    private static Response response;

    @Given("^user requests flight detail by Id")
    public void when_user_requests_flight_detail_by_Id() {

        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");
        response = request
                .get("/12");

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
                        "    \"departureTime\": \"2024-01-12T19:34:14\"\n" +
                        "}")
                .post();

        String jsonString = response.asString();

    }


    @Then("Correct Flight detail is added")
    public void flight_detail_is_added() {
        Assert.assertEquals(201, response.getStatusCode());
        response.body().asString().equals("{\"flightNumber\":\"8205675646\",\"flight\":\"German Airways\",\"origin\":\"Schiphol\",\"destination\":\"Hannover\",\"speedSeries\":null,\"capacity\":700,\"departureTime\":\"2024-08-08T00:00:00\"}");
    }

}
