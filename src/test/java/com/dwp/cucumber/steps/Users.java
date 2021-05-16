package com.dwp.cucumber.steps;

import com.dwp.cucumber.pojo.User;
import com.dwp.cucumber.steps.serenity.UsersSteps;
import com.dwp.cucumber.utils.TestUtil;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Steps;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;

public class Users {
    @Steps
    UsersSteps userSteps;
    Response response;

    @When("^sending the request to \"([^\"]*)\"$")
    public void sending_the_request_to(String endPoint) {
        response = userSteps.getAllUsers(endPoint);
    }

    @Then("^verify the successful response$")
    public void verify_the_successful_response() {
        Assert.assertEquals(200,response.statusCode());
    }

    @Then("^verify the number of \"([^\"]*)\" return$")
    public void verify_the_number_of_return(String expectCount) {
        List<User> allUsers = TestUtil.getAllUsers(response.body().asString());
        Assert.assertEquals(Integer.parseInt(expectCount),allUsers.size());
    }

    @Then("^print users to console$")
    public void print_users_to_console() {

        List<User> allUsers = TestUtil.getAllUsers(response.body().asString());
        allUsers.stream()
                .filter(user -> TestUtil.getDistanceFromLondon(Double.parseDouble(user.getLatitude()), Double.parseDouble(user.getLongitude())) <= 50)
                .forEach(System.out::println);
    }

}
