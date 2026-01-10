package com.giffy.demo.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "gifBinaryClient",
        url = "https://media.giphy.com"
)
public interface GifBinaryClient {

    @GetMapping(
            value = "/media/{id}/giphy.gif",
            produces = MediaType.IMAGE_GIF_VALUE
    )
    byte[] fetchGif(@PathVariable("id") String id);
}
