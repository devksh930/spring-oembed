package me.devksh930.oembed.client;

import me.devksh930.oembed.dto.EndpointsDto;
import me.devksh930.oembed.dto.OembedDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class OembedClient {


    private final RestTemplate restTemplate;

    public OembedClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    private RequestEntity<Void> requestEntity(URI uri) {
        return RequestEntity
                .get(uri)
                .build();
    }

    public OembedDto getOembedResource(String url, EndpointsDto byUrlPathMatching) {
        URI reqeustURL = UriComponentsBuilder.fromHttpUrl(byUrlPathMatching.getUrl())
                .queryParam("format", "json")
                .queryParam("url", url).encode().build().toUri();

        ResponseEntity<OembedDto> exchange = restTemplate.exchange(requestEntity(reqeustURL), OembedDto.class);
        return exchange.getBody();
    }

}
