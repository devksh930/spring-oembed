package me.devksh930.oembed.client;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.OembedDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
@RequiredArgsConstructor
public class OembedClient {


    private final RestTemplate restTemplate;


    private RequestEntity<Void> requestEntity(URI uri) {
        return RequestEntity
                .get(uri)
                .build();
    }

    public OembedDto getOembedResource(String url, String apiUrl) {
        URI reqeustURL = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("format", "json")
                .queryParam("url", url).encode().build().toUri();

        ResponseEntity<OembedDto> exchange = restTemplate.exchange(requestEntity(reqeustURL), OembedDto.class);
        return exchange.getBody();
    }

}
