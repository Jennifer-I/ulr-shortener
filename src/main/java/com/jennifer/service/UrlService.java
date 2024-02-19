package com.jennifer.service;

import com.jennifer.dto.UrlRequestDto;
import com.jennifer.dto.UrlResponseDto;
import com.jennifer.model.Url;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public interface UrlService {
    UrlResponseDto generateShortLink(UrlRequestDto requestDto);

    Url getEncodedUrl(String shortLink);

    boolean isValidUrl(String shortLink);

    void redirectToOriginalUrl(String shortLink, HttpServletResponse response) throws IOException;
}
