package me.devksh930.oembed.controller;

import lombok.RequiredArgsConstructor;
import me.devksh930.oembed.service.OembedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/oembeds")
@RequiredArgsConstructor
public class OembedRestController {

    private final OembedService oembedService;

    @GetMapping
    public ResponseEntity<HashMap<String, Object>> getOembedResource(@RequestParam("url") String url) {
        HashMap<String, Object> oembedResource = (HashMap<String, Object>) oembedService.getOembedResource(url.trim());
        return new ResponseEntity<>(oembedResource, HttpStatus.OK);
    }

}
