package com.jennifer.service;

import com.google.common.hash.Hashing;
import com.jennifer.dto.UrlRequestDto;
import com.jennifer.dto.UrlResponseDto;
import com.jennifer.model.Url;
import com.jennifer.repository.UrlRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class UrlServiceImpl implements UrlService {
    private final UrlRepository urlRepository;

    public UrlServiceImpl(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }


    @Override
    public UrlResponseDto generateShortLink(UrlRequestDto requestDto) {
        if (StringUtils.isNotEmpty(requestDto.getOriginalUrl())) {
            String encodedUrl = encodeUrl(requestDto.getOriginalUrl());
            Url url = Url.builder()
                    .originalUrl(requestDto.getOriginalUrl())
                    .shortLink(encodedUrl)
                    .createdAt(LocalDateTime.now())
                    .expiresAt(getExpirationDate(String.valueOf(requestDto.getExpiresAt())))
                    .build();
            if (url != null) {
                urlRepository.save(url);
                UrlResponseDto responseDto = UrlResponseDto.builder()
                        .originalUrl(url.getOriginalUrl())
                        .expiresAt(String.valueOf(url.getExpiresAt()))
                        .shortLink(url.getShortLink())
                        .build();
                return responseDto;

            } else {
                return null;
            }
        }


        return null;
    }

    private String encodeUrl(String url) {
        String encodedUrl;
        LocalDateTime time = LocalDateTime.now();
        encodedUrl = Hashing.sha256().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString();
        return encodedUrl;


    }

    private LocalDateTime getExpirationDate(String expiresAt) {
        if (expiresAt.equals("null")) {
            return LocalDateTime.now().plusMinutes(5);
        } else {
            try {
                return LocalDateTime.parse(expiresAt);
            } catch (DateTimeParseException e) {

                System.err.println("Invalid expiration date format: " + expiresAt);
                return LocalDateTime.now().plusMinutes(5);
            }
        }
    }


    @Override
    public Url getEncodedUrl(String shortLink) {
        return urlRepository.findByShortLink(shortLink);
    }


    @Override
    public boolean isValidUrl(String shortLink) {
        Url url = getEncodedUrl(shortLink);
        return url != null && url.getExpiresAt().isAfter(LocalDateTime.now());
    }

    @Override
    public void redirectToOriginalUrl(String shortLink, HttpServletResponse response) throws IOException {
        if (!isValidUrl(shortLink)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL or URL expired");
            return;
        }
        Url url = getEncodedUrl(shortLink);
        response.sendRedirect(url.getOriginalUrl());
    }
}

