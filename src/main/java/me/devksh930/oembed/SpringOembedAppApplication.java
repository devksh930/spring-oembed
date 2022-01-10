package me.devksh930.oembed;

import me.devksh930.oembed.common.OembedProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        OembedProperties.class
})
public class SpringOembedAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringOembedAppApplication.class, args);
    }

}
