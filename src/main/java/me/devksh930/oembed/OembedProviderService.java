package me.devksh930.oembed;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.OembedProviderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OembedProviderService {
    private final OembedProviderClient oembedProviderClient;

    public List<OembedProviderDto> getProvider() {
        return oembedProviderClient.getProvider();

    }

}
