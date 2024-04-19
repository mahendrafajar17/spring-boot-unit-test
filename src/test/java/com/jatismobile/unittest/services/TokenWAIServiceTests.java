package com.jatismobile.unittest.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@ExtendWith(MockitoExtension.class)
public class TokenWAIServiceTests {

    String clientBaseURL = "http://localhost:9000";

    @Mock
    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void redeem2xx() {
        ResponseEntity<Object> responseEntityMock = ResponseEntity.ok().body("TEST");
        when(restTemplate.exchange(eq(clientBaseURL), eq(HttpMethod.POST), any(), eq(Object.class)))
                .thenReturn(responseEntityMock);

        TokenWAIService tokenWAIService = new TokenWAIService(restTemplate);
        tokenWAIService.clientBaseURL = clientBaseURL;
        ResponseEntity<Object> responseEntity = tokenWAIService.redeem();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void redeemError() {
        ResponseEntity<Object> responseEntityMock = ResponseEntity.badRequest().body("TEST");
        when(restTemplate.exchange(eq(clientBaseURL), eq(HttpMethod.POST), any(), eq(Object.class)))
                .thenReturn(responseEntityMock);

        TokenWAIService tokenWAIService = new TokenWAIService(restTemplate);
        tokenWAIService.clientBaseURL = clientBaseURL;
        assertThrows(RuntimeException.class, () -> {
            tokenWAIService.redeem();
        });

    }
}
