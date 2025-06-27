package com.vspiewak.hexagonal.acceptance;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

import org.springframework.boot.test.web.server.LocalServerPort;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StepDefinitions {

    private Response response;

    @LocalServerPort private Integer port;

    @When("the client make a GET on {word}")
    public void the_client_make_a_get_request_on(String endpoint) {
        RestAssured.port = port;
        response = when().get(endpoint);
    }

    @Then("the client receives a status code of {int}")
    public void the_client_receives_a_status_code_of(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("the client receives {word} with value {string}")
    public void the_client_receives_a_body_with_path_and_value(String path, String value) {
        response.then().body(path, equalTo(value));
    }
}
