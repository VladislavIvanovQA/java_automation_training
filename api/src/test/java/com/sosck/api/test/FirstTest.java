package com.sosck.api.test;

import com.sosck.api.request.register.RegisterUser;
import com.sosck.api.response.customers.Customer;
import com.sosck.api.response.customers.CustomerItem;
import com.sosck.api.utils.RandomString;
import io.restassured.RestAssured;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest {
    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://localhost:80";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void register(){
        registerUser(getUser())
                .statusCode(200)
                .body("id", not(isEmptyOrNullString()))
                .extract().jsonPath().get("id");
    }

    @Test
    public void registerNotValid(){
        RegisterUser user = getUser();
        registerUser(user)
                .statusCode(200)
                .body("id", not(isEmptyOrNullString()))
                .extract().jsonPath().get("id");

        registerUser(user)
                .statusCode(500);
    }

    @Test
    public void registerAndViewUser(){
        String id = registerUser(getUser())
                .statusCode(200)
                .body("id", not(isEmptyOrNullString()))
                .extract().jsonPath().get("id");

        Customer customers = getCustomers();

        assert customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) != null;
    }

    @Test
    public void registerCheckDeleteUser(){
        String id = registerUser(getUser())
                .statusCode(200)
                .body("id", not(isEmptyOrNullString()))
                .extract().jsonPath().get("id");

        Customer customers = getCustomers();

        assert customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) != null;

        base().delete("/customers/" + id)
                .then()
                .statusCode(200)
                .body(containsString("\"{\\\"status\\\":true}\\n\""));

        Customer customer = getCustomers();

        assert customer.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) == null;
    }

    @Test
    public void registerCheckUserGetCustomerWithId(){
        ExtractableResponse<Response> response = registerUser(getUser())
                .statusCode(200)
                .body("id", not(isEmptyOrNullString()))
                .extract();

        String id = response.jsonPath().get("id");
        Map<String, String> cookies = response.cookies();


        Customer customers = getCustomers();

        assert customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) != null;

        CustomerItem item = base().cookies(cookies).get(String.format("/customers/%s", id))
                .then()
                .statusCode(200)
                .extract().as(CustomerItem.class);
        CustomerItem customerItem1 = customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null);
        assert customerItem1 != null;

        assert customerItem1.equals(item);
    }

    private Customer getCustomers() {
        return base().get("/customers")
                .then()
                .statusCode(200)
                .extract().as(Customer.class);
    }

    private RegisterUser getUser() {
        String login = new RandomString(21).nextString();
        String email = new RandomString(21).nextString() + "@mail.ru";
        return new RegisterUser()
                .username(login)
                .password("1234567890")
                .email(email);
    }

    private ValidatableResponse registerUser(RegisterUser user){
        return base().body(user)
            .post("/register")
            .then();
    }

    private RequestSpecification base(){
        return given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON);
//            .filters(new ResponseLoggingFilter(), new ResponseLoggingFilter());
    }
}
