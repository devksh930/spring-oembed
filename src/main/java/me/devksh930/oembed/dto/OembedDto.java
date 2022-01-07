package me.devksh930.oembed.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OembedDto {

    private String type;

    private String version;

    private String title;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("author_url")
    private String authorUrl;

    @JsonProperty("provider_name")
    private String providerName;

    @JsonProperty("provider_url")
    private String providerUrl;

    private Long cacheAge;

    @JsonProperty("thumbnail_url")
    private String thumbnailUrl;

    @JsonProperty("thumbnail_width")
    private Integer thumbnailWidth;

    @JsonProperty("thumbnail_height")
    private Integer thumbnailHeight;

    private String url;

    private String html;

    private Integer width;

    private Integer height;

}
