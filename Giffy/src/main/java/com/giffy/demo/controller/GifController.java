package com.giffy.demo.controller;

import com.giffy.demo.client.GifClient;
import com.giffy.demo.client.GifBinaryClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class GifController {

    private final GifClient gifClient;
    private final GifBinaryClient gifBinaryClient;

    @Value("${giphy.api.key}")
    private String apiKey;

    // 🔼 INCREASE LIMIT HERE
    private static final int LIMIT = 24;

    public GifController(GifClient gifClient, GifBinaryClient gifBinaryClient) {
        this.gifClient = gifClient;
        this.gifBinaryClient = gifBinaryClient;
    }

    @GetMapping("/gifs")
    public List<String> getMultipleGifs(
            @RequestParam String q,
            @RequestParam(defaultValue = "0") int offset
    ) {

        Map<String, Object> response =
                (Map<String, Object>) gifClient.searchGif(
                        apiKey,
                        q,
                        LIMIT,
                        "g",
                        offset
                );

        List<Map<String, Object>> data =
                (List<Map<String, Object>>) response.get("data");

        return data.stream()
                .map(item -> (String) item.get("id"))
                .map(id -> "/api/gif/proxy/" + id)
                .collect(Collectors.toList());
    }

    @GetMapping("/gif/proxy/{id}")
    public ResponseEntity<byte[]> proxyGif(@PathVariable String id) {

        byte[] gif = gifBinaryClient.fetchGif(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "image/gif")
                .body(gif);
    }
}
