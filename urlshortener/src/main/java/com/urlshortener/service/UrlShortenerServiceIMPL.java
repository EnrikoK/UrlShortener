package com.urlshortener.service;

import com.urlshortener.dto.UserInputDto;
import com.urlshortener.entity.UrlEntity;
import com.urlshortener.exceptions.RedirectNotFoundException;
import com.urlshortener.repository.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;


import java.util.*;

@Service
@RequiredArgsConstructor
public class UrlShortenerServiceIMPL implements UrlShortenerService{

    @Autowired
    private final UrlRepository urlRepository;


    @Override
    public ResponseEntity<UrlEntity> generateShortUrl(UserInputDto url) {
        String[] charset = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
                "T", "U", "V", "W", "X", "Y", "Z","a","b","c","d","e","f","g","h","i","j","k","l","m","n",
                "o","p","q","r","s","t","u","w","x","y","z","1","2","3","4","5","6","7","8","9","0"
        };

        String newUrl = "";
        while(newUrl.equals("") || urlRepository.findByGeneratedUrl(newUrl).isPresent()) {
            for (int i = 0; i < 7; i++) {
                newUrl += charset[(int) (Math.random() * charset.length)];
            }
        }
        UrlEntity generatedUrl = new UrlEntity();
        generatedUrl.setInputUrl(url.getUserInput());
        generatedUrl.setOutputUrl(newUrl);
        generatedUrl.setCreatedAt(new Date());
        urlRepository.save(generatedUrl);
        return ResponseEntity.ok(generatedUrl);
    }

    @Override
    public ResponseEntity<Optional<UrlEntity>> redirectUrl(String redirectUrl) {
        Optional<UrlEntity> originalUrl = urlRepository.findByGeneratedUrl(redirectUrl);
        System.out.println(originalUrl.toString());
        if(originalUrl.isPresent()){
            RedirectView redirectView = new RedirectView();
            redirectView.setUrl(originalUrl.get().getInputUrl());
            return ResponseEntity.ok(originalUrl);
        }else {
            throw new RedirectNotFoundException("Url not found!");
        }
    }


}
