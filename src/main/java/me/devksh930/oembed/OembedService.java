package me.devksh930.oembed;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OembedService {

    @Value("${oembed.instagram.accessToken}")
    private String accessToken;


    public String getOembedResource(String url) {

        return url;
    }
}
