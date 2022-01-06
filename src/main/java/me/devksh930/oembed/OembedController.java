package me.devksh930.oembed;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.EndpointsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oembed")
@RequiredArgsConstructor
public class OembedController {
    private final OembedProviderService oembedProviderService;
    private final OembedService oembedService;
    private final OembedProviderClient client;

    @GetMapping("/provider")
    public List<EndpointsDto> findAllEndPoint() {
        return oembedProviderService.findAllEndPoint();
    }

    public String getOembedResource(@RequestParam("url") String url) {
        oembedService.getOembedResource(url);
        return "test";
    }

}
