package com.urlshortener.service;
import com.urlshortener.dto.UserInputDto;
import com.urlshortener.entity.UrlEntity;
import com.urlshortener.exceptions.RedirectNotFoundException;
import com.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UrlShortenerServiceIMPL implements UrlShortenerService{

    @Autowired
    private final UrlRepository urlRepository;


    @Override
    public ResponseEntity<UrlEntity> generateShortUrl(UserInputDto url) {

        String charset = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";

        StringBuilder newUrl = new StringBuilder();
        while(newUrl.toString().equals("") || urlRepository.findByGeneratedUrl(newUrl.toString()).isPresent()) {
            newUrl = new StringBuilder();
            for (int i = 0; i < 7; i++) {
                newUrl.append(charset.charAt(((int) (Math.random() * charset.length()))));
            }
        }
        UrlEntity generatedUrl = new UrlEntity();
        generatedUrl.setInputUrl(url.getUserInput());
        generatedUrl.setOutputUrl(newUrl.toString());
        generatedUrl.setCreatedAt(new Date());
        urlRepository.save(generatedUrl);
        return ResponseEntity.ok(generatedUrl);
    }

    @Override
    public ResponseEntity<Optional<UrlEntity>> redirectUrl(String redirectUrl) {
        Optional<UrlEntity> originalUrl = urlRepository.findByGeneratedUrl(redirectUrl);
        if(originalUrl.isPresent()){
            return ResponseEntity.ok(originalUrl);
        }else {
            throw new RedirectNotFoundException("Url not found!");
        }
    }


}
