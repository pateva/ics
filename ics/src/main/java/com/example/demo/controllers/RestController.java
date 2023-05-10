package com.example.demo.controllers;

import com.example.demo.models.Greeting;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();
    private final String API_URL_CLASSIFY = "https://api.ximilar.com/classify/v2/classify";
    private final String API_KEY = System.getenv("API_KEY_XIMILAR");

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String empty() {
        return new String("Welcome!!!:)");
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/images")
    public ResponseEntity<String> classifyImage(@RequestParam("url") String imageUrl) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Token " + API_KEY);

        JSONObject body = new JSONObject();
        body.put("url", imageUrl);

        HttpEntity<String> requestEntity = new HttpEntity<>(body.toString(), headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(API_URL_CLASSIFY, HttpMethod.POST, requestEntity, String.class);

        return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
    }

}



