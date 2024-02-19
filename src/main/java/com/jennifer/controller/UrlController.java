package com.jennifer.controller;

import com.jennifer.dto.UrlRequestDto;
import com.jennifer.dto.UrlResponseDto;
import com.jennifer.service.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/url")
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/generateUrl")
    public ResponseEntity<UrlResponseDto> generateUlr(@RequestBody UrlRequestDto requestDto){
     return ResponseEntity.ok(urlService.generateShortLink(requestDto));
    }
    @GetMapping("/{shortLink}")
    public void redirectToOriginalUrl(@PathVariable String shortLink, HttpServletResponse response) throws Exception {
        urlService.redirectToOriginalUrl(shortLink, response);
    }

}
