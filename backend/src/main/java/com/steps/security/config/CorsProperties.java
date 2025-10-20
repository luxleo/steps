package com.steps.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security.cors")
public record CorsProperties(
    String[] allowedOrigins
) {

}
