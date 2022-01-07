package me.devksh930.oembed.service;

import me.devksh930.oembed.client.OembedProviderClient;
import me.devksh930.oembed.dto.EndpointsDto;
import me.devksh930.oembed.dto.OembedProviderDto;
import me.devksh930.oembed.service.OembedProviderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OembedProviderServiceTest {
    @Mock
    private OembedProviderClient oembedProviderClient;

    @InjectMocks
    private OembedProviderService oembedProviderService;

    private final String YoutubeOembedURL = "https://www.youtube.com/oembed";

    private List<OembedProviderDto> mokingJson() {
        OembedProviderDto oembedProviderDto = new OembedProviderDto();
        EndpointsDto endpointsDto = new EndpointsDto();

        String s1 = "https://*.youtube.com/watch*";
        endpointsDto.setSchemes(List.of(s1));
        endpointsDto.setUrl(YoutubeOembedURL);
        oembedProviderDto.setEndpoints(List.of(endpointsDto));
        List<OembedProviderDto> oembedProviderDtos = new ArrayList<>();
        oembedProviderDtos.add(oembedProviderDto);
        return oembedProviderDtos;
    }

    @Test
    @DisplayName("url로 패턴이 매칭된 Endpoint객체를 가져온다")
    void findByUrlPathMatching() {

        Mockito.when(oembedProviderClient.getProvider()).then(invocation -> {
            return mokingJson();
        });

        EndpointsDto byUrlPathMatching = oembedProviderService.findByUrlPathMatching("https://www.youtube.com/watch?v=dBD54EZIrZo");

        assertEquals(byUrlPathMatching.getUrl(), YoutubeOembedURL);

    }

    @Test
    @DisplayName("Oembed의 모든 Endpoint를 가져온다")
    void findAllEndPoint() {
        Mockito.when(oembedProviderClient.getProvider()).then(invocation -> {
            return mokingJson();
        });
        List<EndpointsDto> allEndPoint = oembedProviderService.findAllEndPoint();

        assertNotNull(allEndPoint);
    }

    @Test
    @DisplayName("url로 패턴이 매칭된 Endpoint객체를 가져온다-실패")
    void findByUrlPathMatching_fail() {
        Mockito.when(oembedProviderClient.getProvider()).then(invocation -> {
            return mokingJson();
        });

        assertThrows(RuntimeException.class, () -> {
            oembedProviderService.findByUrlPathMatching("");
        });
    }
}