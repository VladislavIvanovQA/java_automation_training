package com.sosck.api.test;

import com.sosck.api.request.register.RegisterUser;
import com.sosck.api.response.customers.Customer;
import com.sosck.api.response.customers.CustomerItem;
import com.sosck.api.service.UsersApiService;
import com.sosck.api.utils.RandomString;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.sosck.api.conditions.Conditions.bodyField;
import static com.sosck.api.conditions.Conditions.statusCode;
import static io.restassured.RestAssured.filters;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstTest {
    private final UsersApiService usersApiService = new UsersApiService();

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI = "http://localhost:80";
        RestAssured.defaultParser = Parser.JSON;
    }

    @Test
    public void register(){
//        given().body(getUser())
//                .when()
//                .post("/register")
//                .then()
//                .statusCode(200);
        usersApiService.postRegisterUser(getUser())
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyOrNullString())));
    }

    @Test
    public void registerNotValid(){
        RegisterUser user = getUser();

        usersApiService.postRegisterUser(user)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyOrNullString())));

        usersApiService.postRegisterUser(user)
                .shouldHave(statusCode(500));
    }

    @Test
    public void registerAndViewUser(){
        String id = usersApiService.postRegisterUser(getUser())
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyOrNullString())))
                .getJsonPathValue("id");

        Customer customers = usersApiService.getCustomers()
                .shouldHave(statusCode(200))
                .asPOJO(Customer.class);

        assert customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) != null;
    }

    @Test
    public void registerCheckDeleteUser(){
        String id = usersApiService.postRegisterUser(getUser())
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyOrNullString())))
                .getJsonPathValue("id");

        Customer customers = usersApiService.getCustomers()
                .shouldHave(statusCode(200))
                .asPOJO(Customer.class);

        assert customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) != null;

        usersApiService.deleteCustomersWithId(id)
                .shouldHave(statusCode(200))
                .shouldHave(bodyField(containsString("\"{\\\"status\\\":true}\\n\"")));


        Customer customer = usersApiService.getCustomers()
                .shouldHave(statusCode(200))
                .asPOJO(Customer.class);

        assert customer.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) == null;
    }

    @Test
    public void registerCheckUserGetCustomerWithId(){
        ExtractableResponse<Response> response = usersApiService.postRegisterUser(getUser())
                .shouldHave(statusCode(200))
                .shouldHave(bodyField("id", not(isEmptyOrNullString())))
                .execute();

        String id = response.jsonPath().getString("id");

        Customer customers = usersApiService.getCustomers()
                .shouldHave(statusCode(200))
                .asPOJO(Customer.class);

        assert customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null) != null;

        CustomerItem item = usersApiService.getCustomersWithId(id, response.cookies())
                .shouldHave(statusCode(200))
                .asPOJO(CustomerItem.class);

        CustomerItem customerItem1 = customers.get_embedded().getCustomer().stream()
                .filter(customerItem -> customerItem.getId().equals(id))
                .findFirst()
                .orElse(null);
        assert customerItem1 != null;

        assert customerItem1.equals(item);
    }

    private RegisterUser getUser() {
        String login = new RandomString(21).nextString();
        String email = new RandomString(21).nextString() + "@mail.ru";
        return new RegisterUser()
                .username(login)
                .password("1234567890")
                .email(email);
    }
}
