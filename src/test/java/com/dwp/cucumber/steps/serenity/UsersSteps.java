package com.dwp.cucumber.steps.serenity;

import io.restassured.response.Response;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;

/*
    This is step function , where actual request will submit to the server and returns the response
 */

public class UsersSteps extends ScenarioSteps {
    private static final String BASE_URI="https://bpdts-test-app.herokuapp.com/";
    @Step("Get the details of end point")
    public Response getAllUsers(String endpoint){
        return  SerenityRest.get(BASE_URI + endpoint).andReturn();
    }
}
