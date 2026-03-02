package com.sadi.coinchat.service;

import com.sadi.coinchat.io.ApiResponse;

public interface ChatBotService {

    ApiResponse getCoinDetails(String prompt);

    String sendChat(String prompt);
}
