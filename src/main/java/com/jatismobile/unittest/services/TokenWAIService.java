package com.jatismobile.unittest.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenWAIService {
    @Value("${app.client.base-url}")
    String clientBaseURL;

    RestTemplate restTemplate;
 
    public TokenWAIService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Object> redeem() {
        HttpHeaders httpHeaderRequest = new HttpHeaders();
        httpHeaderRequest.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> httpEntity = new HttpEntity<>(clientBaseURL, httpHeaderRequest);

        ResponseEntity<Object> responseEntity = restTemplate.exchange(clientBaseURL, HttpMethod.POST, httpEntity,
                Object.class);

        if (responseEntity.getStatusCode().isError()) {
            throw new RuntimeException(responseEntity.getBody().toString());
        }

        return responseEntity;
    }
}
