package me.devksh930.oembed.service;

import me.devksh930.oembed.client.OembedClient;
import me.devksh930.oembed.dto.EndpointsDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class OembedServiceTest {
    @Mock
    private OembedClient oembedClient;

    @Mock
    private OembedProviderService oembedProviderService;

    @InjectMocks
    private OembedService oembedService;


    private final String inputURL = "https://www.youtube.com/watch?v=dBD54EZIrZo";

    private final String YoutubeOembedURL = "https://www.youtube.com/oembed";

    private EndpointsDto endpointJson() {
        EndpointsDto endpointsDto = new EndpointsDto();

        String s1 = "https://*.youtube.com/watch*";
        endpointsDto.setSchemes(List.of(s1));
        endpointsDto.setUrl(YoutubeOembedURL);
        return endpointsDto;
    }

    private Map<String, Object> oembedResource() {
        Map<String, Object> oembedResource = new HashMap<>();
        oembedResource.put("author_name", "작성자명");
        oembedResource.put("author_url", "작성자 url");
        oembedResource.put("type", "타입");
        oembedResource.put("height", "높이");
        oembedResource.put("width", "넓이");
        oembedResource.put("provider_url", "프로바이더 url");
        return oembedResource;
    }


    @Test
    @DisplayName("성공 : oEmbed리소스를 가져온다")
    void getOembedResource() {
        Mockito.when(oembedProviderService.findByUrlPathMatching(inputURL)).then(invocation -> {
            return endpointJson();
        });
        Mockito.when(oembedClient.getOembedResource(inputURL, YoutubeOembedURL)).then(invocation -> {
            return oembedResource();
        });

        Map<String, Object> result = oembedService.getOembedResource(inputURL);

        assertEquals(result.get("author_name"),oembedResource().get("author_name"));
        assertEquals(result.get("author_url"),oembedResource().get("author_url"));
        assertEquals(result.get("type"),oembedResource().get("type"));
        assertEquals(result.get("height"),oembedResource().get("height"));
        assertEquals(result.get("provider_url"),oembedResource().get("provider_url"));
    }
}