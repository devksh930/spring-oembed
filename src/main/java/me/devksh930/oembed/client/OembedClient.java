package me.devksh930.oembed.client;

import lombok.RequiredArgsConstructor;
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


    private RequestEntity<Void> requestEntity(URI uri) {
        return RequestEntity
                .get(uri)
                .build();
    }

    public Map<String, Object> getOembedResource(String url, String apiUrl) {
        URI reqeustURL = UriComponentsBuilder.fromHttpUrl(apiUrl)
//                .queryParam("format", "json")
                .queryParam("url", url).encode().build().toUri();

        ResponseEntity<HashMap<String, Object>> exchange = restTemplate.exchange(requestEntity(reqeustURL), new ParameterizedTypeReference<>() {
        });
        return exchange.getBody();
    }

}
