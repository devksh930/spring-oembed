package me.devksh930.oembed;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.OembedProviderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OembedProviderService {
    private final OembedProviderClient oembedProviderClient;

    public List<OembedProviderDto> getProvider() {
        return oembedProviderClient.getProvider();
    }

    public OembedProviderDto findOembdProviderInfo(String providerName) {

        Optional<OembedProviderDto> oembedProviderDto = this.getProvider().stream()
                .filter(p -> p.getProviderName().equals(providerName))
                .findFirst();

        return oembedProviderDto.orElseThrow(() -> new RuntimeException("없음"));
    }

}
