package com.ai.gemini_chat;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")        // allow all endpoints
                .allowedOrigins("*")      // allow all origins
                .allowedMethods("*")      // allow all HTTP methods
                .allowedHeaders("*")      // allow all headers
                .allowCredentials(false); // cannot use * with credentials
    }
}
