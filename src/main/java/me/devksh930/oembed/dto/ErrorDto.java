package me.devksh930.oembed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorDto {
    @JsonProperty("error_message")
    private String errorMessage;
    @JsonProperty("status_code")
    private String statusCode;
}
