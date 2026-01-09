package com.giffy.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(
        name = "GifClient",
        url = "${giphy.api.url}"
)
public interface GifClient {

    @GetMapping
    Object searchGif(
            @RequestParam("api_key") String apiKey,
            @RequestParam("q") String query,
            @RequestParam("limit") int limit,
            @RequestParam("rating") String rating
    );
}
