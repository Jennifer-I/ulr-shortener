package com.jennifer.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiResponse <T> {
    private String status;
    private String error;

    public ApiResponse(String status, String error) {
        this.status = status;
        this.error = error;
    }
}
