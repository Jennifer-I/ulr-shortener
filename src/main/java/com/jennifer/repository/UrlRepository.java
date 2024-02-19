package com.jennifer.repository;

import com.jennifer.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface UrlRepository extends JpaRepository<Url, Long> {
    Url findByShortLink(String shortLink);
    Url findByOriginalUrl(String originalUrl);

   List<Url> findByExpiresAtBefore(LocalDateTime expiresAt);

    void deleteByExpiresAtBefore(LocalDateTime now);
}
