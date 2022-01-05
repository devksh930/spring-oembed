package me.devksh930.oembed.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EndpointsDto {

    private List<String> schemes;

    private String url;

    private boolean discovery;
}
