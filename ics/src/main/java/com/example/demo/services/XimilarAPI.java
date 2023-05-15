package com.example.demo.services;

import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.RecognitionResponseBody;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class XimilarAPI {
    private static final String API_URL_CLASSIFY = "https://api.ximilar.com/photo/tags/v2/tags";
    private static final String API_KEY = System.getenv("API_KEY_XIMILAR");
    private static RestTemplate restTemplate = new RestTemplate();

    public static ResponseEntity<RecognitionResponseBody> postApiTagRequestXimilar(RecognitionRequestBody body) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + API_KEY);
        HttpEntity<RecognitionRequestBody> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<RecognitionResponseBody> responseEntity;
        System.out.println();
        try {
            responseEntity = restTemplate.exchange(
                    API_URL_CLASSIFY
                    , HttpMethod.POST
                    , requestEntity
                    , RecognitionResponseBody.class);

            return new ResponseEntity<>(responseEntity.getBody(), HttpStatus.OK);
        } catch (HttpClientErrorException.BadRequest e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } catch (HttpClientErrorException.Unauthorized e) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
