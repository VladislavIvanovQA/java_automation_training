package com.sosck.api.conditions;

import lombok.experimental.UtilityClass;
import org.hamcrest.Matcher;

@UtilityClass
public class Conditions {

    public static StatusCodeCondition statusCode(int statusCode){
        return new StatusCodeCondition(statusCode);
    }

    public static BodyJsonCondition bodyField(String jsonPath, Matcher matcher){
        return new BodyJsonCondition(jsonPath, matcher);
    }

    public static BodyHtmlCondition bodyField (Matcher matcher){
        return new BodyHtmlCondition(matcher);
    }
}
