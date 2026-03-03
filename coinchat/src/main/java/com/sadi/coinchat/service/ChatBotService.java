package com.sadi.coinchat.service;

import com.sadi.coinchat.io.ApiResponse;

public interface ChatBotService {

    ApiResponse getCoinDetails(String prompt) throws Exception;

    String sendChat(String prompt);
}
