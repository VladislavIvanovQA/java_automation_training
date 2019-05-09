package com.sosck.api.test;

import com.sosck.api.ProjectConfig;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;

import static java.lang.System.*;
import static java.util.Collections.singletonMap;

public class BaseTest {

    private static final String ENV_VARIABLE = "env";

    @BeforeAll
    public static void setup(){
        ProjectConfig config = ConfigFactory.create(ProjectConfig.class, singletonMap(ENV_VARIABLE, getProperty(ENV_VARIABLE)));

        RestAssured.baseURI = config.host();
        RestAssured.defaultParser = Parser.JSON;
    }
}
