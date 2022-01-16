package me.devksh930.oembed.client;

import me.devksh930.oembed.dto.OembedProviderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(OembedProviderClient.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class OembedProviderClientTest {


    @Autowired
    private OembedProviderClient oembedProviderClient;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    private static final String OEMBED_PROIVDER_URL = "https://oembed.com/providers.json";

    @Test
    @DisplayName("성공 : Oembed프로바이더 정보가 포함된 리스트를 가져온다")
    void getProviderList() {

        mockRestServiceServer.expect(requestTo(OEMBED_PROIVDER_URL))
                .andRespond(withSuccess(new ClassPathResource("providerList.json"), MediaType.TEXT_PLAIN));

        List<OembedProviderDto> provider = oembedProviderClient.getProvider();
        OembedProviderDto oembedProviderDto = provider.get(0);

        assertNotNull(oembedProviderDto.getProviderName());
        assertNotNull(oembedProviderDto.getProviderUrl());
        assertNotNull(oembedProviderDto.getEndpoints());
    }
}