package com.sosck.api.service;

import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;

public class ApiService {
    public RequestSpecification base(){
        return given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
            .filters(new ResponseLoggingFilter(), new ResponseLoggingFilter());
    }
}
