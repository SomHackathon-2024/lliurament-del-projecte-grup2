package com.tecnocampus.hackathon.api.rateLimiting;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

@Service
public class RateLimiterService {

    private final RateLimiter rateLimiter = RateLimiter.create(1.0); // 1 permit per second
    private final ConcurrentHashMap<String, Long> timeoutMap = new ConcurrentHashMap<>();
    private final long timeoutDuration = TimeUnit.SECONDS.toMillis(10); // 60 seconds timeout


    public boolean tryAcquire(String userId) {
        Long timeoutEnd = timeoutMap.get(userId);
        if (timeoutEnd != null && System.currentTimeMillis() < timeoutEnd) {
            return false;
        }

        if (!rateLimiter.tryAcquire()) {
            timeoutMap.put(userId, System.currentTimeMillis() + timeoutDuration);
            return false;
        }

        return true;
    }
}