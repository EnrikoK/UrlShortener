package com.urlshortener.service;
import com.urlshortener.dto.UserInputDto;
import com.urlshortener.entity.UrlEntity;
import org.springframework.http.ResponseEntity;
import java.util.Optional;

public interface UrlShortenerService {
    ResponseEntity<UrlEntity> generateShortUrl(UserInputDto url);


    ResponseEntity<Optional<UrlEntity>> redirectUrl(String redirectUrl);
}
