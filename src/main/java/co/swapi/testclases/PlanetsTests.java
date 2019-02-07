package co.swapi.testclases;


import co.swapi.model.Planet;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class PlanetsTests {

    private static final String url = "http://swapi.co/api/planets";
    private static Response response;
    private static JSONObject responseInJSON;


    @BeforeClass()
    public static void callPlanetsApi() {
        URI uri = URI.create(url);
        response = given().when().get(uri);
        //response.prettyPrint();
    }


    @Test(priority = 0)
    public static void validateResponseAndStatusCode() {
        int expectedResponseCode = 200;
        Assert.assertEquals(expectedResponseCode, response.getStatusCode(), "Valdiating reponse code");
        boolean validJson = true;
        try {
            responseInJSON = new JSONObject(response.asString());

        } catch (Exception e) {
            validJson = false;
        }
        Assert.assertTrue(validJson);
    }

    @Test(priority = 1)
    public static void validateDataEachPlanetInfo() {
        responseInJSON = new JSONObject(response.asString());
        JSONArray resultsArray = responseInJSON.getJSONArray("results");
        List<Planet> planets = new ArrayList<>();
        for (int i = 0; i < resultsArray.length(); i++) {

            JSONObject planet = resultsArray.getJSONObject(i);

            Assert.assertNotNull(planet.get("name"));


            //Include rest of all additional validations here
            //Also we can create List of Planet Objects and store them in list and perform all the validations as required
        }
    }

    @Test(priority = 2)
    public static void validateCountOfPlanets() {
        responseInJSON = new JSONObject(response.asString());
        Assert.assertEquals(61,responseInJSON.get("count"));
    }

    @Test(priority = 2)
    public static void validatecurrentAndNext   () {
        responseInJSON = new JSONObject(response.asString());
        Assert.assertEquals("https://swapi.co/api/planets/?page=2",responseInJSON.get("next"));
        Assert.assertNotNull(responseInJSON.get("previous"));
    }

    @Test(priority = 2)
    public static void validateResultsCount() {
        responseInJSON = new JSONObject(response.asString());
        JSONArray resultsArray = responseInJSON.getJSONArray("results");
        Assert.assertEquals(10,resultsArray.length());
    }



}
