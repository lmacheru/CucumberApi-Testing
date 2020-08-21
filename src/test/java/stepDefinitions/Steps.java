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
    private static final String USER_ID = "9b5f49ab-eea9-45f4-9d66-bcf56a531b85";
    private static final String USERNAME = "TOOLSQA-Test";
    private static final String PASSWORD = "Test@@123";
    private static final String BASE_URL = "https://dog.ceo/api/breeds";
    private static final String PetBASE_URL = "https://petstore.swagger.io/v2/pet";
    public String CreadtedId = "";

    private static String token;
    private static Response response;
    private static String jsonString;
    private static String bookId;


    @Given("A Random Breed")
    public void RandomBreedCheck() {
        RestAssured.baseURI = BASE_URL + "/image/random";
        RequestSpecification request = RestAssured.given();
        response = request.get();

        jsonString = response.asString();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Get all list")
    public void getAllList() {
        RestAssured.baseURI = BASE_URL + "/list/all";
    }

    @Then("Check if Bulldog is there")
    public void bulldogCheck() {
        Assert.assertEquals(200, response.getStatusCode());
    }

    @When("Retrieve all sub-breeds")
    public void SubBreeds() {
        RestAssured.baseURI = BASE_URL + "/bulldog/list";
        Assert.assertEquals(200, response.getStatusCode());
    }


    @Given("Retrieve all available pets")
    public void AvailableStatus() {
        RestAssured.baseURI = PetBASE_URL + "/findByStatus?status=available";
        RequestSpecification request = RestAssured.given();
        response = request.get();

        jsonString = response.asString();
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Then("Confirm name and Category 12 is in the response")
    public void Confirming() {

        ResponseBody body = response.getBody();

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        JsonArray jsonArray = new JsonArray();


        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.get(i).getAsJsonObject();


            System.out.println(jsonObject.get("id").getAsString());

        }

        /*List<String> allBooks = jsonPathEvaluator.getList("..id");

        // Iterate over the list and print individual book item
        for (String book : allBooks) {
            System.out.println("Book: " + book);
        }*/
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