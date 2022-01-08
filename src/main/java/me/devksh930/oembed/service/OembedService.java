package me.devksh930.oembed.service;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.client.OembedClient;
import me.devksh930.oembed.dto.EndpointsDto;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OembedService {
    private final OembedProviderService oembedProviderService;
    private final OembedClient oembedClient;

    public Map<String, Object> getOembedResource(String url) {
        EndpointsDto byUrlPathMatching = oembedProviderService.findByUrlPathMatching(url);
        return oembedClient.getOembedResource(url, byUrlPathMatching.getUrl());
    }
}
