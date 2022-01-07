package me.devksh930.oembed.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oembed")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OembedProperties {
    private String providerListURL;
    private Instagram instagram;
    private Facebook facebook;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Facebook {
        private String accessToken;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Instagram {
        private String accessToken;

    }
}


