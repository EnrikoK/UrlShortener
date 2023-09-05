package com.urlshortener.controllers;
import com.urlshortener.dto.UserInputDto;
import com.urlshortener.entity.UrlEntity;
import com.urlshortener.service.UrlShortenerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class UrlShortenerController {

    @Autowired
    private final UrlShortenerService shortenerService;


    @PostMapping("/generate")
    public ResponseEntity<UrlEntity> generateNewUrl(@RequestBody UserInputDto url){
        return shortenerService.generateShortUrl(url);
    }



    @GetMapping("/{redirectUrl}")
    public ResponseEntity<Optional<UrlEntity>> redirect(@PathVariable String redirectUrl) {
        return  shortenerService.redirectUrl(redirectUrl);

    }
}
