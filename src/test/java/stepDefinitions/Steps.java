package stepDefinitions;

import java.util.List;
import java.util.Map;

import gherkin.deps.com.google.gson.JsonArray;
import gherkin.deps.com.google.gson.JsonObject;
import io.restassured.response.ResponseBody;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Steps {

    private static final String BASE_URL = "https://dog.ceo/api/breeds";
    private static final String PetBASE_URL = "https://petstore.swagger.io/v2/pet";
    public String CreadtedId = "";

    private static String token;
    private static Response response;
    private static String jsonString;

    //Dogs ----------------https://dog.ceo/dog-api/------------------------------------------------
    @Given("A Random Breed")
    public void RandomBreedCheck() {
        RestAssured.baseURI = BASE_URL + "/image/random";
        RequestSpecification request = RestAssured.given();
        response = request.get();

        jsonString = response.asString();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Get list of Random Breed")
    public void getAllList() {
        RestAssured.baseURI = BASE_URL + "/list/all";
        RequestSpecification request = RestAssured.given();
        response = request.get();

        jsonString = response.asString();
        Assert.assertEquals(200, response.getStatusCode());
        System.out.println("List All Random Breed Response Body is: " + response.asString());

    }

    @Then("Check if Bulldog is there")
    public void bulldogCheck() {

        ResponseBody body = response.getBody();

        Assert.assertEquals(200, response.getStatusCode());
        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        String Bulldog = jsonPathEvaluator.get("message").toString();

        Assert.assertTrue(Bulldog.contains("bulldog"));
        System.out.println("Bulldog was found on the response:: " + Bulldog);
    }

    @Then("Retrieve all sub-breeds")
    public void SubBreeds() {
        RestAssured.baseURI = "https://dog.ceo/api/breed/bulldog/list";
        RequestSpecification request = RestAssured.given();

        response = request.get();
        jsonString = response.asString();
        System.out.println("Retrieve all sub-breeds Response Body is: " + response.asString());
    }

    //Pets -------------------https://petstore.swagger.io----------------------------------------------
    @Given("all available pets Confirm Doggie with Category 12 is in the response")
    public void AvailableStatus() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet/findByStatus?status=available";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();

        // To check for sub string presence get the Response body as a String.
        // Do a String.contains
        String bodyAsString = body.asString();
        System.out.println(" pets response:: " + bodyAsString);

        Assert.assertEquals("Response body contains " /*Expected value*/, bodyAsString.contains("doggie") /*Actual Value*/, true);
        Assert.assertEquals("Response body contains " /*Expected value*/, bodyAsString.contains("300") /*Actual Value*/, true);

    }


    @When("I add a new pet")
    public void addPetInList() {
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Accept", "application/json")
                .header("Content-Type", "application/json");

        response = request.body("{\n" +
                "  \"id\": 0,\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"string\"\n" +
                "  },\n" +
                "  \"name\": \"doggie\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \"available\"\n" +
                "}").post(PetBASE_URL);
    }

    @Then("The pet is added and ID retrieved")
    public void petIsAdded() {
        Assert.assertEquals(200, response.getStatusCode());
        ResponseBody body = response.getBody();

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        CreadtedId = jsonPathEvaluator.get("id").toString();

        System.out.println("Response ID is: " +  jsonPathEvaluator.get("id"));
    }

    @Then("Retrieve info of created pet using id")
    public void getPetWithId() {

        RestAssured.baseURI = PetBASE_URL + "/" + CreadtedId;
        RequestSpecification request = RestAssured.given();
        response = request.get();

        ResponseBody body = response.getBody();

        // into the string representation.
        System.out.println("Response Body is: " + body.asString());
    }
}