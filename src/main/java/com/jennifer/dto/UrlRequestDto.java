package com.jennifer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UrlRequestDto {
    private String OriginalUrl;
    private LocalDateTime expiresAt;

}
