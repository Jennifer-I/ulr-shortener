package com.jennifer.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class UrlResponseDto {
private String originalUrl;
private String shortLink;
private String expiresAt;

}
