package me.devksh930.oembed;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.EndpointsDto;
import me.devksh930.oembed.dto.OembedDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OembedService {
    private final OembedProviderService oembedProviderService;
    private final OembedClient oembedClient;

    public OembedDto getOembedResource(String url) {
        EndpointsDto byUrlPathMatching = oembedProviderService.findByUrlPathMatching(url);
        return oembedClient.getOembedResource(url, byUrlPathMatching);
    }
}
