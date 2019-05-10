package com.sosck.api.assertions;

import com.sosck.api.conditions.Condition;
import io.qameta.allure.Step;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AssertableResponse {
    private final Response response;

    @Step
    public AssertableResponse shouldHave(Condition conditions){
        conditions.check(response);
        return this;
    }

    public String getJsonPathValue(String jsonPath){
        return response
                .then()
                .extract()
                .jsonPath()
                .get(jsonPath);
    }

    public String getHtmlPathValue(String htmlPath){
        return response
                .then()
                .extract()
                .htmlPath()
                .getString(htmlPath).replace("\"", "");
    }

    public <T> T asPOJO(Class<T> tClass){
        return response
                .then()
                .extract()
                .as(tClass);
    }

    public ExtractableResponse<Response> execute(){
        return response
                .then()
                .extract();
    }
}
