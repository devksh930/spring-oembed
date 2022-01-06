package me.devksh930.oembed;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.dto.EndpointsDto;
import me.devksh930.oembed.dto.OembedDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/oembeds")
@RequiredArgsConstructor
public class OembedController {
    private final OembedProviderService oembedProviderService;
    private final OembedService oembedService;

    @GetMapping("/providers")
    public List<EndpointsDto> findAllEndPoint() {
        return oembedProviderService.findAllEndPoint();
    }

    @GetMapping
    public ResponseEntity<OembedDto> getOembedResource(@RequestParam("url") String url) {
        OembedDto oembedResource = oembedService.getOembedResource(url.trim());
        return new ResponseEntity<>(oembedResource, HttpStatus.OK);
    }

}
