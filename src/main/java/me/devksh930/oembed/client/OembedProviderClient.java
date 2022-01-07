package me.devksh930.oembed.client;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.OembedProviderDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OembedProviderClient {

    private final RestTemplate restTemplate;

    @Value("${oembed.providerListUrl}")
    private String OEMBED_PROIVDER_URL;

    public List<OembedProviderDto> getProvider() {

        restTemplate.getInterceptors().add((request, body, execution) -> {
            ClientHttpResponse response = execution.execute(request, body);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            return response;
        });

        final HttpHeaders headers = new HttpHeaders();
        final HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<OembedProviderDto[]> exchange = restTemplate.exchange(OEMBED_PROIVDER_URL, HttpMethod.GET, entity, OembedProviderDto[].class);
        return new ArrayList<>(Arrays.asList(Objects.requireNonNull(exchange.getBody())));
    }

}