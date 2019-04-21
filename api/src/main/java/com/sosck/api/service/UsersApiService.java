package com.sosck.api.service;

import com.sosck.api.assertions.AssertableResponse;
import com.sosck.api.request.register.RegisterUser;
import io.restassured.http.Cookie;
import io.restassured.http.Cookies;

import java.util.Map;

public class UsersApiService extends ApiService {

    public AssertableResponse postRegisterUser(RegisterUser user){
        return new AssertableResponse(base().body(user)
                .post("/register"));
    }

    public AssertableResponse getCustomers(){
        return new AssertableResponse(base()
                .get("/customers"));
    }

    public AssertableResponse getCustomersWithId(String id, Map<String, String> cookie){
        return new AssertableResponse(base()
                .cookies(cookie)
                .get("/customers/" + id));
    }

    public AssertableResponse deleteCustomersWithId(String id){
        return new AssertableResponse(base()
                .delete("/customers/" + id));
    }
}
