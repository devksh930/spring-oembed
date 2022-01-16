package me.devksh930.oembed.controller;

import me.devksh930.oembed.dto.ErrorDto;
import me.devksh930.oembed.exception.ClientException;
import me.devksh930.oembed.exception.ClientForbiddenException;
import me.devksh930.oembed.exception.ProviderNotMatchingException;
import me.devksh930.oembed.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = HttpClientErrorException.class)
    ResponseEntity<ErrorDto> handleHttpClientErrorException(HttpClientErrorException e) {
        return new ResponseEntity<>(ErrorDto.exceptionToErrorDto(e.getMessage(), e.getStatusCode().toString()), e.getStatusCode());
    }

    @ExceptionHandler(value = ProviderNotMatchingException.class)
    ResponseEntity<ErrorDto> handleNotMatchingException(ProviderNotMatchingException e) {
        return new ResponseEntity<>(ErrorDto.exceptionToErrorDto(e.getMessage(), HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ClientForbiddenException.class)
    ResponseEntity<ErrorDto> handleClientForbiddenException(ClientForbiddenException e) {
        return new ResponseEntity<>(ErrorDto.exceptionToErrorDto(e.getMessage(), HttpStatus.FORBIDDEN.toString()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = ClientException.class)
    ResponseEntity<ErrorDto> handleClientException(ClientException e) {
        return new ResponseEntity<>(ErrorDto.exceptionToErrorDto(e.getMessage(), HttpStatus.BAD_REQUEST.toString()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ServerException.class)
    ResponseEntity<ErrorDto> handleServerException(ServerException e) {
        return new ResponseEntity<>(ErrorDto.exceptionToErrorDto(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
