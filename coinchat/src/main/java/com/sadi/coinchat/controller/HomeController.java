package com.sadi.coinchat.controller;

import com.sadi.coinchat.io.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping()
    public ResponseEntity<ApiResponse>Home(){

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Welcome to Coin Chatbot");
        return new  ResponseEntity<>(apiResponse,HttpStatus.OK);


    }

}
