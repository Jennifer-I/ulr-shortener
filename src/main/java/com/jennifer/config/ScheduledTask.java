package com.jennifer.config;

import com.jennifer.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ScheduledTask {
    private final UrlService urlService;


    @Scheduled(cron = "*/30 * * * * *")
    public void execute(){
        urlService.deleteExpiredUrl();

    }

}
