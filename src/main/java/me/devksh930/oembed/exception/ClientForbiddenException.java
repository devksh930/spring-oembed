package me.devksh930.oembed.exception;

public class ClientForbiddenException extends RuntimeException {
    public ClientForbiddenException(String messge) {
        super(messge);
    }
}
