package com.jennifer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "url")
public class Url {

    @Id
    @GeneratedValue
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String originalUrl;
    private String shortLink;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;

}
