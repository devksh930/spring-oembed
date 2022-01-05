package me.devksh930.oembed;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.OembedProviderDto;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OembedProviderClient {

    private final RestTemplate restTemplate;

    private static final String OEMBED_PROIVDER_URL = "https://oembed.com/providers.json";

    public List<OembedProviderDto> getProvider() {

        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<OembedProviderDto[]> exchange = restTemplate.exchange(OEMBED_PROIVDER_URL, HttpMethod.GET, entity, OembedProviderDto[].class);
        List<OembedProviderDto> oembedProviderDtos = new ArrayList<OembedProviderDto>(Arrays.asList(exchange.getBody()));

        return oembedProviderDtos;
    }

}