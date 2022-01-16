package me.devksh930.oembed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorDto {
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("status_code")
    private String statusCode;


    public static ErrorDto exceptionToErrorDto(String errorMessage, String statusCode) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setErrorMessage(errorMessage);
        errorDto.setStatusCode(statusCode);
        return errorDto;
    }
}
