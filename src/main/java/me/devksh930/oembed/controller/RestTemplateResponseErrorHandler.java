package me.devksh930.oembed.controller;

import lombok.extern.slf4j.Slf4j;
import me.devksh930.oembed.exception.ClientException;
import me.devksh930.oembed.exception.ClientForbiddenException;
import me.devksh930.oembed.exception.NotFoundException;
import me.devksh930.oembed.exception.ServerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@Component
@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String errorMessage = toString(response.getBody());
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {

            log.debug("=====서버에러 ======{}", response.getBody());
            throw new ServerException(errorMessage);

        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            log.debug("=====클라이언트에러 ======{}", errorMessage);

            if (response.getStatusCode() == HttpStatus.FORBIDDEN) {
                log.debug("=====Forbidden ======{}", errorMessage);
                throw new ClientForbiddenException("권한 문제로 지원하지 않는 URL입니다");
            }

            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.debug("=====NotFoundError ======{}", errorMessage);
                throw new NotFoundException(errorMessage);
            }

            throw new ClientException(errorMessage);
        }
    }

    public String toString(InputStream inputStream) {
        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}

