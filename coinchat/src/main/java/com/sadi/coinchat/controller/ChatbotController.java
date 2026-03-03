package com.sadi.coinchat.controller;

import com.sadi.coinchat.dto.PromptBody;
import com.sadi.coinchat.io.ApiResponse;
import com.sadi.coinchat.service.ChatBotService;
import com.sadi.coinchat.service.implementation.ChatBotServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ai/")
@RequiredArgsConstructor
public class ChatbotController {


    private  final ChatBotService chatBotService;


    @PostMapping("chat")
    public ResponseEntity<ApiResponse> getCoinDetails(@RequestBody PromptBody promptBody) throws Exception {

        chatBotService.getCoinDetails(promptBody.getPrompt());

        ApiResponse response = new ApiResponse();
        response.setMessage(promptBody.getPrompt());
        return new ResponseEntity<>(response, HttpStatus.OK);


    }

}


