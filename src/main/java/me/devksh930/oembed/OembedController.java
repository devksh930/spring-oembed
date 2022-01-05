package me.devksh930.oembed;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.OembedProviderDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oembed")
@RequiredArgsConstructor
public class OembedController {
    private final OembedProviderService oembedProviderService;
    private final OembedService oembedService;

    @GetMapping("/provider")
    public List<OembedProviderDto> test() {
        List<OembedProviderDto> ombed = oembedProviderService.getProvider();
        return ombed;
    }

}
