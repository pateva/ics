package com.example.demo.controllers;

import com.example.demo.models.Greeting;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicLong;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final String template = "Hello, %s!";
    private static final AtomicLong counter = new AtomicLong();
    private final String API_URL = "https://api.ximilar.com/classify/v2/classify";
    private final String API_KEY = System.getenv("API_KEY_XIMILAR");


    @GetMapping("/")
    public String empty() {
        return new String("Welcome to localhost:8080");
    }

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/images")
    public String postImages(@RequestParam(value = "URL") String imageUrl) throws IOException {

        String json = "{ \"task_id\": " + counter.incrementAndGet() + ", \"data\": { \"image_url\": \"" + imageUrl + "\" } }";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(API_URL + "?url=" + imageUrl);
        httpGet.setHeader("Authorization", "Token " + API_KEY);

//        CloseableHttpResponse httpResponse = null;
//        try {
//            httpResponse = httpClient.execute(httpGet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//            HttpEntity responseEntity = (HttpEntity) httpResponse.getEntity();
//            String responseString = EntityUtils.toString((org.apache.hc.core5.http.HttpEntity) responseEntity);
//
//            JSONObject jsonResponse = new JSONObject(responseString);
//            return jsonResponse.toString();
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } finally {
//            httpResponse.close();
//        }

        return "not ready yet";
    }

}



