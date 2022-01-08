package me.devksh930.oembed.client;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(OembedClient.class)
@AutoConfigureWebClient(registerRestTemplate = true)
class OembedClientTest {
    @Autowired
    private OembedClient oembedClient;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    private static final String OEMBED_API_URL = "https://www.youtube.com/oembed";

    private static final String OEMBED_RESOURCE_URL = "https://www.youtube.com/watch?v=dBD54EZIrZo";

    private String mockApiUrl() {
        return UriComponentsBuilder
                .fromHttpUrl(OEMBED_API_URL)
                .queryParam("url", OEMBED_RESOURCE_URL)
                .encode().build().toUriString();
    }

    @Test
    @DisplayName("oEmbed리소스를 가져온다")
    void getOembedResource() {

        mockRestServiceServer.expect(requestTo(mockApiUrl()))
                .andRespond(withSuccess(new ClassPathResource("/oembedResource.json"), MediaType.APPLICATION_JSON));

        Map<String, Object> oembedResource = oembedClient.getOembedResource(OEMBED_RESOURCE_URL, OEMBED_API_URL);

        assertNotNull(oembedResource.get("provider_name"));
        assertNotNull(oembedResource.get("title"));
    }
}