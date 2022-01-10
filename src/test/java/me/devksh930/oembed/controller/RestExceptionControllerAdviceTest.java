package me.devksh930.oembed.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = RestExceptionControllerAdvice.class)
class RestExceptionControllerAdviceTest {

    @Test
    @DisplayName("HttpClientError처리")
    void handleHttpClientErrorException() {
        assertThrows(HttpClientErrorException.class, () -> {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        });
    }
}