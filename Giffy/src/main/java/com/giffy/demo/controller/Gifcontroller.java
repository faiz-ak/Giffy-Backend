package com.giffy.demo.controller;

import com.giffy.demo.client.GifClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class Gifcontroller {
    private final GifClient gifClient;

    @Value("${giphy.api.key}")
    private String apiKey;

    public Gifcontroller(GifClient gifClient) {
        this.gifClient = gifClient;
    }

    @GetMapping("/gif")
    public Object getGif(@RequestParam String q) {
        return gifClient.searchGif(apiKey, q, 1, "g");
    }
}
