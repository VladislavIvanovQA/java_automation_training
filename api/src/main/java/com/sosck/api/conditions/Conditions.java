package com.sosck.api.conditions;

import io.qameta.allure.Step;
import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {

    @Step
    public static StatusCodeCondition statusCode(int statusCode){
        return new StatusCodeCondition(statusCode);
    }

    @Step
    public static BodyJsonCondition bodyField(String jsonPath, Matcher matcher){
        return new BodyJsonCondition(jsonPath, matcher);
    }

    @Step
    public static BodyHtmlCondition bodyField (Matcher matcher){
        return new BodyHtmlCondition(matcher);
    }
}
