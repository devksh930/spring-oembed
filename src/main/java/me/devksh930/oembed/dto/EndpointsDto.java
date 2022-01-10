package me.devksh930.oembed.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class EndpointsDto {

    private List<String> schemes = new ArrayList<>();

    private String url;

    private boolean discovery;

    public String getUrl() {
        this.url = url.replaceAll("\\{format}", "json");
        return url;
    }
}
