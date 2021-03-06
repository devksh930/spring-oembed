package me.devksh930.oembed.service;

import me.devksh930.oembed.client.OembedProviderClient;
import me.devksh930.oembed.dto.EndpointsDto;
import me.devksh930.oembed.dto.OembedProviderDto;
import me.devksh930.oembed.exception.ProviderNotMatchingException;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OembedProviderService {
    private final OembedProviderClient oembedProviderClient;
    private final AntPathMatcher antPathMatcher;

    public OembedProviderService(OembedProviderClient oembedProviderClient) {
        this.oembedProviderClient = oembedProviderClient;
        this.antPathMatcher = new AntPathMatcher();
    }
    public List<EndpointsDto> findAllEndPoint() {

        return oembedProviderClient.getProvider().stream()
                .map(OembedProviderDto::getEndpoints)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

    }
    public EndpointsDto findByUrlPathMatching(final String url) {

        Optional<EndpointsDto> first = this.findAllEndPoint().stream()
                .filter(s -> s
                        .getSchemes()
                        .stream()
                        .anyMatch(a -> antPathMatcher.match(a, url)||url.contains(a))
                ).findFirst();

        return first.orElseThrow(() -> new ProviderNotMatchingException("URL을 잘못 입력했거나 지원하지 않는 URL입니다"));
    }

}
