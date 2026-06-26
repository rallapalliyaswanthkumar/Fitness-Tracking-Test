package com.wipro.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ai")
public class AIController {

    @PostMapping("/ask")
    public String askAI(@RequestBody String question) {

        String url = "http://localhost:8000/ask";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_PLAIN);

        HttpEntity<String> request = new HttpEntity<>(question, headers);

        String response = restTemplate.postForObject(url, request, String.class);

        return response;
    }
}