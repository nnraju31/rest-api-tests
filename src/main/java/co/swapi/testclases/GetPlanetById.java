package co.swapi.testclases;

import co.swapi.model.Planet;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class GetPlanetById {

    private  final String url = "http://swapi.co/api/planets";
    private  Response response;
    private URI uri;
    private JSONObject responseInJSON;


    @Test(priority = 0)
    public  void allValidations() throws IOException {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {

            uri = URI.create(url + ("/" + (random.nextInt(62) + 1)));
            response = given().when().get(uri);
            response.then().statusCode(200);
        }
    }

    @Test(priority = 1)
    public  void dataTest() throws IOException {
        uri = URI.create(url + ("/" + "1" ));
        response = given().when().get(uri);
        String planet = response.asString();

        ObjectMapper objectMapper = new ObjectMapper();
        Planet planet1 = objectMapper.readValue(planet, Planet.class);

        Assert.assertEquals(planet1.getName(),"Tatooine");

        // Can add validations as required for all the json elements

        uri = URI.create(url + ("/" + "62" ));
        response = given().when().get(uri);
        //response.prettyPrint();

        //Can add validation for response
    }

    @Test(priority = 2)
    public  void negativeTest() throws IOException {
        uri = URI.create(url + ("/" + "-1" ));
        response = given().when().get(uri);
        response.then().statusCode(404);
    }

    @Test(priority = 2)
    public  void floatTest() throws IOException {
        uri = URI.create(url + ("/" + "1.0" ));
        response = given().when().get(uri);
        response.then().statusCode(404);
    }

    @Test(priority = 2)
    public  void boundaryTest() throws IOException {
        uri = URI.create(url + ("/" + "62" ));
        response = given().when().get(uri);
        response.then().statusCode(404);
    }

    @Test(priority = 2)
    public  void StringTest() throws IOException {

        uri = URI.create(url + ("/" + "ADS" ));
        response = given().when().get(uri);
        response.then().statusCode(404);
    }



}
