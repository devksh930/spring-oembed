package me.devksh930.oembed.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Locale;

@Data
@NoArgsConstructor
public class OembedProviderDto {

    @JsonProperty("provider_name")
    private String providerName;

    @JsonProperty("provider_url")
    private String providerUrl;

    private List<EndpointsDto> endpoints;

    public String getProviderName() {
        return providerName.toLowerCase(Locale.ROOT);
    }

}
