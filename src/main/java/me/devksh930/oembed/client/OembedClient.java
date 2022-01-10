package me.devksh930.oembed.client;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.exception.ClientForbiddenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OembedClient {


    private final RestTemplate restTemplate;


    @Value("${oembed.facebook.accessToken}")
    private String facebookAccessToken;
    @Value("${oembed.instagram.accessToken}")
    private String instagramAccessToken;

    private RequestEntity<Void> requestEntity(URI uri) {
        return RequestEntity
                .get(uri)
                .build();
    }

    private URI makeUri(String url, String apiUrl) {
        UriComponentsBuilder requestURLBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("url", url)
                .queryParam("format", "json");
        URI requestURL;

        if (apiUrl.contains("instagram") || apiUrl.contains("facebook")) {
            throw new ClientForbiddenException("권한 문제로 지원하지 않는 URL입니다");
//            requestURL = requestURLBuilder
//                    .queryParam("access_token", instagramAccessToken).encode().build().toUri();

        } else {
            requestURL = requestURLBuilder
                    .encode().build().toUri();
        }
        return requestURL;
    }

    public Map<String, Object> getOembedResource(String url, String apiUrl) {

        ResponseEntity<HashMap<String, Object>> exchange = restTemplate.exchange(requestEntity(makeUri(url, apiUrl)), new ParameterizedTypeReference<>() {
        });
        return exchange.getBody();
    }

}
