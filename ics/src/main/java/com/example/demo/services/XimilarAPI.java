package com.example.demo.services;

import com.example.demo.controllers.dto.RecognitionRequestBody;
import com.example.demo.controllers.dto.RecognitionResponseBody;
import com.example.demo.exceptions.InvalidRequestBodyException;
import com.example.demo.exceptions.UserNotAuthenticatedException;
import com.example.demo.exceptions.XimilarException;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


public class XimilarAPI {
    private static final String API_URL_CLASSIFY = "https://api.ximilar.com/photo/tags/v2/tags";
    private static final String API_KEY = System.getenv("API_KEY_XIMILAR");
    private static final RestTemplate restTemplate = new RestTemplate();

    public static RecognitionResponseBody postApiTagRequestXimilar(RecognitionRequestBody body) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Token " + API_KEY);
        HttpEntity<RecognitionRequestBody> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<RecognitionResponseBody> responseEntity;
        try {
            responseEntity = restTemplate.exchange(
                    API_URL_CLASSIFY
                    , HttpMethod.POST
                    , requestEntity
                    , RecognitionResponseBody.class);

            return responseEntity.getBody();
        } catch (HttpClientErrorException.BadRequest e) {

            throw new InvalidRequestBodyException();
        } catch (HttpClientErrorException.Unauthorized e) {

            throw new UserNotAuthenticatedException();
        } catch (Exception e) {

            throw new XimilarException("Something went wrong!");
        }
    }
}


