package com.example.demo.controllers;

import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.RecognitionResponseBody;
import com.google.common.util.concurrent.RateLimiter;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class ImagesControllerTest {

    @Test
    public void testClassifyImageRateLimiting() {

//        RateLimiter rateLimiter = RateLimiter.create(10);
//        // Mock the rate limiter to always acquire a permit
//        when(rateLimiter.tryAcquire()).thenReturn(true);

        // Create an instance of your ImageController
        ImagesController imagesController = new ImagesController();

        // Create a sample RecognitionRequestBody
        RecognitionRequestBody requestBody = new RecognitionRequestBody();
        // ...

        // Call the classifyImage method multiple times within a short interval
        for (int i = 0; i < 15; i++) {
            ResponseEntity<RecognitionResponseBody> responseEntity = imagesController.classifyImage(requestBody, false);

            // Assert that the response status code is 200 OK for the first 10 requests
            if (i < 10) {
                assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
            }
            // Assert that the response status code is 429 TOO MANY REQUESTS for the subsequent requests
            else {
                assertEquals(HttpStatus.TOO_MANY_REQUESTS, responseEntity.getStatusCode());
            }
        }
    }
}
